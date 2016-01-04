/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service.facade;

import java.util.List;
import org.lugubria.sys.business.Branch;
import org.lugubria.sys.business.Category;
import org.lugubria.sys.domain.KeyValue;

/**
 *
 * @author angel
 */
public interface IBranchRepository {

    public List<Branch> searchByCriteria(String criteria);
    
    public boolean branchDelete(int catId);
    
    public boolean branchUpdate(Branch branch);
    
    public boolean branchCreate(Branch branch);
    
    public boolean branchValidateName(String newName, int branchId);
    
    public boolean branchValidateName(String newName);
    
    public List<KeyValue> getAllBranch();
    
    public List<KeyValue> getAllBranchWithMain();
}
