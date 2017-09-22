/**
 * @Ajay Mishra
 */
package com.mob.shopping.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

	private static final long serialVersionUID = -7117846981590659564L;

	@Id
	@SequenceGenerator(name = "USERS_SEQ", sequenceName = "USERS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
	@Column(name = "ID")
	private Long id;

	@Column(name = "MSISDN")
	private String msisdn;

	@Column(name = "USER_TYPE")
	private Integer userType;
	
	@Column(name = "USER_ID")
	private Long userId;
	
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

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", msisdn=" + msisdn + ", userType=" + userType + ", userId=" + userId
				+ ", isDeleted=" + isDeleted + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate
				+ "]";
	}


	
}
