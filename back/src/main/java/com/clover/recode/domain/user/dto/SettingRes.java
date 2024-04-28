package com.clover.recode.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
@Setter
public class SettingRes {

  private int difficulty;
  private boolean notificationStatus;
  private int notificationHour;
  private int notificationMinute;

}
