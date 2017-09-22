package com.mob.shopping.constants.enums;

public enum UserType {
	DISTRIBUTOR(0), RETAILER(1),CUSTOMER(2);

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

    public static String getName(int value){
        return  UserType.values()[value].toString();
    }
}

