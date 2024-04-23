package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.security.SecurityUserDetails;
import com.thanhnt.cinemasystem.service.JWTService;
import io.jsonwebtoken.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class JWTServiceImpl implements JWTService {
  @Value("${spring.jwt.secretKey}")
  private String secretKey;

  @Value("${spring.jwt.accessTokenExpirationTime}")
  private String accessTokenExpirationTime;

  @Value("${spring.jwt.refreshTokenExpirationTime}")
  private String refreshTokenExpirationTime;

  @Override
  public Boolean validateToken(String token) {
    if (null == token) return false;
    try {
      Jwts.parserBuilder().setSigningKey(secretKey).build().parse(token);
    } catch (SignatureException
        | MalformedJwtException
        | ExpiredJwtException
        | UnsupportedJwtException
        | IllegalArgumentException exception) {

    }
    return true;
  }

  @Override
  public Long getIdFromJwtToken(String token) {
    return Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token)
        .getBody()
        .get("userId", Long.class);
  }

  @Override
  public String getEmailFromJwtToken(String token) {
    return Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token)
        .getBody()
        .get("username", String.class);
  }

  @Override
  public String generateAccessToken(SecurityUserDetails userDetails) {
    Map<String, Object> claims = setClaims(userDetails);
    return Jwts.builder()
        .setSubject(userDetails.getEmail())
        .setClaims(claims)
        .setIssuedAt(new Date())
        .setExpiration(
            new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpirationTime)))
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();
  }

  @Override
  public String generateRefreshToken(SecurityUserDetails userDetails) {
    Map<String, Object> claims = setClaims(userDetails);
    return Jwts.builder()
        .setSubject(userDetails.getEmail())
        .setClaims(claims)
        .setIssuedAt(new Date())
        .setExpiration(
            new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenExpirationTime)))
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();
  }

  private Map<String, Object> setClaims(SecurityUserDetails userDetails) {
    List<String> roles =
        userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", userDetails.getId());
    claims.put("mail", userDetails.getEmail());
    claims.put("roles", roles);
    return claims;
  }
}
