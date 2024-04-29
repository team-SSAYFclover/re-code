package com.clover.recode.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class UserRes {

  private String name;
  private String avatarUrl;
  private String uuid;
  private SettingRes settings;

}
