package com.clover.recode.domain.user.service;

import static com.clover.recode.global.result.error.ErrorCode.*;

import com.clover.recode.domain.auth.dto.CustomOAuth2User;
import com.clover.recode.domain.auth.dto.OAuth2Res;
import com.clover.recode.domain.fcmtoken.entity.FcmToken;
import com.clover.recode.domain.fcmtoken.repository.FcmTokenRepository;
import com.clover.recode.domain.statistics.entity.AlgoReview;
import com.clover.recode.domain.statistics.entity.Statistics;
import com.clover.recode.domain.statistics.entity.WeekReview;
import com.clover.recode.domain.statistics.repository.AlgoReviewRepository;
import com.clover.recode.domain.statistics.repository.StatisticsRepository;
import com.clover.recode.domain.statistics.repository.WeekReviewRepository;
import com.clover.recode.domain.user.dto.FcmReq;
import com.clover.recode.domain.user.dto.IdRes;
import com.clover.recode.domain.user.dto.SettingDto;
import com.clover.recode.domain.user.dto.UserRes;
import com.clover.recode.domain.user.entity.Setting;
import com.clover.recode.domain.user.entity.User;
import com.clover.recode.domain.user.repository.SettingRepository;
import com.clover.recode.domain.user.repository.UserRepository;
import com.clover.recode.global.jwt.JWTUtil;
import com.clover.recode.global.result.error.exception.BusinessException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final SettingRepository settingRepository;
  private final FcmTokenRepository fcmTokenRepository;
  private final StatisticsRepository statisticsRepository;
  private final WeekReviewRepository weekReviewRepository;
  private final AlgoReviewRepository algoReviewRepository;
  private final JWTUtil jwtUtil;


  @Override
  public UserRes getUserInfo(Authentication authentication) {

    CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

    User user = userRepository.findById(customUserDetails.getId())
        .orElseThrow(() -> new BusinessException(USER_NOT_EXISTS));

    UserRes userRes = UserRes.builder()
        .name(user.getName())
        .avatarUrl(user.getAvatarUrl())
        .uuid(user.getUuid())
        .settings(SettingDto.builder()
            .difficulty(user.getSetting().getDifficulty())
            .notificationStatus(user.getSetting().getNotificationStatus())
            .notificationTime(user.getSetting().getNotificationTime())
            .build())
        .build();

    return userRes;
  }

  @Override
  @Transactional
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) {

    // 리프레시 토큰 획득
    String token = request.getHeader("Authorization");
    String refresh = null;
    Cookie[] cookies = request.getCookies();
    if(cookies == null) {
      throw new BusinessException(HTTP_HEADER_INVALID);
    }

    for(Cookie cookie : cookies) {
      if(cookie.getName().equals("refresh_token")) {
        refresh = cookie.getValue();
        break;
      }
    }

    // 리프레시 토큰이 없거나, 만료되었다면 예외 발생
    if (token == null || !token.startsWith("Bearer ")) {
      throw new BusinessException(HTTP_HEADER_INVALID);
    }

    token = token.split(" ")[1];

    Long id = jwtUtil.getId(token);
    String storedRefreshToken = jwtUtil.getRefreshEntity(id);

    if(refresh == null || !refresh.equals(storedRefreshToken)) {
      try {
        jwtUtil.deleteRefreshEntity(id);
      } catch(Exception e) {
        throw new BusinessException(REFRESH_TOKEN_INVALID);
      }
    }

    String newAccess = jwtUtil.createJwt(id, 1800000L);
    String updatedRefreshToken = UUID.randomUUID().toString();
    jwtUtil.addRefreshEntity(id, updatedRefreshToken);
    response.setHeader("access_token", newAccess);
    response.addCookie(createCookie("refresh_token", updatedRefreshToken));
  }

  @Override
  @Transactional
  public void patchSetting(SettingDto settingDto, Authentication authentication) {

    CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

    User user = userRepository.findById(customUserDetails.getId())
        .orElseThrow(() -> new BusinessException(USER_NOT_EXISTS));

    Setting setting = user.getSetting();

    setting.updateSetting(settingDto);

    settingRepository.save(setting);
  }

  @Override
  public IdRes getGithubId(String uuid) {
    IdRes id = userRepository.findByUuid(uuid)
        .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
    return id;
  }

  private Cookie createCookie(String key, String value) {
    Cookie cookie = new Cookie(key, value);
    cookie.setMaxAge(259200);
    cookie.setSecure(true);
    cookie.setPath("/");
    cookie.setHttpOnly(true);

    return cookie;
  }

  @Transactional
  public User registerUser(OAuth2Res oAuth2Response) {

    User user = userRepository.findByGithubId(Long.parseLong(oAuth2Response.getProviderId()));
    if(user == null) {
      user = createUser(oAuth2Response);
    } else {
      user.setName(oAuth2Response.getName());
      userRepository.save(user);
    }

    return user;
  }

  @Override
  @Transactional
  public void postFcmToken(FcmReq fcmReq, Authentication authentication) {

    CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

    if(fcmTokenRepository.existsByToken(fcmReq.getToken()))
      return;
    User user = userRepository.findById(customUserDetails.getId())
        .orElseThrow(() -> new BusinessException(USER_NOT_EXISTS));

    FcmToken fcmToken = FcmToken.builder()
        .token(fcmReq.getToken())
        .user(user)
        .build();

    fcmTokenRepository.save(fcmToken);
  }

  private User createUser(OAuth2Res oAuth2Response) {
    User user = User.builder()
        .githubId(Long.parseLong(oAuth2Response.getProviderId()))
        .avatarUrl(oAuth2Response.getAvatarUrl())
        .name(oAuth2Response.getName())
        .uuid(UUID.randomUUID().toString())
        .build();

    userRepository.save(user);

    Setting setting = Setting.builder()
        .user(user)
        .build();

    settingRepository.save(setting);

    Statistics statistics= Statistics.builder()
            .user(user)
            .ranking(100)
            .supplementaryNo(1806)
            .randomNo(2178)
            .supplementaryTitle("부분합")
            .randomTitle("미로 탐색")
            .sequence(0)
            .build();

    statisticsRepository.save(statistics);

    AlgoReview algoReview= AlgoReview.builder()
            .statistics(statistics)
            .build();

    algoReviewRepository.save(algoReview);

    return user;
  }


}
