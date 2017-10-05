/**
 * @Ajay Mishra
 */
package com.mob.shopping.beans.request;

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
    
    private String address;
    

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
    
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "RetailerDto [id=" + id + ", districtId=" + districtId + ", distributorId=" + distributorId
				+ ", registrationStatus=" + registrationStatus + ", storeName=" + storeName + ", msisdn=" + msisdn
				+ ", gstNumber=" + gstNumber + ", address=" + address + "]";
	}


}
