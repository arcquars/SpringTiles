/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service.facade;

import java.util.List;
import java.util.Map;
import org.lugubria.sys.domain.KeyValue;
import org.lugubria.sys.domain.ProviderPersonDto;

/**
 *
 * @author angel
 */
public interface IProviderRepository {
    
    public boolean providerValidateName(String newName);
    
    public boolean createProviderPerson(ProviderPersonDto ppd);

    public List<KeyValue> getAllProvider();
}
