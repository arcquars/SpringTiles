
package org.lugubria.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.lugubria.services package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetpersonListByCriteriaResponse_QNAME = new QName("http://services.lugubria.org/", "getpersonListByCriteriaResponse");
    private final static QName _GetSizeResult_QNAME = new QName("http://services.lugubria.org/", "getSizeResult");
    private final static QName _GetSizeResultResponse_QNAME = new QName("http://services.lugubria.org/", "getSizeResultResponse");
    private final static QName _GetpersonListByCriteria_QNAME = new QName("http://services.lugubria.org/", "getpersonListByCriteria");
    private final static QName _GetPerson_QNAME = new QName("http://services.lugubria.org/", "getPerson");
    private final static QName _GetPersonResponse_QNAME = new QName("http://services.lugubria.org/", "getPersonResponse");
    private final static QName _UpdatePerson_QNAME = new QName("http://services.lugubria.org/", "updatePerson");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.lugubria.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdatePerson }
     * 
     */
    public UpdatePerson createUpdatePerson() {
        return new UpdatePerson();
    }

    /**
     * Create an instance of {@link GetpersonListByCriteriaResponse }
     * 
     */
    public GetpersonListByCriteriaResponse createGetpersonListByCriteriaResponse() {
        return new GetpersonListByCriteriaResponse();
    }

    /**
     * Create an instance of {@link GetpersonListByCriteria }
     * 
     */
    public GetpersonListByCriteria createGetpersonListByCriteria() {
        return new GetpersonListByCriteria();
    }

    /**
     * Create an instance of {@link GetSizeResultResponse }
     * 
     */
    public GetSizeResultResponse createGetSizeResultResponse() {
        return new GetSizeResultResponse();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link GetSizeResult }
     * 
     */
    public GetSizeResult createGetSizeResult() {
        return new GetSizeResult();
    }

    /**
     * Create an instance of {@link GetPerson }
     * 
     */
    public GetPerson createGetPerson() {
        return new GetPerson();
    }

    /**
     * Create an instance of {@link GetPersonResponse }
     * 
     */
    public GetPersonResponse createGetPersonResponse() {
        return new GetPersonResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetpersonListByCriteriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.lugubria.org/", name = "getpersonListByCriteriaResponse")
    public JAXBElement<GetpersonListByCriteriaResponse> createGetpersonListByCriteriaResponse(GetpersonListByCriteriaResponse value) {
        return new JAXBElement<GetpersonListByCriteriaResponse>(_GetpersonListByCriteriaResponse_QNAME, GetpersonListByCriteriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSizeResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.lugubria.org/", name = "getSizeResult")
    public JAXBElement<GetSizeResult> createGetSizeResult(GetSizeResult value) {
        return new JAXBElement<GetSizeResult>(_GetSizeResult_QNAME, GetSizeResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSizeResultResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.lugubria.org/", name = "getSizeResultResponse")
    public JAXBElement<GetSizeResultResponse> createGetSizeResultResponse(GetSizeResultResponse value) {
        return new JAXBElement<GetSizeResultResponse>(_GetSizeResultResponse_QNAME, GetSizeResultResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetpersonListByCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.lugubria.org/", name = "getpersonListByCriteria")
    public JAXBElement<GetpersonListByCriteria> createGetpersonListByCriteria(GetpersonListByCriteria value) {
        return new JAXBElement<GetpersonListByCriteria>(_GetpersonListByCriteria_QNAME, GetpersonListByCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.lugubria.org/", name = "getPerson")
    public JAXBElement<GetPerson> createGetPerson(GetPerson value) {
        return new JAXBElement<GetPerson>(_GetPerson_QNAME, GetPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.lugubria.org/", name = "getPersonResponse")
    public JAXBElement<GetPersonResponse> createGetPersonResponse(GetPersonResponse value) {
        return new JAXBElement<GetPersonResponse>(_GetPersonResponse_QNAME, GetPersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.lugubria.org/", name = "updatePerson")
    public JAXBElement<UpdatePerson> createUpdatePerson(UpdatePerson value) {
        return new JAXBElement<UpdatePerson>(_UpdatePerson_QNAME, UpdatePerson.class, null, value);
    }

}
