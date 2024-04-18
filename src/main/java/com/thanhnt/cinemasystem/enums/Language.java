package com.thanhnt.cinemasystem.enums;

public enum Language {
  ENGLISH,
  VIETNAMESE;

  public boolean isEnglish() {
    return Language.ENGLISH == this;
  }

  public boolean isVietnamese() {
    return Language.VIETNAMESE == this;
  }
}
