package com.thanhnt.cinemasystem.facade.impl;

import com.thanhnt.cinemasystem.entity.Movie;
import com.thanhnt.cinemasystem.entity.MovieGenre;
import com.thanhnt.cinemasystem.entity.MovieTime;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.MovieException;
import com.thanhnt.cinemasystem.exception.MovieGenreException;
import com.thanhnt.cinemasystem.facade.MovieFacade;
import com.thanhnt.cinemasystem.request.MovieCriteria;
import com.thanhnt.cinemasystem.request.UpsertMovieRequest;
import com.thanhnt.cinemasystem.response.*;
import com.thanhnt.cinemasystem.service.CloudinaryService;
import com.thanhnt.cinemasystem.service.MovieGenreService;
import com.thanhnt.cinemasystem.service.MovieService;
import com.thanhnt.cinemasystem.service.MovieTimeService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MovieFacadeImpl implements MovieFacade {

  private final MovieService movieService;
  private final MovieTimeService movieTimeService;
  private final MovieGenreService movieGenreService;
  private final CloudinaryService cloudinaryService;

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
    Movie movie =
        movieService.findById(id).orElseThrow(() -> new MovieException(ErrorCode.MOVIE_NOT_FOUND));
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
            .origin(movie.getOrigin())
            .image(movie.getImage())
            .trailer(movie.getTrailer())
            .releaseDate(movie.getReleaseDate())
            .endDate(movie.getEnd_date())
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
      Long roomId = times.get(0).getRoomScreen().getId();
      String roomCode = times.get(0).getRoomScreen().getRoomCode();
      List<ShowTimeResponse> showtime =
          times.stream()
              .map(mt -> ShowTimeResponse.builder().id(mt.getId()).time(mt.getShowtime()).build())
              .toList();
      responses.add(
          MovieShowingCinemaResponse.builder()
              .movieId(movieId)
              .roomId(roomId)
              .roomCode(roomCode)
              .movieName(movieName)
              .showtimes(showtime)
              .build());
    }
    return BaseResponse.build(responses, true);
  }

  @Override
  public BaseResponse<List<MovieScheduleResponse>> getMovieSchedules(Long id) {
    List<MovieTime> movieTimes = movieTimeService.findMovieTimeByMovieId(id);
    Map<Long, List<MovieTime>> cinemaGrouped =
        movieTimes.stream().collect(Collectors.groupingBy(mt -> mt.getCinemaScreen().getId()));
    List<MovieScheduleResponse> responses = new ArrayList<>();
    for (Map.Entry<Long, List<MovieTime>> entry : cinemaGrouped.entrySet()) {
      Long cinemaId = entry.getKey();
      List<MovieTime> times = entry.getValue();

      String cinemaName = times.get(0).getCinemaScreen().getCinemaName();
      String cinemaAddress =
          times.get(0).getCinemaScreen().getProvince().getProvinceName()
              + " - "
              + times.get(0).getCinemaScreen().getDistrict().getDistrictName()
              + " - "
              + times.get(0).getCinemaScreen().getWard().getWardName();

      String movieName = times.get(0).getMovieTime().getName();
      Long roomId = times.get(0).getRoomScreen().getId();
      String roomCode = times.get(0).getRoomScreen().getRoomCode();

      List<ShowTimeResponse> showtimes =
          times.stream()
              .map(mt -> ShowTimeResponse.builder().id(mt.getId()).time(mt.getShowtime()).build())
              .toList();

      responses.add(
          MovieScheduleResponse.builder()
              .movieName(movieName)
              .movieId(id)
              .cinemaId(cinemaId)
              .cinemaAddress(cinemaAddress)
              .cinemaName(cinemaName)
              .roomId(roomId)
              .roomCode(roomCode)
              .showTimeResponse(showtimes)
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

  @SneakyThrows
  @Override
  @Transactional
  public BaseResponse<Void> createMovie(UpsertMovieRequest request) {
    String imageMovie =
        "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/832px-No-Image-Placeholder.svg.png";
    MovieGenre movieGenre =
        movieGenreService
            .findById(request.getGenre())
            .orElseThrow(() -> new MovieGenreException(ErrorCode.MOVIE_GENRE_NOT_FOUNT));
    Movie movie =
        Movie.builder()
            .name(request.getName())
            .image(imageMovie)
            .cast(request.getCast())
            .director(request.getDirector())
            .releaseDate(request.getReleaseDate())
            .end_date(request.getEndDate())
            .duration(request.getDuration())
            .description(request.getDescription())
            .origin(request.getLanguage())
            .trailer(request.getTrailer())
            .ageRated(request.getAge())
            .genre(movieGenre)
            .build();

    movieService.saveMovie(movie);
    return BaseResponse.ok();
  }

  @Override
  @Transactional
  public BaseResponse<Void> updateMovie(UpsertMovieRequest request) {
    Movie movie =
        movieService
            .findById(request.getId())
            .orElseThrow(() -> new MovieException(ErrorCode.MOVIE_NOT_FOUND));
    movie.updateInfo(
        request.getName(),
        request.getDirector(),
        request.getCast(),
        request.getReleaseDate(),
        request.getEndDate(),
        request.getDuration(),
        request.getLanguage(),
        request.getAge(),
        request.getDescription(),
        request.getTrailer());
    movieService.saveMovie(movie);
    return BaseResponse.ok();
  }

  @Override
  @SneakyThrows
  @Transactional
  public BaseResponse<Void> upsertImage(Long id, MultipartFile image) {
    Movie movie =
        movieService.findById(id).orElseThrow(() -> new MovieException(ErrorCode.MOVIE_NOT_FOUND));
    String imageMovie = cloudinaryService.uploadImage(image.getBytes());
    movie.upsertImage(imageMovie);
    movieService.saveMovie(movie);
    return BaseResponse.ok();
  }

  @Override
  @Transactional
  public BaseResponse<Void> deleteMovie(Long id) {
    Movie movie =
        movieService.findById(id).orElseThrow(() -> new MovieException(ErrorCode.MOVIE_NOT_FOUND));
    movieService.deactivateMovie(movie.getId());
    return BaseResponse.ok();
  }

  private MovieResponse buildMovieResponse(Movie movie) {
    return MovieResponse.builder()
        .id(movie.getId())
        .name(movie.getName())
        .genre(movie.getGenre().getGenre())
        .image(movie.getImage())
        .duration(movie.getDuration())
        .releaseDate(movie.getReleaseDate())
        .build();
  }
}
