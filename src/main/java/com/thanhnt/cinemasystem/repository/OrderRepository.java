package com.thanhnt.cinemasystem.repository;

import com.thanhnt.cinemasystem.entity.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<TicketOrder, Long> {
  @Query(
      value =
          "select to2.* from ticket_orders to2 where to2.seat_id = :seatId and to2.movie_showtimes = :showtimeId",
      nativeQuery = true)
  TicketOrder findOrderBySeatIdAndShowTimeId(
      @Param("seatId") Long seatId, @Param("showtimeId") Long ShowtimeId);
}
