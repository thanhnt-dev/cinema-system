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
@Table(name = "movie_time")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class MovieTime extends BaseEntity implements Serializable {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
  private Movie movieTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cinema_id", referencedColumnName = "id", nullable = false)
  private Cinema cinemaScreen;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
  private Room roomScreen;

  @Column(name = "showtime", nullable = false)
  private Long showtime;

  @OneToMany(mappedBy = "movieTime", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<TicketOrder> orders = new ArrayList<>();
}
