package com.clover.recode.domain.auth.service;

import com.clover.recode.domain.auth.dto.CustomOAuth2User;
import com.clover.recode.domain.auth.dto.GithubRes;
import com.clover.recode.domain.auth.dto.TokenRes;
import com.clover.recode.domain.auth.dto.OAuth2Res;
import com.clover.recode.domain.user.entity.User;
import com.clover.recode.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final UserService userService;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    OAuth2User oAuth2User = super.loadUser(userRequest);

    OAuth2Res oAuth2Response = new GithubRes(oAuth2User.getAttributes());

    User user = userService.registerUser(oAuth2Response);

    TokenRes tokenRes = new TokenRes();
    tokenRes.setId(user.getId());

    return new CustomOAuth2User(tokenRes);
  }

}