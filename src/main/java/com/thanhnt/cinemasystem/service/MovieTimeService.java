package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.MovieTime;
import java.util.List;

public interface MovieTimeService {
  List<MovieTime> findMovieTimeWithCinemaId(Long id);
}
