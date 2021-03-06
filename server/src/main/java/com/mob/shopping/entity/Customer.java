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

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by B0097545 on 9/4/17.
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

	private static final long serialVersionUID = -269657010500629864L;

	@Id
    @SequenceGenerator(name = "CUSTOMER_SEQ", sequenceName = "CUSTOMER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "RETAILER_ID")
    private Long retailerID;

    @Column(name = "MSISDN")
    private String msisdn;

    @Column(name = "DELIVERY_STATUS")
    private Integer deliveryStatus ;

    @Column(name = "NO_OF_DEVICES")
    private Integer noOfDevices;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRetailerID() {
        return retailerID;
    }

    public void setRetailerID(Long retailerID) {
        this.retailerID = retailerID;
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
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("retailerID", retailerID)
                .append("msisdn", msisdn)
                .append("deliveryStatus", deliveryStatus)
                .append("noOfDevices", noOfDevices)
                .append("isDeleted", isDeleted)
                .append("createdDate", createdDate)
                .append("lastModifiedDate", lastModifiedDate)
                .toString();
    }
}
