package com.thanhnt.cinemasystem.facade;

import com.thanhnt.cinemasystem.request.MovieCriteria;
import com.thanhnt.cinemasystem.request.UpsertMovieRequest;
import com.thanhnt.cinemasystem.response.*;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface MovieFacade {
  BaseResponse<List<MovieResponse>> getMovieIsShowing();

  BaseResponse<List<MovieResponse>> getMovieIsComing();

  BaseResponse<MovieDetailResponse> getMovieById(Long id);

  BaseResponse<List<MovieShowingCinemaResponse>> getMovieByCinemaId(Long id);

  BaseResponse<List<MovieScheduleResponse>> getMovieSchedules(Long id);

  BaseResponse<PaginationResponse<List<MovieResponse>>> getByFilter(MovieCriteria criteria);

  BaseResponse<Void> createMovie(UpsertMovieRequest request);

  BaseResponse<Void> updateMovie(UpsertMovieRequest request);

  BaseResponse<Void> upsertImage(Long id, MultipartFile image);

  BaseResponse<Void> deleteMovie(Long id);
}
