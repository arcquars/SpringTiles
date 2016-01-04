/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.form;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author angel
 */
public class EmployeeForm {

    // beans Person
    private int perId;
    private boolean del;
    
    @NotEmpty
    @Size(min = 3, max = 100)
    private String names;
    @NotEmpty
    @Size(min = 1, max = 40)
    private String firstname;
    @NotEmpty
    @Size(min = 1, max = 40)
    private String lastname;
    @Min(10000)
    @Max(99999999)
    private int ci;
    @NotEmpty
    @Size(min = 1, max = 40)
    private String address;
    @Min(1000000)
    @Max(9999999)
    private int phone_address;
    @Min(10000000)
    @Max(99999990)
    private int phone_mobil;
    @Pattern(regexp = ".+@.+\\.[a-z]+")
    private String email;

    // employee
    private String position;
    // branch
    private int branch;

    public int getBranch() {
        return branch;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
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


    public int getPhone_address() {
        return phone_address;
    }

    public void setPhone_address(int phone_address) {
        this.phone_address = phone_address;
    }

    public int getPhone_mobil() {
        return phone_mobil;
    }

    public void setPhone_mobil(int phone_mobil) {
        this.phone_mobil = phone_mobil;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public int getPerId() {
        return perId;
    }

    public void setPerId(int perId) {
        this.perId = perId;
    }

    public void px(){
        System.out.println("address: "+this.address);
        System.out.println("email: "+this.email);
        System.out.println("firtsname: "+this.firstname);
        System.out.println("lastname: "+this.lastname);
        System.out.println("names: "+this.names);
        System.out.println("position: "+this.position);
        System.out.println("branch: "+this.branch);
        System.out.println("ci: "+this.ci);
        System.out.println("del:"+this.del);
        System.out.println("perId:"+this.perId);
        System.out.println("phone_address: "+this.phone_address);
        System.out.println("phone_mobil: "+this.phone_mobil);
        
    }
}
