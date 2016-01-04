/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service.facade;

import org.lugubria.sys.domain.BuyProducts;

/**
 *
 * @author angel
 */
public interface IBuyRepository {
 
    public boolean saveBuy(BuyProducts buyProducts);
    
    public BuyProducts getBuyProductByCreditId(int creditId);
}
