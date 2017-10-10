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
@Table(name = "DISTRIBUTOR")
public class Distributor implements Serializable {

	private static final long serialVersionUID = 1214811411152820677L;

	@Id
	@SequenceGenerator(name = "DISTRIBUTOR_SEQ", sequenceName = "DISTRIBUTOR_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISTRIBUTOR_SEQ")
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DISTRICT_ID")
	private Long districtId;

	@Column(name = "MSISDN")
	private String msisdn;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "REGION")
	private String region;

	@Column(name = "CITY")
	private String city;

	@Column(name = "SRD_MD_ID")
	private String srdMdId;

	@Column(name = "SRD_MD_TYPE")
	private String srdMdType;

	@Column(name = "SRD_MD_NAME")
	private String srdMdName;

	@Column(name = "EMAIL")
	private String email;

	@JsonIgnore
	@Column(name = "IS_DELETED")
	private int isDeleted;

	@JsonIgnore
	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@JsonIgnore
	@Column(name = "LAST_MODIFIED_DATE")
	private Timestamp lastModifiedDate;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSrdMdId() {
		return srdMdId;
	}

	public void setSrdMdId(String srdMdId) {
		this.srdMdId = srdMdId;
	}

	public String getSrdMdType() {
		return srdMdType;
	}

	public void setSrdMdType(String srdMdType) {
		this.srdMdType = srdMdType;
	}

	public String getSrdMdName() {
		return srdMdName;
	}

	public void setSrdMdName(String srdMdName) {
		this.srdMdName = srdMdName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public String toString() {
		return "Distributor [id=" + id + ", name=" + name + ", districtId=" + districtId + ", msisdn=" + msisdn
				+ ", address=" + address + ", region=" + region + ", city=" + city + ", srdMdId=" + srdMdId
				+ ", srdMdType=" + srdMdType + ", srdMdName=" + srdMdName + ", email=" + email + ", isDeleted="
				+ isDeleted + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + ", getId()="
				+ getId() + ", getName()=" + getName() + ", getDistrictId()=" + getDistrictId() + ", getMsisdn()="
				+ getMsisdn() + ", getAddress()=" + getAddress() + ", getRegion()=" + getRegion() + ", getCity()="
				+ getCity() + ", getSrdMdId()=" + getSrdMdId() + ", getSrdMdType()=" + getSrdMdType()
				+ ", getSrdMdName()=" + getSrdMdName() + ", getEmail()=" + getEmail() + ", getIsDeleted()="
				+ getIsDeleted() + ", getCreatedDate()=" + getCreatedDate() + ", getLastModifiedDate()="
				+ getLastModifiedDate() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	
}
