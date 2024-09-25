package com.thanhnt.cinemasystem.repository;

import com.thanhnt.cinemasystem.entity.Movie;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MovieRepository
    extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

  @Query(
      value =
          "select m.* from movies m where m.end_date > EXTRACT(EPOCH FROM NOW()) * 1000 and m.is_active = true ",
      nativeQuery = true)
  List<Movie> findMovieIsShowing();

  @Query(
      value =
          "select m.* from movies m where m.release_date > EXTRACT(EPOCH FROM NOW()) * 1000 and m.is_active = true ",
      nativeQuery = true)
  List<Movie> findMovieIsComing();

  @Modifying
  @Transactional
  @Query(value = "update movies m set is_active = false where m.id  = :id", nativeQuery = true)
  void deactivateMovieById(@Param("id") Long id);
}
