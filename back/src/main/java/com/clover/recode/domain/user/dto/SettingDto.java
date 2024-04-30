package com.clover.recode.domain.user.dto;

import java.sql.Time;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
public class SettingDto {

  private Integer difficulty;
  private Boolean notificationStatus;
  private Time notificationTime;

}
