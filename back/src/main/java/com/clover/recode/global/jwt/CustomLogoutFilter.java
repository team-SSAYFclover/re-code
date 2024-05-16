package com.olbl.stickeymain.global.jwt;

import com.clover.recode.global.jwt.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomLogoutFilter extends GenericFilterBean {

  private final JWTUtil jwtUtil;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
  }

  private void doFilter(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws IOException, ServletException {
    // Logout 요청이 아니라면 그냥 넘긴다

    String requestUri = request.getRequestURI();
    if (!request.getRequestURI().startsWith("/api/users/logout")) {
      filterChain.doFilter(request, response);
      return;
    }

    String authorization = request.getHeader("Authorization");

    if (authorization == null || !authorization.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    //토큰
    String token = authorization.split(" ")[1];

    //토큰 소멸 시간 검증
    if (jwtUtil.isExpired(token)) {
      filterChain.doFilter(request, response);
      return;
    }
    Cookie[] cookies = request.getCookies();
    if(cookies == null) {
      filterChain.doFilter(request, response);
      return;
    }

    for(Cookie cookie : cookies) {
      if(cookie.getName().equals("refresh_token")) {
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        break;
      }
    }

    //토큰에서 id 획득
    Long id = jwtUtil.getId(token);
    try {
      jwtUtil.deleteRefreshEntity(id);
    } finally {
      response.setStatus(HttpServletResponse.SC_OK);
    }

  }

}