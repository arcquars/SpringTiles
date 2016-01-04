/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.form;

import javax.validation.constraints.Min;

/**
 *
 * @author angel
 */
public class FormReserve {
    
    @Min(-2)
    private int branchId;
    
    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

}
