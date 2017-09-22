/**
 * @Ajay Mishra
 */
package com.mob.shopping.beans.request;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserDto implements  Serializable{
	
	private static final long serialVersionUID = 2629344903490504736L;

	private Long id;

	private String msisdn;
	
	private Integer userType;
	
	private Long userId;

	private int isDeleted;

	private Timestamp createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
		return "UserDto [id=" + id + ", msisdn=" + msisdn + ", userType=" + userType + ", userId=" + userId
				+ ", isDeleted=" + isDeleted + ", createdDate=" + createdDate + "]";
	}

	

	

	
	
	
}
