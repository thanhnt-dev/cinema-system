package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.MovieTime;
import java.util.List;
import java.util.Optional;

public interface MovieTimeService {
  List<MovieTime> findMovieTimeByCinemaId(Long id);

  List<MovieTime> findMovieTimeByMovieId(Long id);

  Optional<MovieTime> findById(Long id);
}
