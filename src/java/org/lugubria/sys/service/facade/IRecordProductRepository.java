/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service.facade;

import java.util.List;
import org.lugubria.sys.domain.RecordProductDto;
import org.lugubria.sys.domain.RecordProductList;

/**
 *
 * @author angel
 */
public interface IRecordProductRepository {

    public RecordProductList getListByCriteria(int type, String criteria);
    
    public List<RecordProductDto> getListByCriteriaPrint(int type, String criteria);
}
