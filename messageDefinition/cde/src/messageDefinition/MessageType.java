package messageDefinition;

/**
 * Created by gregor on 2/1/14.
 */
public enum MessageType {
    ERROR("ERROR"),
    UNKNOWN("UNKNOWN"),
    ACK("ACK"),
    AGENTREQ("AGENTREQ"),
    AGENTRES("AGENTRES"),
    CLOCKTICK("CLOCKTICK"),
    DECREASEHEALTH("DECREASEHEALTH"),
    EXCUSEREQ("EXCUSEREQ"),
    EXCUSERES("EXCUSERES"),
    FIELDRES("FIELDRES"),
    FIELDREQ("FIELDREQ"),
    LAYOUTREQ("LAYOUTREQ"),
    MESSAGE("MESSAGE"),
    PARAMETERREQ("PARAMETERREQ"),
    PARAMETERRES("PARAMETERRES"),
    REGISTER("REGISTER"),
    REQUEST("REQUEST"),
    RESPONSE("RESPONSE"),
    TARGETREQ("TARGETREQ"),
    TARGETRES("TARGETRES"),
    THROWBOMB("THROWBOMB"),
    WHINEREQ("WHINEREQ"),
    WHINERES("WHINERES"),

    //AGENT TYPES WHERE INCLUDED in this enum TO INCREASE SIMPLICITY & reduce number of classes

    BRILLIANTSTUDENT("BRILLIANTSTUDENT"),
    WHINEGEN("WHINEGEN"),
    EXCUSEGEN("EXCUSEGEN"),
    ZOMBIEGEN("ZOMBIEGEN");

    private String type;

    private MessageType(String t){
        this.type = t;
    }

    public static MessageType get(String t){
        for(MessageType cur : MessageType.values()){
            if(cur.toString().compareTo(t)==0){
                return cur;
            }
        }
        return UNKNOWN;
    }

    @Override
    public String toString(){
        return type;
    }
}
