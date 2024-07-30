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
  public BaseResponse<List<MovieResponse>> getMovieIsShowing() {
    List<Movie> movies = movieService.findMovieIsShowing();
    List<MovieResponse> responses = movies.stream().map(this::buildMovieResponse).toList();
    return BaseResponse.build(responses, true);
  }

  @Override
  public BaseResponse<List<MovieResponse>> getMovieIsComing() {
    List<Movie> movies = movieService.findMovieIsComing();
    List<MovieResponse> responses = movies.stream().map(this::buildMovieResponse).toList();
    return BaseResponse.build(responses, true);
  }

  @Override
  public BaseResponse<MovieDetailResponse> getMovieById(Long id) {
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
  public BaseResponse<List<MovieShowingCinemaResponse>> getMovieByCinemaId(Long cinemaId) {
    List<MovieTime> movieTimes = movieTimeService.findMovieTimeByCinemaId(cinemaId);

    Map<Long, List<MovieTime>> moviesGrouped =
        movieTimes.stream().collect(Collectors.groupingBy(mt -> mt.getMovieTime().getId()));

    List<MovieShowingCinemaResponse> responses = new ArrayList<>();
    for (Map.Entry<Long, List<MovieTime>> entry : moviesGrouped.entrySet()) {
      Long movieId = entry.getKey();
      List<MovieTime> times = entry.getValue();
      String movieName = times.get(0).getMovieTime().getName();
      List<Long> showtime = times.stream().map(MovieTime::getShowtime).collect(Collectors.toList());
      responses.add(
          MovieShowingCinemaResponse.builder()
              .movieId(movieId)
              .movieName(movieName)
              .showtime(showtime)
              .build());
    }
    return BaseResponse.build(responses, true);
  }

  @Override
  public BaseResponse<PaginationResponse<List<MovieResponse>>> getByFilter(MovieCriteria criteria) {
    var movies = movieService.findByFilter(criteria);
    List<MovieResponse> movieResponses =
        movies.getContent().stream().map(this::buildMovieResponse).toList();
    int currentPage = (criteria.getCurrentPage() == null) ? 1 : criteria.getCurrentPage();
    return BaseResponse.build(PaginationResponse.build(movieResponses, movies, currentPage), true);
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
