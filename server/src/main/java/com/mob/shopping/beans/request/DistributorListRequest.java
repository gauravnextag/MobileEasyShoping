package com.mob.shopping.beans.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DistributorListRequest {

    private Long districtId;

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("districtId", districtId)
                .toString();
    }
}
