package com.mob.shopping.constants.enums;

public enum DeliveryStatus {
    PENDING(0) , SUCCESS(1) ,FAIL(2);

    Integer value;
    DeliveryStatus(Integer s) {
        this.value = s;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

