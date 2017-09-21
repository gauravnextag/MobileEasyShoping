package com.mob.shopping.beans.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RegistrationRequest {

    private Long distributorId;
    private Long districtId;
    private String storeName;
    private String msisdn;


    public Long getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Long distributorId) {
        this.distributorId = distributorId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("distributorId", distributorId)
                .append("districtId", districtId)
                .append("storeName", storeName)
                .append("msisdn", msisdn)
                .toString();
    }
}
