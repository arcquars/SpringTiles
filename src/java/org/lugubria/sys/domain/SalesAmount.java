package org.lugubria.sys.domain;
// Generated Jun 6, 2012 7:57:45 AM by Hibernate Tools 3.2.1.GA

import java.util.Date;




/**
 * Provider generated by hbm2java
 */
public class SalesAmount  implements java.io.Serializable {


     private Integer id;
     private Integer userId;
     private Integer branchId;
     private String dateStart;
     private String dateEnd;
     private Double amount;
     private Boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
    
    
}