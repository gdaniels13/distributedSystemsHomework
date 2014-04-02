
package registrarClient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetGamesAltResult" type="{http://schemas.datacontract.org/2004/07/Common}ArrayOfGameInfoAlt" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getGamesAltResult"
})
@XmlRootElement(name = "GetGamesAltResponse")
public class GetGamesAltResponse {

    @XmlElementRef(name = "GetGamesAltResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfGameInfoAlt> getGamesAltResult;

    /**
     * Gets the value of the getGamesAltResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfGameInfoAlt }{@code >}
     *     
     */
    public JAXBElement<ArrayOfGameInfoAlt> getGetGamesAltResult() {
        return getGamesAltResult;
    }

    /**
     * Sets the value of the getGamesAltResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfGameInfoAlt }{@code >}
     *     
     */
    public void setGetGamesAltResult(JAXBElement<ArrayOfGameInfoAlt> value) {
        this.getGamesAltResult = value;
    }

}
