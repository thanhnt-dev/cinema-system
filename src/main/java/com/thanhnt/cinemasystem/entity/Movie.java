package com.thanhnt.cinemasystem.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Movie extends BaseEntity implements Serializable {
  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Column(name = "image", nullable = false)
  private String image;

  @Column(name = "director", nullable = false)
  private String director;

  @Column(name = "casts", nullable = false)
  private String cast;

  @Column(name = "release_date", nullable = false)
  private Long releaseDate;

  @Column(name = "end_date", nullable = false)
  private Long end_date;

  @Column(name = "duration", nullable = false)
  private int duration;

  @Column(name = "language", nullable = false)
  private String origin;

  @Column(name = "age_rated", nullable = false)
  private int ageRated;

  @Column(name = "description")
  private String description;

  @Column(name = "trailer")
  private String trailer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "genre_id", referencedColumnName = "id")
  private MovieGenre genre;

  @OneToMany(mappedBy = "movieTime", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<MovieTime> movieTimes = new ArrayList<>();

  public void updateInfo(
      String name,
      String director,
      String cast,
      Long releaseDate,
      Long end_date,
      int duration,
      String origin,
      int ageRated,
      String description,
      String trailer) {
    if (name != null) this.name = name;
    if (director != null) this.director = director;
    if (cast != null) this.cast = cast;
    if (releaseDate != null) this.releaseDate = releaseDate;
    if (end_date != null) this.end_date = end_date;
    if (duration > 0) this.duration = duration;
    if (origin != null) this.origin = origin;
    if (ageRated > 0) this.ageRated = ageRated;
    if (description != null) this.description = description;
    if (trailer != null) this.trailer = trailer;
  }

  public void upsertImage(String image) {
    this.image = image;
  }
}
