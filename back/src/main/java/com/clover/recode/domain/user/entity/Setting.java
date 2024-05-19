package com.clover.recode.domain.user.entity;

import com.clover.recode.domain.user.dto.SettingDto;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import java.sql.Time;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(of = {"difficulty", "notificationStatus", "notificationTime"})
@DynamicInsert
public class Setting {

  @Id
  private Long userId;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "user_id")
  private User user;

  @ColumnDefault("1")
  private Integer difficulty;


  @ColumnDefault("true")
  private Boolean notificationStatus;

  @ColumnDefault("150000")
  private Time notificationTime;

  public void updateSetting(SettingDto settingDto) {
    if(settingDto.getDifficulty() != null) {
      this.difficulty = settingDto.getDifficulty();
    }
    if(settingDto.getNotificationStatus() != null) {
      this.notificationStatus = settingDto.getNotificationStatus();
    }
    if(settingDto.getNotificationTime() != null) {
      this.notificationTime = settingDto.getNotificationTime();
    }
  }

}
