package com.clover.recode.domain.problem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class CodeRegistReq {
  private Integer codeNo;
  private String name;
  private String content;

}
