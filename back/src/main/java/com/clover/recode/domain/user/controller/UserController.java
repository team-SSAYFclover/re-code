package com.clover.recode.domain.user.controller;

import static com.clover.recode.global.result.ResultCode.*;

import com.clover.recode.domain.user.dto.FcmReq;
import com.clover.recode.domain.user.dto.IdRes;
import com.clover.recode.domain.user.dto.SettingDto;
import com.clover.recode.domain.user.dto.UserRes;
import com.clover.recode.domain.user.service.UserService;
import com.clover.recode.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@Slf4j
@Tag(name = "users", description = "사용자 API")
public class UserController {

  private final UserService userService;

  @Operation(summary = "사용자 정보 조회")
  @GetMapping
  public ResponseEntity<ResultResponse> getUserInfo(Authentication authentication) {
    UserRes userRes = userService.getUserInfo(authentication);
    return ResponseEntity.ok(ResultResponse.of(GET_USER_INFO_SUCCESS, userRes));
  }

  @Operation(summary = "액세스 토큰 재발급")
  @PostMapping("/reissue")
  public ResponseEntity<ResultResponse> refreshToken(HttpServletRequest request,
      HttpServletResponse response) {

    // 리프레시 토큰 획득
    String token = request.getHeader("Authorization");
    String refresh = null;
    Cookie[] cookies = request.getCookies();
    for(Cookie cookie : cookies) {
      if(cookie.getName().equals("refresh_token")) {
        refresh = cookie.getValue();
        break;
      }
    }

    userService.refreshToken(token, refresh, response);
    return ResponseEntity.ok(ResultResponse.of(TOKEN_REISSUE_SUCCESS));
  }

  @Operation(summary = "사용자 설정 변경")
  @PatchMapping("/setting")
  public ResponseEntity<ResultResponse> patchSetting(@RequestBody SettingDto settingDto,
      Authentication authentication) {
    userService.patchSetting(settingDto, authentication);
    return ResponseEntity.ok(ResultResponse.of(PATCH_USER_SETTING_SUCCESS));
  }

  @Operation(summary = "사용자 코드로 고유 번호 조회")
  @GetMapping("/code")
  public ResponseEntity<ResultResponse> getGithubId(String uuid) {
    IdRes id = userService.getGithubId(uuid);
    return ResponseEntity.ok(ResultResponse.of(GET_USER_NUMBER_SUCCESS, id));
  }

  @Operation(summary = "FCM 토큰 등록")
  @PostMapping("/fcm")
  public ResponseEntity<ResultResponse> postFcmToken(@RequestBody FcmReq fcmReq,Authentication authentication) {
    userService.postFcmToken(fcmReq, authentication);
    return ResponseEntity.ok(ResultResponse.of(POST_TOKEN_SUCCESS));
  }



}
