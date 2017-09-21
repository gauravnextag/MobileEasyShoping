package com.mob.shopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by B0097545 on 9/4/17.
 */
@Entity
@Table(name = "DISTRIBUTOR")
public class Distributor implements Serializable {

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
                .append("districtId", districtId)
                .append("msisdn", msisdn)
                .append("address", address)
                .append("isDeleted", isDeleted)
                .append("createdDate", createdDate)
                .append("lastModifiedDate", lastModifiedDate)
                .toString();
    }
}
