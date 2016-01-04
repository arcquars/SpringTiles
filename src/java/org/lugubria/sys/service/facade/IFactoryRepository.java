/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service.facade;

import java.util.List;
import org.lugubria.sys.domain.Factory;
import org.lugubria.sys.domain.KeyValue;

/**
 *
 * @author angel
 */
public interface IFactoryRepository {
 
    public List<KeyValue> getAllFactory();
    
    public boolean factoryCreate(Factory factory);
    
    public boolean factoryValidateName(String newName);
}
