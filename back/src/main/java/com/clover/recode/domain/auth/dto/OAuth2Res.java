package com.clover.recode.domain.auth.dto;

public interface OAuth2Res {

  //제공자 (Ex. naver, google, ...)
  String getProvider();
  //제공자에서 발급해주는 아이디(번호)
  String getProviderId();
  //사용자 실명 (설정한 이름)
  String getName();
  //프로필 사진 Uri
  String getAvatarUrl();


}
