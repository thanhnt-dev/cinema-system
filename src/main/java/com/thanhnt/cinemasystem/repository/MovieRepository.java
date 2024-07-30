package com.thanhnt.cinemasystem.repository;

import com.thanhnt.cinemasystem.entity.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository
    extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

  @Query(
      value =
          "select m.* from movies m where m.premiere <= EXTRACT(EPOCH FROM NOW()) * 1000 and m.is_active = true ",
      nativeQuery = true)
  List<Movie> findMovieIsShowing();

  @Query(
      value =
          "select m.* from movies m where m.premiere > EXTRACT(EPOCH FROM NOW()) * 1000 and m.is_active = true ",
      nativeQuery = true)
  List<Movie> findMovieIsComing();
}
