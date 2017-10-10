package com.mob.shopping.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by B0097545 on 9/4/17.
 */
@Entity
@Table(name = "RETAILER")
public class Retailer implements Serializable {

	private static final long serialVersionUID = 4757618707103096267L;

	@Id
    @SequenceGenerator(name = "RETAILER_SEQ", sequenceName = "RETAILER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RETAILER_SEQ")

    @Column(name = "ID")
    private Long id;

    @Column(name = "STORE_NAME")
    private String storeName;

    @Column(name = "DISTRICT_ID")
    private Long districtId;

    @Column(name = "DISTRIBUTOR_ID")
    private Long distributorId;

    @Column(name = "MSISDN")
    private String msisdn;

    @Column(name = "REGISTRATION_STATUS")
    private Integer registrationStatus ;

    @Column(name = "GST_NUMBER")
    private String gstNumber ;
    
    @Column(name = "ADDRESS")
    private String address;
    
    @Column(name = "LAPU_NUMBER")
    private String lapuNumber;

    @JsonIgnore
    @Column(name = "IS_DELETED")
    private int isDeleted;

    @JsonIgnore
    @Column(name="CREATED_DATE")
    private Timestamp createdDate;

    @JsonIgnore
    @Column(name="LAST_MODIFIED_DATE")
    private Timestamp lastModifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Integer getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(Integer registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Long distributorId) {
        this.distributorId = distributorId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }


    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
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

	public String getLapuNumber() {
		return lapuNumber;
	}

	public void setLapuNumber(String lapuNumber) {
		this.lapuNumber = lapuNumber;
	}

	public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

	@Override
	public String toString() {
		return "Retailer [id=" + id + ", storeName=" + storeName + ", districtId=" + districtId + ", distributorId="
				+ distributorId + ", msisdn=" + msisdn + ", registrationStatus=" + registrationStatus + ", gstNumber="
				+ gstNumber + ", address=" + address + ", lapuNumber=" + lapuNumber + ", isDeleted=" + isDeleted
				+ ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + "]";
	}
	
	


}
