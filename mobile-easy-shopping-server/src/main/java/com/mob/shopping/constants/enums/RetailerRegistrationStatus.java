package com.mob.shopping.constants.enums;

public enum RetailerRegistrationStatus {
    PENDING(1) , SUCCESS(0) ,FAIL(1);

    Integer value;
    RetailerRegistrationStatus(Integer s) {
        this.value = s;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

