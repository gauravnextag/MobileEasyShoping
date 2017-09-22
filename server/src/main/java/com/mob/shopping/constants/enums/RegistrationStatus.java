package com.mob.shopping.constants.enums;

public enum RegistrationStatus {
    PENDING(0) , APPROVED(1) ,REJECTED(2);

    Integer value;
    RegistrationStatus(Integer s) {
        this.value = s;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

