package com.mob.shopping.beans.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DistrictRequest {

    private String stateId;

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("stateId", stateId)
                .toString();
    }
}
