package com.clover.recode.domain.user.service;

import com.clover.recode.domain.user.dto.idRes;
import com.clover.recode.domain.user.dto.SettingDto;
import com.clover.recode.domain.user.dto.UserRes;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

public interface UserService {

  // 사용자 정보 조회
  public UserRes getUserInfo(Authentication authentication);

  void refreshToken(String token, String id, HttpServletResponse response);

  void patchSetting(SettingDto setting, Authentication authentication);

  idRes getGithubId(String uuid);
}
