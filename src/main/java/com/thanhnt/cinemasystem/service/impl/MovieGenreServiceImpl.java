package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.entity.MovieGenre;
import com.thanhnt.cinemasystem.repository.MovieGenreRepository;
import com.thanhnt.cinemasystem.service.MovieGenreService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieGenreServiceImpl implements MovieGenreService {
  private final MovieGenreRepository movieGenreRepository;

  @Override
  public Optional<MovieGenre> findById(Long id) {
    return movieGenreRepository.findById(id);
  }
}
