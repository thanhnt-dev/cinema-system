package com.thanhnt.cinemasystem.repository;

import com.thanhnt.cinemasystem.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {}
