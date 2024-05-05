package com.clover.recode.domain.auth.service;

import com.clover.recode.domain.auth.dto.CustomOAuth2User;
import com.clover.recode.domain.auth.dto.GithubRes;
import com.clover.recode.domain.auth.dto.TokenRes;
import com.clover.recode.domain.auth.dto.OAuth2Res;
import com.clover.recode.domain.statistics.entity.AlgoReview;
import com.clover.recode.domain.statistics.entity.Statistics;
import com.clover.recode.domain.statistics.entity.WeekReview;
import com.clover.recode.domain.statistics.repository.AlgoReviewRepository;
import com.clover.recode.domain.statistics.repository.StatisticsRepository;
import com.clover.recode.domain.statistics.repository.WeekReviewRepository;
import com.clover.recode.domain.user.entity.Setting;
import com.clover.recode.domain.user.entity.User;
import com.clover.recode.domain.user.repository.SettingRepository;
import com.clover.recode.domain.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;
  private final SettingRepository settingRepository;
  private final StatisticsRepository statisticsRepository;
  private final WeekReviewRepository weekReviewRepository;
  private final AlgoReviewRepository algoReviewRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    OAuth2User oAuth2User = super.loadUser(userRequest);

    OAuth2Res oAuth2Response = new GithubRes(oAuth2User.getAttributes());

    User user = userRepository.findByGithubId(Long.parseLong(oAuth2Response.getProviderId()));
    if(user == null) {
      user = createNewUser(oAuth2Response);
    } else {
      user.setName(oAuth2Response.getName());
      userRepository.save(user);
    }

    TokenRes tokenRes = new TokenRes();
    tokenRes.setId(user.getId());

    return new CustomOAuth2User(tokenRes);
  }

  @Transactional
  protected User createNewUser(OAuth2Res oAuth2Response) {
    User user = User.builder()
        .githubId(Long.parseLong(oAuth2Response.getProviderId()))
        .avatarUrl(oAuth2Response.getAvatarUrl())
        .name(oAuth2Response.getName())
        .uuid(UUID.randomUUID().toString())
        .build();

    Setting setting = Setting.builder()
        .user(user)
        .build();

    settingRepository.save(setting);

    Statistics statistics= Statistics.builder()
            .user(user)
            .build();

    statisticsRepository.save(statistics);

    WeekReview weekReview= WeekReview.builder()
            .statistics(statistics)
            .date(LocalDate.now())
            .build();

    weekReviewRepository.save(weekReview);

    AlgoReview algoReview= AlgoReview.builder()
            .id(statistics.getId())
            .statistics(statistics)
            .build();

    algoReviewRepository.save(algoReview);

    return user;
  }

}