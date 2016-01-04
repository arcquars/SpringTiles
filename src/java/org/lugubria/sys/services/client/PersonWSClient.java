/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.services.client;

import org.lugubria.sys.domain.Person;
import org.lugubria.sys.services.client.impl.IPersonWS;

/**
 *
 * @author angel
 */
public class PersonWSClient{
    
    IPersonWS personWS;

    public IPersonWS getPersonWS() {
        return personWS;
    }

    public void setPersonWS(IPersonWS personWS) {
        this.personWS = personWS;
    }
    
    public int callService(){
        return personWS.getSizeResult("pe",1);
    }
    
    public int callListSize(){
        return personWS.getpersonListByCriteria(1, "pe").size();
    }
    
    public Person callPersonByCi(){
        String ee = personWS.getPerson(456).getNames();
        System.err.println("eee:::::::::::::"+ee);
        return personWS.getPerson(456);
    }
    
    
}
