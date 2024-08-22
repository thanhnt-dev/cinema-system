package com.thanhnt.cinemasystem.enums;

public enum PaymentStatus {
    PENDING,
    SUCCESS,
    FAIL;

    public boolean isPending() {
        return PaymentStatus.PENDING == this;
    }

    public boolean isSuccess() {
        return PaymentStatus.SUCCESS == this;
    }

    public boolean isFail() {
        return PaymentStatus.FAIL == this;
    }

}
