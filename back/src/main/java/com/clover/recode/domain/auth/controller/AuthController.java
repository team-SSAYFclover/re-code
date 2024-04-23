package com.clover.recode.domain.auth.controller;

import com.clover.recode.domain.auth.dto.LoginReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

  @PostMapping("/login")
  public String loginOauth() {
    log.info("login");
    return "loginOAuth";
  }

}
