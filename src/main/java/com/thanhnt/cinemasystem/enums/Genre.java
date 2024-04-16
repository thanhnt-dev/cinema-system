package com.thanhnt.cinemasystem.enums;

public enum Genre {
    SHOWING,
    COMING;

    private boolean isShowing() {return Genre.SHOWING == this;}
    private boolean isComing() {return Genre.COMING == this;}
}
