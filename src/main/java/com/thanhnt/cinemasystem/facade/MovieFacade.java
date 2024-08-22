package com.thanhnt.cinemasystem.facade;

import com.thanhnt.cinemasystem.request.MovieCriteria;
import com.thanhnt.cinemasystem.response.*;
import java.util.List;

public interface MovieFacade {
  BaseResponse<List<MovieResponse>> getMovieIsShowing();

  BaseResponse<List<MovieResponse>> getMovieIsComing();

  BaseResponse<MovieDetailResponse> getMovieById(Long id);

  BaseResponse<List<MovieShowingCinemaResponse>> getMovieByCinemaId(Long id);

  BaseResponse<List<MovieScheduleResponse>> getMovieSchedules(Long id);

  BaseResponse<PaginationResponse<List<MovieResponse>>> getByFilter(MovieCriteria criteria);
}
