package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.MovieGenre;
import java.util.Optional;

public interface MovieGenreService {
  Optional<MovieGenre> findById(Long id);
}
