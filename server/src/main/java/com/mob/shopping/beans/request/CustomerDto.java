/**
 * @Ajay Mishra
 */
package com.mob.shopping.beans.request;

import java.io.Serializable;
import java.sql.Timestamp;

public class CustomerDto implements Serializable {

	private static final long serialVersionUID = 6113816439608799941L;

	private Long id;

    private String name;

    private Long retailerId;

    private String msisdn;

    private Integer deliveryStatus ;

    private Integer noOfDevices;
    
    private String otp;

    private int isDeleted;

    private Timestamp createdDate;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(Long retailerId) {
		this.retailerId = retailerId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Integer getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(Integer deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Integer getNoOfDevices() {
		return noOfDevices;
	}

	public void setNoOfDevices(Integer noOfDevices) {
		this.noOfDevices = noOfDevices;
	}
	
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "CustomerDto [id=" + id + ", name=" + name + ", retailerId=" + retailerId + ", msisdn=" + msisdn
				+ ", deliveryStatus=" + deliveryStatus + ", noOfDevices=" + noOfDevices + ", otp=" + otp
				+ ", isDeleted=" + isDeleted + ", createdDate=" + createdDate + "]";
	}

	
	

	
    
}
