package com.clover.recode.domain.user.service;

import static com.clover.recode.global.result.error.ErrorCode.*;

import com.clover.recode.domain.auth.dto.CustomOAuth2User;
import com.clover.recode.domain.user.dto.SettingRes;
import com.clover.recode.domain.user.dto.UserRes;
import com.clover.recode.domain.user.entity.User;
import com.clover.recode.domain.user.repository.UserRepository;
import com.clover.recode.global.jwt.JWTUtil;
import com.clover.recode.global.result.error.ErrorCode;
import com.clover.recode.global.result.error.exception.BusinessException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
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
        .settings(SettingRes.builder()
            .difficulty(user.getSetting().getLevel())
            .notificationStatus(user.getSetting().getNotificationStatus())
            .notificationHour(user.getSetting().getNotificationHour())
            .notificationMinute(user.getSetting().getNotificationMinute())
            .build())
        .build();

    return userRes;
  }

  @Override
  public void refreshToken(String token, String refresh, HttpServletResponse response) {


    // 리프레시 토큰이 없거나, 만료되었다면 예외 발생
    if (token == null || !token.startsWith("Bearer ")) {
      throw new BusinessException(HTTP_HEADER_INVALID);
    }

    token = token.split(" ")[1];

    Long id = jwtUtil.getId(token);
    String storedRefreshToken = jwtUtil.getRefreshEntity(id);

    log.info("token : {}, id : {}, refresh : {}, stored: {}",token, id, refresh, storedRefreshToken);

    if(refresh == null || !refresh.equals(storedRefreshToken)) {
      try {
        jwtUtil.deleteRefreshEntity(id);
      } catch(Exception e) {
        throw new BusinessException(REFRESH_TOKEN_INVALID);
      }
    }

    String newAccess = jwtUtil.createJwt(id, 10800000L);
    String updatedRefreshToken = UUID.randomUUID().toString();
    jwtUtil.addRefreshEntity(id, updatedRefreshToken);
    response.setHeader("access_token", newAccess);
    response.addCookie(createCookie("refresh_token", updatedRefreshToken));
  }

  private Cookie createCookie(String key, String value) {
    Cookie cookie = new Cookie(key, value);
    cookie.setMaxAge(259200);
    // TODO : 개발 끝나면 쿠키 Secure 주석풀기
    //cookie.setSecure(true);
    cookie.setPath("/");
    cookie.setHttpOnly(true);

    return cookie;
  }
}
