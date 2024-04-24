package com.clover.recode.domain.auth.service;

import com.clover.recode.domain.auth.dto.CustomOAuth2User;
import com.clover.recode.domain.auth.dto.GithubRes;
import com.clover.recode.domain.auth.dto.LoginRes;
import com.clover.recode.domain.auth.dto.OAuth2Res;
import com.clover.recode.domain.user.entity.User;
import com.clover.recode.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    OAuth2User oAuth2User = super.loadUser(userRequest);

    System.out.println(oAuth2User.getAttributes());



    String registrationId = userRequest.getClientRegistration().getRegistrationId();

    OAuth2Res oAuth2Response = null;
    if (registrationId.equals("github")) {
      oAuth2Response = new GithubRes(oAuth2User.getAttributes());
    }
    else {

      return null;
    }

    User existData = userRepository.findBygithubId(Long.parseLong(oAuth2Response.getProviderId()));
    if(existData == null) {
      createNewUser(oAuth2Response);
    }

    LoginRes loginRes = new LoginRes();
    loginRes.setName(oAuth2Response.getName());
    loginRes.setAvatarUri(oAuth2Response.getAvatarUri());

    return new CustomOAuth2User(loginRes);
  }

  private User createNewUser(OAuth2Res oAuth2Response) {
    User user = User.builder()
        .githubId(Long.parseLong(oAuth2Response.getProviderId()))
        .fcmToken(null)
        .build();
    return userRepository.save(user);
  }


}