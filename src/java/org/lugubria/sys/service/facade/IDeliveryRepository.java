/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service.facade;

import org.lugubria.sys.domain.DeliveryBranch;

/**
 *
 * @author angel
 */
public interface IDeliveryRepository {

    public boolean saveDelivery(DeliveryBranch db);
}
