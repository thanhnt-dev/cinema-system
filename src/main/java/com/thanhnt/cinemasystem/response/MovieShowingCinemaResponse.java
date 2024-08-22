package com.thanhnt.cinemasystem.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MovieShowingCinemaResponse {
  private Long movieId;
  private Long roomId;
  private String roomCode;
  private String movieName;
  private List<ShowTimeResponse> showtimes;
}
