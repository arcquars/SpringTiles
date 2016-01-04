/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service.facade;

import java.util.List;

/**
 *
 * @author angel
 */
public interface IDetailBuyRepository {
    
    public List<Double> getDatesBuyByProductId(int productId);
}
