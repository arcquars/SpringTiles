package org.lugubria.sys.domain;
// Generated Jun 9, 2012 6:41:24 PM by Hibernate Tools 3.2.1.GA



/**
 * Factory generated by hbm2java
 */
public class Factory  implements java.io.Serializable {


     private Integer factoryId;
     private String name;
     private String origin;
     private boolean del;

    public Factory() {
    }

	
    public Factory(String name, boolean del) {
        this.name = name;
        this.del = del;
    }
    public Factory(String name, String origin, boolean del) {
       this.name = name;
       this.origin = origin;
       this.del = del;
    }
   
    public Integer getFactoryId() {
        return this.factoryId;
    }
    
    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getOrigin() {
        return this.origin;
    }
    
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public boolean isDel() {
        return this.del;
    }
    
    public void setDel(boolean del) {
        this.del = del;
    }




}


