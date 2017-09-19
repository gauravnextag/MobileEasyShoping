package com.mob.shopping.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by B0097545 on 9/4/17.
 */
@Entity
@Table(name = "BANK_TXN_LOG")
public class Customer implements Serializable {

    @Id
    @Column(name = "TXN_ID")
    private String txnId;
    @Column(name = "MOB_TXN_ID")
    private String mobTxnId;

    @Column(name = "MSISDN")
    private String msisdn;

    @Column(name = "CAF_NUMBER")
    private String caf;

    @Column(name = "APP_NAME")
    private String appName;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "TXN_DATE")
    private Date txnDate;

    @Column(name = "SNAP_DATE")
    private Date snapDate;

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getMobTxnId() {
        return mobTxnId;
    }

    public void setMobTxnId(String mobTxnId) {
        this.mobTxnId = mobTxnId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getCaf() {
        return caf;
    }

    public void setCaf(String caf) {
        this.caf = caf;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }

    public Date getSnapDate() {
        return snapDate;
    }

    public void setSnapDate(Date snapDate) {
        this.snapDate = snapDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("txnId", txnId)
                .append("mobTxnId", mobTxnId)
                .append("msisdn", msisdn)
                .append("caf", caf)
                .append("appName", appName)
                .append("status", status)
                .append("message", message)
                .append("txnDate", txnDate)
                .append("snapDate", snapDate)
                .toString();
    }
}
