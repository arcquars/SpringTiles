/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service.facade;

import org.lugubria.sys.domain.ListProductRefund;

/**
 *
 * @author angel
 */
public interface IDetailRefundRepository {
    
    public ListProductRefund getListByIdsAndBranchId(String ids, int branchId);
    
    public boolean saveListProductRefund(ListProductRefund lpr);
    
}
