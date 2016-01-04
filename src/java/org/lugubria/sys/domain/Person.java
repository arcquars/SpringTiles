/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.domain;

import java.io.Serializable;

/**
 *
 * @author angel
 */
public class Person implements Serializable{

     private int perId;
     private String names;
     private String firstname;
     private String lastname;
     private int ci;
     private String address;
     private int phoneAddress;
     private int phoneMobil;
     private String email;
     private boolean del;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public int getPerId() {
        return perId;
    }

    public void setPerId(int perId) {
        this.perId = perId;
    }

    public int getPhoneAddress() {
        return phoneAddress;
    }

    public void setPhoneAddress(int phoneAddress) {
        this.phoneAddress = phoneAddress;
    }

    public int getPhoneMobil() {
        return phoneMobil;
    }

    public void setPhoneMobil(int phoneMobil) {
        this.phoneMobil = phoneMobil;
    }


}
