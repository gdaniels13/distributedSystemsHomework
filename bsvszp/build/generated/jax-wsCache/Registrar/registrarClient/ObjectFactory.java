
package registrarClient;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the registrarClient package. 
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

    private final static QName _AnyURI_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyURI");
    private final static QName _ArrayOfGameInfoAlt_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "ArrayOfGameInfoAlt");
    private final static QName _Char_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
    private final static QName _DateTime_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "dateTime");
    private final static QName _ComponentInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "ComponentInfo");
    private final static QName _QName_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "QName");
    private final static QName _UnsignedShort_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedShort");
    private final static QName _ArrayOfGameInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "ArrayOfGameInfo");
    private final static QName _Float_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "float");
    private final static QName _Long_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "long");
    private final static QName _GameInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "GameInfo");
    private final static QName _Short_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "short");
    private final static QName _GameInfoGameStatus_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "GameInfo.GameStatus");
    private final static QName _Base64Binary_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "base64Binary");
    private final static QName _Byte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "byte");
    private final static QName _EndPoint_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "EndPoint");
    private final static QName _Boolean_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "boolean");
    private final static QName _UnsignedByte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedByte");
    private final static QName _AnyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyType");
    private final static QName _DistributableObject_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "DistributableObject");
    private final static QName _UnsignedInt_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedInt");
    private final static QName _Int_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "int");
    private final static QName _Decimal_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "decimal");
    private final static QName _Double_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "double");
    private final static QName _Guid_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
    private final static QName _GameInfoAlt_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "GameInfoAlt");
    private final static QName _Duration_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
    private final static QName _String_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "string");
    private final static QName _UnsignedLong_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedLong");
    private final static QName _GameInfoAltLabel_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "Label");
    private final static QName _GameInfoAltAliveTimestamp_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "AliveTimestamp");
    private final static QName _GameInfoAltCommunicationEndPoint_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "CommunicationEndPoint");
    private final static QName _GameInfoAltStatus_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "Status");
    private final static QName _RegisterGamePublicEP_QNAME = new QName("http://tempuri.org/", "publicEP");
    private final static QName _RegisterGameLabel_QNAME = new QName("http://tempuri.org/", "label");
    private final static QName _GetGamesResponseGetGamesResult_QNAME = new QName("http://tempuri.org/", "GetGamesResult");
    private final static QName _RegisterGameResponseRegisterGameResult_QNAME = new QName("http://tempuri.org/", "RegisterGameResult");
    private final static QName _GetGamesAltResponseGetGamesAltResult_QNAME = new QName("http://tempuri.org/", "GetGamesAltResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: registrarClient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ChangeStatus }
     * 
     */
    public ChangeStatus createChangeStatus() {
        return new ChangeStatus();
    }

    /**
     * Create an instance of {@link RegisterGameResponse }
     * 
     */
    public RegisterGameResponse createRegisterGameResponse() {
        return new RegisterGameResponse();
    }

    /**
     * Create an instance of {@link GameInfo }
     * 
     */
    public GameInfo createGameInfo() {
        return new GameInfo();
    }

    /**
     * Create an instance of {@link ChangeStatusResponse }
     * 
     */
    public ChangeStatusResponse createChangeStatusResponse() {
        return new ChangeStatusResponse();
    }

    /**
     * Create an instance of {@link GetGamesAltResponse }
     * 
     */
    public GetGamesAltResponse createGetGamesAltResponse() {
        return new GetGamesAltResponse();
    }

    /**
     * Create an instance of {@link ArrayOfGameInfoAlt }
     * 
     */
    public ArrayOfGameInfoAlt createArrayOfGameInfoAlt() {
        return new ArrayOfGameInfoAlt();
    }

    /**
     * Create an instance of {@link AmAliveResponse }
     * 
     */
    public AmAliveResponse createAmAliveResponse() {
        return new AmAliveResponse();
    }

    /**
     * Create an instance of {@link AmAlive }
     * 
     */
    public AmAlive createAmAlive() {
        return new AmAlive();
    }

    /**
     * Create an instance of {@link GetGamesAlt }
     * 
     */
    public GetGamesAlt createGetGamesAlt() {
        return new GetGamesAlt();
    }

    /**
     * Create an instance of {@link GetGames }
     * 
     */
    public GetGames createGetGames() {
        return new GetGames();
    }

    /**
     * Create an instance of {@link GetGamesResponse }
     * 
     */
    public GetGamesResponse createGetGamesResponse() {
        return new GetGamesResponse();
    }

    /**
     * Create an instance of {@link ArrayOfGameInfo }
     * 
     */
    public ArrayOfGameInfo createArrayOfGameInfo() {
        return new ArrayOfGameInfo();
    }

    /**
     * Create an instance of {@link RegisterGame }
     * 
     */
    public RegisterGame createRegisterGame() {
        return new RegisterGame();
    }

    /**
     * Create an instance of {@link EndPoint }
     * 
     */
    public EndPoint createEndPoint() {
        return new EndPoint();
    }

    /**
     * Create an instance of {@link DistributableObject }
     * 
     */
    public DistributableObject createDistributableObject() {
        return new DistributableObject();
    }

    /**
     * Create an instance of {@link ComponentInfo }
     * 
     */
    public ComponentInfo createComponentInfo() {
        return new ComponentInfo();
    }

    /**
     * Create an instance of {@link GameInfoAlt }
     * 
     */
    public GameInfoAlt createGameInfoAlt() {
        return new GameInfoAlt();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyURI")
    public JAXBElement<String> createAnyURI(String value) {
        return new JAXBElement<String>(_AnyURI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfGameInfoAlt }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "ArrayOfGameInfoAlt")
    public JAXBElement<ArrayOfGameInfoAlt> createArrayOfGameInfoAlt(ArrayOfGameInfoAlt value) {
        return new JAXBElement<ArrayOfGameInfoAlt>(_ArrayOfGameInfoAlt_QNAME, ArrayOfGameInfoAlt.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "char")
    public JAXBElement<Integer> createChar(Integer value) {
        return new JAXBElement<Integer>(_Char_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "dateTime")
    public JAXBElement<XMLGregorianCalendar> createDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComponentInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "ComponentInfo")
    public JAXBElement<ComponentInfo> createComponentInfo(ComponentInfo value) {
        return new JAXBElement<ComponentInfo>(_ComponentInfo_QNAME, ComponentInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "QName")
    public JAXBElement<QName> createQName(QName value) {
        return new JAXBElement<QName>(_QName_QNAME, QName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedShort")
    public JAXBElement<Integer> createUnsignedShort(Integer value) {
        return new JAXBElement<Integer>(_UnsignedShort_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfGameInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "ArrayOfGameInfo")
    public JAXBElement<ArrayOfGameInfo> createArrayOfGameInfo(ArrayOfGameInfo value) {
        return new JAXBElement<ArrayOfGameInfo>(_ArrayOfGameInfo_QNAME, ArrayOfGameInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "float")
    public JAXBElement<Float> createFloat(Float value) {
        return new JAXBElement<Float>(_Float_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "long")
    public JAXBElement<Long> createLong(Long value) {
        return new JAXBElement<Long>(_Long_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GameInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "GameInfo")
    public JAXBElement<GameInfo> createGameInfo(GameInfo value) {
        return new JAXBElement<GameInfo>(_GameInfo_QNAME, GameInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "short")
    public JAXBElement<Short> createShort(Short value) {
        return new JAXBElement<Short>(_Short_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GameInfoGameStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "GameInfo.GameStatus")
    public JAXBElement<GameInfoGameStatus> createGameInfoGameStatus(GameInfoGameStatus value) {
        return new JAXBElement<GameInfoGameStatus>(_GameInfoGameStatus_QNAME, GameInfoGameStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "byte")
    public JAXBElement<Byte> createByte(Byte value) {
        return new JAXBElement<Byte>(_Byte_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndPoint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "EndPoint")
    public JAXBElement<EndPoint> createEndPoint(EndPoint value) {
        return new JAXBElement<EndPoint>(_EndPoint_QNAME, EndPoint.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedByte")
    public JAXBElement<Short> createUnsignedByte(Short value) {
        return new JAXBElement<Short>(_UnsignedByte_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyType")
    public JAXBElement<Object> createAnyType(Object value) {
        return new JAXBElement<Object>(_AnyType_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DistributableObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "DistributableObject")
    public JAXBElement<DistributableObject> createDistributableObject(DistributableObject value) {
        return new JAXBElement<DistributableObject>(_DistributableObject_QNAME, DistributableObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedInt")
    public JAXBElement<Long> createUnsignedInt(Long value) {
        return new JAXBElement<Long>(_UnsignedInt_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "decimal")
    public JAXBElement<BigDecimal> createDecimal(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Decimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "double")
    public JAXBElement<Double> createDouble(Double value) {
        return new JAXBElement<Double>(_Double_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "guid")
    public JAXBElement<String> createGuid(String value) {
        return new JAXBElement<String>(_Guid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GameInfoAlt }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "GameInfoAlt")
    public JAXBElement<GameInfoAlt> createGameInfoAlt(GameInfoAlt value) {
        return new JAXBElement<GameInfoAlt>(_GameInfoAlt_QNAME, GameInfoAlt.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "duration")
    public JAXBElement<Duration> createDuration(Duration value) {
        return new JAXBElement<Duration>(_Duration_QNAME, Duration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedLong")
    public JAXBElement<BigInteger> createUnsignedLong(BigInteger value) {
        return new JAXBElement<BigInteger>(_UnsignedLong_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "Label", scope = GameInfoAlt.class)
    public JAXBElement<String> createGameInfoAltLabel(String value) {
        return new JAXBElement<String>(_GameInfoAltLabel_QNAME, String.class, GameInfoAlt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "AliveTimestamp", scope = GameInfoAlt.class)
    public JAXBElement<String> createGameInfoAltAliveTimestamp(String value) {
        return new JAXBElement<String>(_GameInfoAltAliveTimestamp_QNAME, String.class, GameInfoAlt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "CommunicationEndPoint", scope = GameInfoAlt.class)
    public JAXBElement<String> createGameInfoAltCommunicationEndPoint(String value) {
        return new JAXBElement<String>(_GameInfoAltCommunicationEndPoint_QNAME, String.class, GameInfoAlt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "Status", scope = GameInfoAlt.class)
    public JAXBElement<String> createGameInfoAltStatus(String value) {
        return new JAXBElement<String>(_GameInfoAltStatus_QNAME, String.class, GameInfoAlt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndPoint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "publicEP", scope = RegisterGame.class)
    public JAXBElement<EndPoint> createRegisterGamePublicEP(EndPoint value) {
        return new JAXBElement<EndPoint>(_RegisterGamePublicEP_QNAME, EndPoint.class, RegisterGame.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "label", scope = RegisterGame.class)
    public JAXBElement<String> createRegisterGameLabel(String value) {
        return new JAXBElement<String>(_RegisterGameLabel_QNAME, String.class, RegisterGame.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfGameInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetGamesResult", scope = GetGamesResponse.class)
    public JAXBElement<ArrayOfGameInfo> createGetGamesResponseGetGamesResult(ArrayOfGameInfo value) {
        return new JAXBElement<ArrayOfGameInfo>(_GetGamesResponseGetGamesResult_QNAME, ArrayOfGameInfo.class, GetGamesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndPoint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "CommunicationEndPoint", scope = ComponentInfo.class)
    public JAXBElement<EndPoint> createComponentInfoCommunicationEndPoint(EndPoint value) {
        return new JAXBElement<EndPoint>(_GameInfoAltCommunicationEndPoint_QNAME, EndPoint.class, ComponentInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GameInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "RegisterGameResult", scope = RegisterGameResponse.class)
    public JAXBElement<GameInfo> createRegisterGameResponseRegisterGameResult(GameInfo value) {
        return new JAXBElement<GameInfo>(_RegisterGameResponseRegisterGameResult_QNAME, GameInfo.class, RegisterGameResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "Label", scope = GameInfo.class)
    public JAXBElement<String> createGameInfoLabel(String value) {
        return new JAXBElement<String>(_GameInfoAltLabel_QNAME, String.class, GameInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfGameInfoAlt }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetGamesAltResult", scope = GetGamesAltResponse.class)
    public JAXBElement<ArrayOfGameInfoAlt> createGetGamesAltResponseGetGamesAltResult(ArrayOfGameInfoAlt value) {
        return new JAXBElement<ArrayOfGameInfoAlt>(_GetGamesAltResponseGetGamesAltResult_QNAME, ArrayOfGameInfoAlt.class, GetGamesAltResponse.class, value);
    }

}
