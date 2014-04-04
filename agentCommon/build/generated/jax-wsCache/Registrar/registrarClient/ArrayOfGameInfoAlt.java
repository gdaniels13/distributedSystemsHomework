
package registrarClient;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfGameInfoAlt complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfGameInfoAlt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GameInfoAlt" type="{http://schemas.datacontract.org/2004/07/Common}GameInfoAlt" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfGameInfoAlt", namespace = "http://schemas.datacontract.org/2004/07/Common", propOrder = {
    "gameInfoAlt"
})
public class ArrayOfGameInfoAlt {

    @XmlElement(name = "GameInfoAlt", nillable = true)
    protected List<GameInfoAlt> gameInfoAlt;

    /**
     * Gets the value of the gameInfoAlt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gameInfoAlt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGameInfoAlt().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GameInfoAlt }
     * 
     * 
     */
    public List<GameInfoAlt> getGameInfoAlt() {
        if (gameInfoAlt == null) {
            gameInfoAlt = new ArrayList<GameInfoAlt>();
        }
        return this.gameInfoAlt;
    }

}
