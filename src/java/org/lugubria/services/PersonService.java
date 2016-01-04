
package org.lugubria.services;

import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "PersonService", targetNamespace = "http://services.lugubria.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PersonService {


    /**
     * 
     * @param person
     */
    @WebMethod
    @Oneway
    @RequestWrapper(localName = "updatePerson", targetNamespace = "http://services.lugubria.org/", className = "org.lugubria.services.UpdatePerson")
    public void updatePerson(
        @WebParam(name = "person", targetNamespace = "")
        Person person);

    /**
     * 
     * @param criteria
     * @param type
     * @return
     *     returns java.util.List<org.lugubria.services.Person>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getpersonListByCriteria", targetNamespace = "http://services.lugubria.org/", className = "org.lugubria.services.GetpersonListByCriteria")
    @ResponseWrapper(localName = "getpersonListByCriteriaResponse", targetNamespace = "http://services.lugubria.org/", className = "org.lugubria.services.GetpersonListByCriteriaResponse")
    public List<Person> getpersonListByCriteria(
        @WebParam(name = "type", targetNamespace = "")
        int type,
        @WebParam(name = "criteria", targetNamespace = "")
        String criteria);

    /**
     * 
     * @param criteria
     * @param type
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getSizeResult", targetNamespace = "http://services.lugubria.org/", className = "org.lugubria.services.GetSizeResult")
    @ResponseWrapper(localName = "getSizeResultResponse", targetNamespace = "http://services.lugubria.org/", className = "org.lugubria.services.GetSizeResultResponse")
    public int getSizeResult(
        @WebParam(name = "criteria", targetNamespace = "")
        String criteria,
        @WebParam(name = "type", targetNamespace = "")
        int type);

    /**
     * 
     * @param perId
     * @return
     *     returns org.lugubria.services.Person
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getPerson", targetNamespace = "http://services.lugubria.org/", className = "org.lugubria.services.GetPerson")
    @ResponseWrapper(localName = "getPersonResponse", targetNamespace = "http://services.lugubria.org/", className = "org.lugubria.services.GetPersonResponse")
    public Person getPerson(
        @WebParam(name = "perId", targetNamespace = "")
        int perId);

}
