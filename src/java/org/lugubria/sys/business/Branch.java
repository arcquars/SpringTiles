/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.business;

/**
 *
 * @author angel
 */
public class Branch {

    private Integer branchId;
    private String name;
    private String address;
    private Integer phone;
    private Boolean del;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}
