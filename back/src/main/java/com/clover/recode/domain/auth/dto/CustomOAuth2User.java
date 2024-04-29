package com.clover.recode.domain.auth.dto;

import java.util.Collection;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@AllArgsConstructor
@ToString
public class CustomOAuth2User implements OAuth2User {
  private final TokenRes tokenRes;
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
    return tokenRes.getId().toString();
  }

  public Long getId() {
    return tokenRes.getId();
  }

}
