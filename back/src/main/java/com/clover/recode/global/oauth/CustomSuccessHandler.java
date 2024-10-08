package com.clover.recode.global.oauth;

import com.clover.recode.domain.auth.dto.CustomOAuth2User;
import com.clover.recode.global.jwt.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final JWTUtil jwtUtil;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

    //OAuth2User
    CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

    Long id = customUserDetails.getId();

    String access = jwtUtil.createJwt(id, 1800000L);
    String refresh = UUID.randomUUID().toString();
    jwtUtil.addRefreshEntity(id, refresh);

    response.addCookie(createCookie("refresh_token", refresh));

    StringBuilder sb = new StringBuilder();

    sb.append("https://re-code.site/redirect");
    sb.append("?access_token=").append(access);

    response.sendRedirect(sb.toString());
  }

  private Cookie createCookie(String key, String value) {
    Cookie cookie = new Cookie(key, value);
    cookie.setMaxAge(259200);
    cookie.setSecure(true);
    cookie.setPath("/");
    cookie.setHttpOnly(true);

    return cookie;
  }
}
