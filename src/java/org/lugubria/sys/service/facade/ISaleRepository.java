/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service.facade;

import java.util.HashMap;
import java.util.List;
import org.lugubria.sys.domain.KeyValue;
import org.lugubria.sys.domain.ListProductSale;
import org.lugubria.sys.domain.ListSaleDetailGain;
import org.lugubria.sys.domain.ListSalesAccount;
import org.lugubria.sys.domain.ProductReserve;
import org.lugubria.sys.domain.ReserveDetail;
import org.lugubria.sys.domain.ReserveDto;
import org.lugubria.sys.domain.SalesAmount;

/**
 *
 * @author angel
 */
public interface ISaleRepository {
 
    public ListProductSale getListByProductIdsAndBranchId(String ids, int branchId);
    
    public ProductReserve getListReserveByProductIdsAndBranchId(String ids, int branchId);
    
    public String saveListproductSale(ListProductSale lps);
    
    public ListSaleDetailGain getSaleDetail(int branchId, String dateStart, String dateEnd);
    
    public ListSalesAccount getListSaleAccount(int branchId, String dateStart, String dateEnd);
    
    public ListSalesAccount getSaleAccount(int branchId, String dateStart, String dateEnd);
    
    public boolean saveListproductReserve(ProductReserve pr);
    
    public List<ReserveDto> getListReserve(int branchId, String dateReserve, boolean all);
    
    public ReserveDetail getReserveDetail(int reserveId);
    
    public boolean reserveUpdate(int reserveId);
    
    public SalesAmount getLastSaleAmount(int branchId, int userId);
    
    public String[] getLastSaleAmountDates(int branchId);
    
    public boolean saveSalesAccount(SalesAmount sa);
    
    public List<KeyValue> lastSaleAmountDateByBranchId(long branchId);
}
