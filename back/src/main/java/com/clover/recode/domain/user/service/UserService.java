package com.clover.recode.domain.user.service;

import com.clover.recode.domain.auth.dto.OAuth2Res;
import com.clover.recode.domain.user.dto.FcmReq;
import com.clover.recode.domain.user.dto.IdRes;
import com.clover.recode.domain.user.dto.SettingDto;
import com.clover.recode.domain.user.dto.UserRes;
import com.clover.recode.domain.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

public interface UserService {

  // 사용자 정보 조회
  public UserRes getUserInfo(Authentication authentication);

  void refreshToken(HttpServletRequest request, HttpServletResponse response);

  void patchSetting(SettingDto setting, Authentication authentication);

  IdRes getGithubId(String uuid);

  User registerUser(OAuth2Res oAuth2Response);

  void postFcmToken(FcmReq fcmReq, Authentication authentication);
}
