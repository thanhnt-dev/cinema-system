package com.thanhnt.cinemasystem.service;

import java.util.concurrent.TimeUnit;

public interface CacheService {
  void store(String key, Object value, Integer timeout, TimeUnit timeUnit);
}
