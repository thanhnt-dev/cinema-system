package com.thanhnt.cinemasystem.enums;

public enum RoomType {
    VIP,
    TWOD,
    THREED;

    public boolean isVip() {return RoomType.VIP == this;}

    public boolean isTwod() {return RoomType.TWOD == this;}

    public boolean isThreed() {return RoomType.THREED == this;}
}

