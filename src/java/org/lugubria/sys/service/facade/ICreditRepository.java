/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service.facade;

import java.util.List;
import org.lugubria.sys.domain.CreditDto;
import org.lugubria.sys.domain.ListCreditDetailDto;
import org.lugubria.sys.domain.dto.credit.ListPaymentDto;

/**
 *
 * @author angel
 */
public interface ICreditRepository {
    
    public List<CreditDto> getCreditByProvider(int providerId);
    
    public boolean savePayment(int creditId, double payment);
    
    public ListCreditDetailDto getListCreditByCriteria(int providerId, int buyCancel, String dateStart, String dateEnd);
    
    public ListPaymentDto getListPaymentByCredit(int creditId);
    
}
