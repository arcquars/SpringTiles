/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service.facade;

import java.util.List;
import java.util.Map;
import org.lugubria.sys.business.Category;
import org.lugubria.sys.domain.KeyValue;

/**
 *
 * @author angel
 */
public interface ICategoryRepository {
    
    public List<Category> searchByCriteria(String criteria);
    
    public boolean categoryDelete(int catId);
    
    public boolean categoryUpdate(Category category);
    
    public boolean categoryCreate(Category category);
    
    public boolean categoryValidateName(String newName);
    
    public List<KeyValue> getAllCategory();
}
