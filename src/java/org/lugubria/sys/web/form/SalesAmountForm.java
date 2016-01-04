/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.form;

import java.util.Date;

/**
 *
 * @author angel
 */
public class SalesAmountForm {

    private int userId;
    private int branchId;
    private String branchName;
    private double totalSale;
    private double totalSaleCredit;
    private double totalReserveClose;
    private double totalReserveOpen;
    private double amount;
    private String dateStart;
    private String dateEnd;

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public double getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(double totalSale) {
        this.totalSale = totalSale;
    }

    public double getTotalReserveClose() {
        return totalReserveClose;
    }

    public void setTotalReserveClose(double totalReserveClose) {
        this.totalReserveClose = totalReserveClose;
    }

    public double getTotalReserveOpen() {
        return totalReserveOpen;
    }

    public void setTotalReserveOpen(double totalReserveOpen) {
        this.totalReserveOpen = totalReserveOpen;
    }

    public double getTotalSaleCredit() {
        return totalSaleCredit;
    }

    public void setTotalSaleCredit(double totalSaleCredit) {
        this.totalSaleCredit = totalSaleCredit;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
