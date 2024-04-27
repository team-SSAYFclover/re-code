package com.clover.recode.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
@Component
public class JWTUtil {

  private SecretKey secretKey;
  private RedisTemplate redisTemplate;

  public JWTUtil(@Value("${jwt.secret}") String secret, RedisTemplate redisTemplate) {
    secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    this.redisTemplate = redisTemplate;
  }

  public Long getId(String token) {
    try {
      return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
          .get("id", Long.class);
    } catch (ExpiredJwtException e) {  // 만료된 토큰이지만 id 값 추출
      Claims claims = e.getClaims();
      return claims.get("id", Long.class);
    } catch (JwtException e) {  // 다른 JWT 관련 예외 처리 로직
      return null;
    }
  }

  public Boolean isExpired(String token) {
    try {
      return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
          .getExpiration().before(new Date());
    } catch (Exception e) {
      return true;
    }
  }

  public void addRefreshEntity(Long id, String refresh) {
    redisTemplate.opsForValue().set("refresh"+id, refresh, Duration.ofMillis(259200000L));
  }

  public String getRefreshEntity(Long id) {
    return (String) redisTemplate.opsForValue().get("refresh"+id);
  }

  public void deleteRefreshEntity(Long id) {
    redisTemplate.opsForValue().getAndDelete("refresh"+id);
  }


  public String createJwt(Long id, Long expiredMs) {
    return Jwts.builder()
        .claim("id", id)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + expiredMs))
        .signWith(secretKey)
        .compact();
  }
}