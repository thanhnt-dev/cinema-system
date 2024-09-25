package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.Movie;
import com.thanhnt.cinemasystem.request.MovieCriteria;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface MovieService {
  List<Movie> findMovieIsShowing();

  List<Movie> findMovieIsComing();

  Optional<Movie> findById(Long id);

  Page<Movie> findByFilter(MovieCriteria criteria);

  void saveMovie(Movie movie);

  void deactivateMovie(Long id);
}
