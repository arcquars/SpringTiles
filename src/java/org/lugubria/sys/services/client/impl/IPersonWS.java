/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.services.client.impl;

import org.lugubria.sys.domain.Person;

/**
 *
 * @author angel
 */
public interface IPersonWS {
    
    public int getSizeResult(String criteria, int type);
    public java.util.List<Person> getpersonListByCriteria(int type, String criteria);
    public Person getPerson(int ci);
}
