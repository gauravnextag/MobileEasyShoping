/**
 * @Ajay Mishra
 */
package com.mob.shopping.beans.request;

import java.io.Serializable;

public class RetailerDto implements Serializable{
	
	private static final long serialVersionUID = 5161951753589611610L;

		private Long id;

	    private String store_name;

	    private Long districtId;

	    private Long distributorID;
	    
	    private Integer registrationStatus ;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getStore_name() {
			return store_name;
		}

		public void setStore_name(String store_name) {
			this.store_name = store_name;
		}

		public Long getDistrictId() {
			return districtId;
		}

		public void setDistrictId(Long districtId) {
			this.districtId = districtId;
		}

		public Long getDistributorID() {
			return distributorID;
		}

		public void setDistributorID(Long distributorID) {
			this.distributorID = distributorID;
		}

		
		public Integer getRegistrationStatus() {
			return registrationStatus;
		}

		public void setRegistrationStatus(Integer registrationStatus) {
			this.registrationStatus = registrationStatus;
		}

		@Override
		public String toString() {
			return "RetailerDto [id=" + id + ", store_name=" + store_name + ", districtId=" + districtId
					+ ", distributorID=" + distributorID + ", registrationStatus=" + registrationStatus + "]";
		}

	    

	    
}
