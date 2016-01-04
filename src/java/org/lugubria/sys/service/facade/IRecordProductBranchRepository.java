/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service.facade;

import java.util.List;
import org.lugubria.sys.domain.RecordProductBranchDto;

/**
 *
 * @author angel
 */
public interface IRecordProductBranchRepository {
    
    public List<RecordProductBranchDto> getListRpbByBranch(int branchId, int type, String criteria);
    
    public boolean validDeleteByBranchId(int branchId);
}
