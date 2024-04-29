package com.clover.recode.domain.user.controller;

import static com.clover.recode.global.result.ResultCode.*;

import com.clover.recode.domain.user.dto.UserRes;
import com.clover.recode.domain.user.service.UserService;
import com.clover.recode.global.result.ResultCode;
import com.clover.recode.global.result.ResultResponse;
import com.clover.recode.global.result.error.exception.BusinessException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<ResultResponse> getUserInfo(Authentication authentication) {
    UserRes userRes = userService.getUserInfo(authentication);
    return ResponseEntity.ok(ResultResponse.of(GET_USER_INFO_SUCCESS, userRes));
  }

  @PostMapping("/refresh")
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

}
