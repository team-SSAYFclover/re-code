package com.clover.recode.domain.user.entity;

import com.clover.recode.domain.fcmtoken.entity.FcmToken;
import com.clover.recode.domain.statistics.entity.Statistics;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(value = AuditingEntityListener.class)
@ToString
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private Long githubId;
  private String name;
  private String avatarUrl;

  private String uuid;

  @OneToOne(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
  private Setting setting;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<FcmToken> fcmToken;

  @OneToOne(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
  private Statistics statistics;

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdTime;
  private LocalDateTime updatedTime;

  @PrePersist
  public void prePersist() {
    LocalDateTime now = LocalDateTime.now();
    createdTime = now;
    updatedTime = now;
  }

  @PreUpdate
  public void preUpdate() {
    updatedTime = LocalDateTime.now();
  }

}
