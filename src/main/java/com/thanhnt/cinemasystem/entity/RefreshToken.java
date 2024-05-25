package com.thanhnt.cinemasystem.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "refresh_tokens")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class RefreshToken extends BaseEntity implements Serializable {
  @Column(name = "refresh_token", nullable = false)
  private String refreshToken;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  public void updateNewRefreshToken(String token) {
    this.refreshToken = token;
  }
}
