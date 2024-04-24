package com.clover.recode.domain.auth.dto;

import java.util.Collection;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@AllArgsConstructor
public class CustomOAuth2User implements OAuth2User {
  private final LoginRes loginRes;
  @Override
  public Map<String, Object> getAttributes() {
    return null;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getName() {
    return loginRes.getName();
  }

  public String getAvatarUrl() {
    return loginRes.getAvatarUri();
  }
  public Long getId() {
    return loginRes.getId();
  }

}
