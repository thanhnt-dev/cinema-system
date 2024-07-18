package com.thanhnt.cinemasystem.facade;

import com.thanhnt.cinemasystem.request.MovieCriteria;
import com.thanhnt.cinemasystem.response.*;
import java.util.List;

public interface MovieFacade {
  BaseResponse<List<MovieResponse>> findMovieIsShowing();

  BaseResponse<List<MovieResponse>> findMovieIsComing();

  BaseResponse<MovieDetailResponse> findMovieById(Long id);

  BaseResponse<List<MovieShowingCinemaResponse>> findMovieByCinemaId(Long id);

  BaseResponse<PaginationResponse<List<MovieResponse>>> findByFilter(MovieCriteria criteria);
}
