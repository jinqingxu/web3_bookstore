
package com.soap.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.soap.client package. 
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

    private final static QName _Statisticone_QNAME = new QName("http://service.module.com/", "statisticone");
    private final static QName _Statistictwo_QNAME = new QName("http://service.module.com/", "statistictwo");
    private final static QName _StatistictwoResponse_QNAME = new QName("http://service.module.com/", "statistictwoResponse");
    private final static QName _StatisticoneResponse_QNAME = new QName("http://service.module.com/", "statisticoneResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.soap.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Statisticone }
     * 
     */
    public Statisticone createStatisticone() {
        return new Statisticone();
    }

    /**
     * Create an instance of {@link Statistictwo }
     * 
     */
    public Statistictwo createStatistictwo() {
        return new Statistictwo();
    }

    /**
     * Create an instance of {@link StatistictwoResponse }
     * 
     */
    public StatistictwoResponse createStatistictwoResponse() {
        return new StatistictwoResponse();
    }

    /**
     * Create an instance of {@link StatisticoneResponse }
     * 
     */
    public StatisticoneResponse createStatisticoneResponse() {
        return new StatisticoneResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Statisticone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.module.com/", name = "statisticone")
    public JAXBElement<Statisticone> createStatisticone(Statisticone value) {
        return new JAXBElement<Statisticone>(_Statisticone_QNAME, Statisticone.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Statistictwo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.module.com/", name = "statistictwo")
    public JAXBElement<Statistictwo> createStatistictwo(Statistictwo value) {
        return new JAXBElement<Statistictwo>(_Statistictwo_QNAME, Statistictwo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatistictwoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.module.com/", name = "statistictwoResponse")
    public JAXBElement<StatistictwoResponse> createStatistictwoResponse(StatistictwoResponse value) {
        return new JAXBElement<StatistictwoResponse>(_StatistictwoResponse_QNAME, StatistictwoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatisticoneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.module.com/", name = "statisticoneResponse")
    public JAXBElement<StatisticoneResponse> createStatisticoneResponse(StatisticoneResponse value) {
        return new JAXBElement<StatisticoneResponse>(_StatisticoneResponse_QNAME, StatisticoneResponse.class, null, value);
    }

}
