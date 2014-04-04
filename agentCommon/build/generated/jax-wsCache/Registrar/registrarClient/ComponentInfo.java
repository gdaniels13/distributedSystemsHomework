
package registrarClient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComponentInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComponentInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/Common}DistributableObject">
 *       &lt;sequence>
 *         &lt;element name="CommunicationEndPoint" type="{http://schemas.datacontract.org/2004/07/Common}EndPoint" minOccurs="0"/>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComponentInfo", namespace = "http://schemas.datacontract.org/2004/07/Common", propOrder = {
    "communicationEndPoint",
    "id"
})
@XmlSeeAlso({
    GameInfo.class
})
public class ComponentInfo
    extends DistributableObject
{

    @XmlElementRef(name = "CommunicationEndPoint", namespace = "http://schemas.datacontract.org/2004/07/Common", type = JAXBElement.class, required = false)
    protected JAXBElement<EndPoint> communicationEndPoint;
    @XmlElement(name = "Id")
    protected Short id;

    /**
     * Gets the value of the communicationEndPoint property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link EndPoint }{@code >}
     *     
     */
    public JAXBElement<EndPoint> getCommunicationEndPoint() {
        return communicationEndPoint;
    }

    /**
     * Sets the value of the communicationEndPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link EndPoint }{@code >}
     *     
     */
    public void setCommunicationEndPoint(JAXBElement<EndPoint> value) {
        this.communicationEndPoint = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setId(Short value) {
        this.id = value;
    }

}
