package com.thanhnt.cinemasystem.controller;

import com.thanhnt.cinemasystem.facade.MovieFacade;
import com.thanhnt.cinemasystem.request.MovieCriteria;
import com.thanhnt.cinemasystem.response.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Nullable;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {
  private final MovieFacade movieFacade;

  @GetMapping("/showing")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"MOVIE APIs"},
      summary = "Movie is showing")
  public BaseResponse<List<MovieResponse>> getMovieShowing() {
    return this.movieFacade.findMovieIsShowing();
  }

  @GetMapping("/coming")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"MOVIE APIs"},
      summary = "Movie is coming")
  public BaseResponse<List<MovieResponse>> getMovieComing() {
    return this.movieFacade.findMovieIsComing();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"MOVIE APIs"},
      summary = "Movie is coming")
  public BaseResponse<MovieDetailResponse> getMovieById(@PathVariable("id") Long id) {
    return this.movieFacade.findMovieById(id);
  }

  @GetMapping("/cinema/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"MOVIE APIs"},
      summary = "Get movie information from cinema id")
  public BaseResponse<List<MovieShowingCinemaResponse>> getMovieByCinemaId(
      @PathVariable("id") Long id) {
    return this.movieFacade.findMovieByCinemaId(id);
  }

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"MOVIE APIs"},
      summary = "Get movie by criteria")
  public BaseResponse<PaginationResponse<List<MovieResponse>>> getMovie(
      @Nullable MovieCriteria criteria) {
    return this.movieFacade.findByFilter(criteria);
  }
}
