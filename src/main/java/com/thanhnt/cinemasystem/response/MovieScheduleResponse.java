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
public class MovieScheduleResponse {
  private Long movieId;
  private Long cinemaId;
  private String movieName;
  private String cinemaName;
  private String cinemaAddress;
  private Long roomId;
  private String roomCode;
  private List<ShowTimeResponse> showTimeResponse;
}
