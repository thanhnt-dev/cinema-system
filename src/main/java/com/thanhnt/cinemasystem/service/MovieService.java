package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.Movie;
import com.thanhnt.cinemasystem.request.MovieCriteria;
import java.util.List;
import org.springframework.data.domain.Page;

public interface MovieService {
  List<Movie> findMovieIsShowing();

  List<Movie> findMovieIsComing();

  Movie findById(Long id);

  Page<Movie> findByFilter(MovieCriteria criteria);
}
