package com.clover.recode.domain.auth.service;

import com.clover.recode.domain.auth.dto.CustomOAuth2User;
import com.clover.recode.domain.auth.dto.GithubRes;
import com.clover.recode.domain.auth.dto.TokenRes;
import com.clover.recode.domain.auth.dto.OAuth2Res;
import com.clover.recode.domain.user.entity.Setting;
import com.clover.recode.domain.user.entity.User;
import com.clover.recode.domain.user.repository.SettingRepository;
import com.clover.recode.domain.user.repository.UserRepository;
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

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    OAuth2User oAuth2User = super.loadUser(userRequest);

    System.out.println(oAuth2User.getAttributes());

    String registrationId = userRequest.getClientRegistration().getRegistrationId();

    OAuth2Res oAuth2Response = new GithubRes(oAuth2User.getAttributes());


    User existData = userRepository.findBygithubId(Long.parseLong(oAuth2Response.getProviderId()))
        .orElseGet(() -> createNewUser(oAuth2Response));


    TokenRes tokenRes = new TokenRes();
    tokenRes.setId(existData.getId());
    tokenRes.setGithubId(existData.getGithubId());

    return new CustomOAuth2User(tokenRes);
  }

  @Transactional
  protected User createNewUser(OAuth2Res oAuth2Response) {
    User user = User.builder()
        .githubId(Long.parseLong(oAuth2Response.getProviderId()))
        .avatarUrl(oAuth2Response.getAvatarUrl())
        .name(oAuth2Response.getName())
        .build();
    userRepository.saveAndFlush(user);
    Setting setting = Setting.builder()
        .user(user).build();
    settingRepository.save(setting);
    return user;
  }

}