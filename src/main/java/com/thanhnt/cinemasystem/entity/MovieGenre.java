package com.thanhnt.cinemasystem.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "movie_genres")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MovieGenre extends BaseEntity implements Serializable {
  @Column(name = "genre", nullable = false)
  private String genre;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  List<Movie> movies = new ArrayList<>();
}
