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
@Table(name = "cinemas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Cinema extends BaseEntity implements Serializable {
  @Column(name = "cinema_name", nullable = false)
  private String cinemaName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "province_id", referencedColumnName = "id", nullable = false)
  private Province province;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "district_id", nullable = false, referencedColumnName = "id")
  private District district;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "ward_id", nullable = false, referencedColumnName = "id")
  private Ward ward;

  @OneToMany(mappedBy = "cinemaRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<Room> rooms = new ArrayList<>();

  @OneToMany(mappedBy = "cinemaScreen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<MovieTime> movieTimes = new ArrayList<>();
}
