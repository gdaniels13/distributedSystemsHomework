
package registrarClient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GameInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GameInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/Common}ComponentInfo">
 *       &lt;sequence>
 *         &lt;element name="AliveTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Label" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://schemas.datacontract.org/2004/07/Common}GameInfo.GameStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GameInfo", namespace = "http://schemas.datacontract.org/2004/07/Common", propOrder = {
    "aliveTimestamp",
    "label",
    "status"
})
public class GameInfo
    extends ComponentInfo
{

    @XmlElement(name = "AliveTimestamp")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar aliveTimestamp;
    @XmlElementRef(name = "Label", namespace = "http://schemas.datacontract.org/2004/07/Common", type = JAXBElement.class, required = false)
    protected JAXBElement<String> label;
    @XmlElement(name = "Status")
    protected GameInfoGameStatus status;

    /**
     * Gets the value of the aliveTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAliveTimestamp() {
        return aliveTimestamp;
    }

    /**
     * Sets the value of the aliveTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAliveTimestamp(XMLGregorianCalendar value) {
        this.aliveTimestamp = value;
    }

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLabel(JAXBElement<String> value) {
        this.label = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link GameInfoGameStatus }
     *     
     */
    public GameInfoGameStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link GameInfoGameStatus }
     *     
     */
    public void setStatus(GameInfoGameStatus value) {
        this.status = value;
    }

}
