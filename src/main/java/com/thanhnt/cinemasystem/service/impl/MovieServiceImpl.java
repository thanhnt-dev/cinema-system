package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.entity.Movie;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.MovieException;
import com.thanhnt.cinemasystem.repository.MovieRepository;
import com.thanhnt.cinemasystem.request.MovieCriteria;
import com.thanhnt.cinemasystem.service.MovieService;
import com.thanhnt.cinemasystem.specifications.MovieSpecifications;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
  private final MovieRepository movieRepository;

  @Override
  public List<Movie> findMovieIsShowing() {
    return movieRepository.findMovieIsShowing();
  }

  @Override
  public List<Movie> findMovieIsComing() {
    return movieRepository.findMovieIsComing();
  }

  @Override
  public Movie findById(Long id) {
    return movieRepository
        .findById(id)
        .orElseThrow(() -> new MovieException(ErrorCode.MOVIE_NOT_FOUND));
  }

  @Override
  public Page<Movie> findByFilter(MovieCriteria criteria) {
    int currentPage = (criteria.getCurrentPage() == null) ? 1 : criteria.getCurrentPage();
    int pageSize = (criteria.getPageSize() == null) ? 10 : criteria.getPageSize();
    Pageable pageable = PageRequest.of(Math.max(currentPage - 1, 0), pageSize);
    Specification<Movie> specification = MovieSpecifications.baseSpecification();

    if (criteria.getMovieName() != null) {
      specification = specification.and(MovieSpecifications.filterByName(criteria.getMovieName()));
    }

    return movieRepository.findAll(specification, pageable);
  }
}
