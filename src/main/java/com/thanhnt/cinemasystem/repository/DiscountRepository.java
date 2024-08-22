package com.thanhnt.cinemasystem.repository;

import com.thanhnt.cinemasystem.entity.Discount;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

  @Query(
      value =
          "select d.* from discounts d where d.discount_code = :discount_code and d.expiration_date > EXTRACT(EPOCH FROM NOW()) * 1000 and d.quantity > 0",
      nativeQuery = true)
  Optional<Discount> findByCode(@Param("discount_code") String discount_code);
}
