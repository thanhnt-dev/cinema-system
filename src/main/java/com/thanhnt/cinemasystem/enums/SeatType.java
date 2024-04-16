package com.thanhnt.cinemasystem.enums;

public enum SeatType {
    VIP,
    NORMAL,
    COUPLE;

    public boolean isVip() {return SeatType.VIP == this;}

    public boolean isNormal() {return SeatType.NORMAL == this;}

    public boolean isCouple() {return SeatType.COUPLE == this;}
}
