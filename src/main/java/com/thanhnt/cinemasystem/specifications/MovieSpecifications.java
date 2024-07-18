package com.thanhnt.cinemasystem.specifications;

import com.thanhnt.cinemasystem.entity.Movie;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecifications {
  public static Specification<Movie> baseSpecification() {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isActive"), true);
  }

  public static Specification<Movie> filterByName(String name) {
    return (root, query, criteriaBuilder) -> {
      if (name == null || name.trim().isEmpty()) {
        return null;
      }
      return criteriaBuilder.like(root.get("name"), "%" + name + "%");
    };
  }
}
