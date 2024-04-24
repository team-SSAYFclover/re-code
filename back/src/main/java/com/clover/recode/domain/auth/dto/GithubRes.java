package com.clover.recode.domain.auth.dto;

import java.util.Map;

public class GithubRes implements OAuth2Res{

  private final Map<String, Object> attribute;

  public GithubRes(Map<String, Object> attribute) {
    this.attribute = attribute;
  }

  @Override
  public String getProvider() {
    return "github";
  }

  @Override
  public String getProviderId() {
    return attribute.get("id").toString();
  }

  @Override
  public String getName() {
    return attribute.get("login").toString();
  }

  @Override
  public String getAvatarUri() {
    return attribute.get("avatar_url").toString();
  }
}
