package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.entity.MovieTime;
import com.thanhnt.cinemasystem.repository.MovieTimeRepository;
import com.thanhnt.cinemasystem.service.MovieTimeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieTimeServiceImpl implements MovieTimeService {
  private final MovieTimeRepository movieTimeRepository;

  @Override
  public List<MovieTime> findMovieTimeByCinemaId(Long id) {
    return movieTimeRepository.findByCinemaScreen(id);
  }
}
