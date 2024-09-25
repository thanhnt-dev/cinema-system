package com.thanhnt.cinemasystem.controller;

import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.UnauthorizedException;
import com.thanhnt.cinemasystem.facade.MovieFacade;
import com.thanhnt.cinemasystem.request.MovieCriteria;
import com.thanhnt.cinemasystem.request.UpsertMovieRequest;
import com.thanhnt.cinemasystem.response.*;
import io.jsonwebtoken.io.IOException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {
  private final MovieFacade movieFacade;

  @GetMapping("/now-showing")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"MOVIE APIs"},
      summary = "Movie is showing")
  public BaseResponse<List<MovieResponse>> getMovieShowing() {
    return this.movieFacade.getMovieIsShowing();
  }

  @GetMapping("/up-coming")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"MOVIE APIs"},
      summary = "Movie is coming")
  public BaseResponse<List<MovieResponse>> getMovieComing() {
    return this.movieFacade.getMovieIsComing();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"MOVIE APIs"},
      summary = "Movie is coming")
  public BaseResponse<MovieDetailResponse> getMovieById(@PathVariable("id") Long id) {
    return this.movieFacade.getMovieById(id);
  }

  @GetMapping("/cinema/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"MOVIE APIs"},
      summary = "Get movie information from cinema id")
  public BaseResponse<List<MovieShowingCinemaResponse>> getMovieByCinemaId(
      @PathVariable("id") Long id) {
    return this.movieFacade.getMovieByCinemaId(id);
  }

  @GetMapping("/schedule/{movieId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"MOVIE APIs"},
      summary = "Get schedule information from movie id")
  public BaseResponse<List<MovieScheduleResponse>> getMovieSchedule(
      @PathVariable("movieId") Long id) {
    return this.movieFacade.getMovieSchedules(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"MOVIE APIs"},
      summary = "Get movie by criteria")
  public BaseResponse<PaginationResponse<List<MovieResponse>>> getMovie(
      @Nullable MovieCriteria criteria) {
    return this.movieFacade.getByFilter(criteria);
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"MOVIE APIs"},
      summary = "Create new movie")
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> createMovie(
      @RequestBody @Valid UpsertMovieRequest request, @RequestHeader("API_KEY") String apiKey)
      throws IOException {

    if (!apiKey.equals("123")) {
      throw new UnauthorizedException(ErrorCode.API_KEY_INVALID);
    }
    return this.movieFacade.createMovie(request);
  }

  @PutMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"MOVIE APIs"},
      summary = "Update movie")
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> updateMovie(
      @RequestBody @Valid UpsertMovieRequest request, @RequestHeader("API_KEY") String apiKey)
      throws IOException {

    if (!apiKey.equals("123")) {
      throw new UnauthorizedException(ErrorCode.API_KEY_INVALID);
    }
    return this.movieFacade.updateMovie(request);
  }

  @PostMapping("/add-image/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"MOVIE APIs"},
      summary = "Upsert image movie")
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> addImage(
      @PathVariable("id") Long id,
      @RequestPart MultipartFile image,
      @RequestHeader("API_KEY") String apiKey)
      throws IOException {

    if (!apiKey.equals("123")) {
      throw new UnauthorizedException(ErrorCode.API_KEY_INVALID);
    }
    return this.movieFacade.upsertImage(id, image);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"MOVIE APIs"},
      summary = "Delete movie")
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> deleteMovie(
      @PathVariable("id") Long id, @RequestHeader("API_KEY") String apiKey) {
    if (!apiKey.equals("123")) {
      throw new UnauthorizedException(ErrorCode.API_KEY_INVALID);
    }
    return this.movieFacade.deleteMovie(id);
  }
}
