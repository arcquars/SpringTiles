/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service.facade;

import java.util.List;
import org.lugubria.sys.domain.RecordProductDto;

/**
 *
 * @author angel
 */
public interface IReserveRepository {

    public List<RecordProductDto> getListProductReserve(int branchId);
    
}
