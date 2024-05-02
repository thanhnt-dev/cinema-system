package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.service.CacheService;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheIServicempl implements CacheService {
  private final RedisTemplate<String, Object> redisTemplate;

  @Override
  public void store(String key, Object value, Integer timeout, TimeUnit timeUnit) {
    this.redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
  }
}
