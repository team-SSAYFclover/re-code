package com.clover.recode.domain.auth.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TokenRes {

  private Long id;
  private Long githubId;

}
