package com.thanhnt.cinemasystem.facade.impl;

import com.thanhnt.cinemasystem.entity.Movie;
import com.thanhnt.cinemasystem.entity.MovieTime;
import com.thanhnt.cinemasystem.facade.MovieFacade;
import com.thanhnt.cinemasystem.request.MovieCriteria;
import com.thanhnt.cinemasystem.response.*;
import com.thanhnt.cinemasystem.service.MovieService;
import com.thanhnt.cinemasystem.service.MovieTimeService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieFacadeImpl implements MovieFacade {

  private final MovieService movieService;
  private final MovieTimeService movieTimeService;

  @Override
  public BaseResponse<List<MovieResponse>> findMovieIsShowing() {
    List<Movie> list = movieService.findMovieIsShowing();
    List<MovieResponse> movieResponses = list.stream().map(this::buildMovieResponse).toList();
    return BaseResponse.build(movieResponses, true);
  }

  @Override
  public BaseResponse<List<MovieResponse>> findMovieIsComing() {
    List<Movie> list = movieService.findMovieIsComing();
    List<MovieResponse> movieResponses = list.stream().map(this::buildMovieResponse).toList();
    return BaseResponse.build(movieResponses, true);
  }

  @Override
  public BaseResponse<MovieDetailResponse> findMovieById(Long id) {
    Movie movie = movieService.findById(id);
    return BaseResponse.build(
        MovieDetailResponse.builder()
            .id(movie.getId())
            .cast(movie.getCast())
            .ageRated(movie.getAgeRated())
            .description(movie.getDescription())
            .director(movie.getDirector())
            .name(movie.getName())
            .duration(movie.getDuration())
            .genre(movie.getGenre().getGenre())
            .language(movie.getLanguage())
            .image(movie.getImage())
            .trailer(movie.getTrailer())
            .premiere(movie.getPremiere())
            .build(),
        true);
  }

  @Override
  public BaseResponse<List<MovieShowingCinemaResponse>> findMovieByCinemaId(Long cinemaId) {
    List<MovieTime> movieTimes = movieTimeService.findMovieTimeWithCinemaId(cinemaId);

    Map<Long, List<MovieTime>> moviesGrouped =
        movieTimes.stream().collect(Collectors.groupingBy(mt -> mt.getMovieTime().getId()));

    List<MovieShowingCinemaResponse> response = new ArrayList<>();
    for (Map.Entry<Long, List<MovieTime>> entry : moviesGrouped.entrySet()) {
      Long movieId = entry.getKey();
      List<MovieTime> times = entry.getValue();
      String movieName = times.get(0).getMovieTime().getName();
      List<Long> showtime = times.stream().map(MovieTime::getShowtime).collect(Collectors.toList());
      response.add(new MovieShowingCinemaResponse(movieId, movieName, showtime));
    }
    return BaseResponse.build(response, true);
  }

  @Override
  public BaseResponse<PaginationResponse<List<MovieResponse>>> findByFilter(
      MovieCriteria criteria) {
    var result = movieService.findByFilter(criteria);
    List<MovieResponse> movieResponses =
        result.getContent().stream().map(this::buildMovieResponse).toList();
    int currentPage = (criteria.getCurrentPage() == null) ? 1 : criteria.getCurrentPage();
    return BaseResponse.build(PaginationResponse.build(movieResponses, result, currentPage), true);
  }

  private MovieResponse buildMovieResponse(Movie movie) {
    return MovieResponse.builder()
        .id(movie.getId())
        .name(movie.getName())
        .genre(movie.getGenre().getGenre())
        .image(movie.getImage())
        .duration(movie.getDuration())
        .premiere(movie.getPremiere())
        .build();
  }
}
