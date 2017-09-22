package com.mob.shopping.constants.enums;

public enum UserType {
    RETAILER(1) , DISTRIBUTOR(0) ,CUSTOMER(2);

    Integer value;
    UserType(Integer s) {
        this.value = s;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

