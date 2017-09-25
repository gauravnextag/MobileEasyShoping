/**
 * @Ajay Mishra
 */
package com.mob.shopping.beans.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class RetailerDto implements Serializable{
	
	private static final long serialVersionUID = 5161951753589611610L;

    private Long id;

    private Long districtId;

    private Long distributorId;

    private Integer registrationStatus ;

    private String storeName;

    private String msisdn;

    private String gstNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Long distributorId) {
        this.distributorId = distributorId;
    }

    public Integer getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(Integer registrationStatus) {
        this.registrationStatus = registrationStatus;
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

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("storeName", storeName)
                .append("districtId", districtId)
                .append("distributorId", distributorId)
                .append("registrationStatus", registrationStatus)
                .append("storeName", storeName)
                .append("msisdn", msisdn)
                .toString();
    }
}
