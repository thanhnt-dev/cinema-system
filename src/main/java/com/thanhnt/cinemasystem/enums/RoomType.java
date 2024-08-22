package com.thanhnt.cinemasystem.enums;

public enum RoomType {
  VIP,
  STANDARD,
  PREMIUM;

  public boolean isVip() {
    return RoomType.VIP == this;
  }

  public boolean isStandard() {
    return RoomType.STANDARD == this;
  }

  public boolean isPremium() {
    return RoomType.PREMIUM == this;
  }
}
