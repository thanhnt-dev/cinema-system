package com.thanhnt.cinemasystem.repository;

import com.thanhnt.cinemasystem.entity.MovieTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieTimeRepository extends JpaRepository<MovieTime, Long> {

  @Query(
      value =
          "select mt.* from movie_time mt where mt.showtime >= EXTRACT(EPOCH FROM NOW()) * 1000 and mt.cinema_id = :id ",
      nativeQuery = true)
  List<MovieTime> findByCinemaScreen(@Param("id") Long id);
}
