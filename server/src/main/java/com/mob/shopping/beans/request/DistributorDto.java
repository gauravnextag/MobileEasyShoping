/**
 * @Ajay Mishra
 */
package com.mob.shopping.beans.request;

import java.io.Serializable;

public class DistributorDto implements Serializable {
	
	private static final long serialVersionUID = -9133339196011025006L;

	private Long id;
	private String name;
	private String msisdn;
	private String srdMdName;

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
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getSrdMdName() {
		return srdMdName;
	}
	public void setSrdMdName(String srdMdName) {
		this.srdMdName = srdMdName;
	}
	@Override
	public String toString() {
		return "DistributorDto [id=" + id + ", name=" + name + ", msisdn=" + msisdn + ", srdMdName=" + srdMdName + "]";
	}

	
	
	
}
