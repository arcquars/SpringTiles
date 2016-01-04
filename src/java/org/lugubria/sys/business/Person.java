/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.business;

import java.util.List;
import org.lugubria.sys.domain.*;

/**
 *
 * @author angel
 */
public interface Person {

     public List<org.lugubria.sys.domain.Person> getpersonListByCriteria(int type, String criteria);
}
