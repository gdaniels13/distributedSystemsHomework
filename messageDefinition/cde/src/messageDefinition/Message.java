package messageDefinition;

/*
Message will look like this
@ are used as delimeters

<type>@<messageID>@<conversationID>@<senderId>@@
 */


public abstract class Message {
	public MessageType type;
	public int messageID;
	public int conversationID;
	public String senderId;

    public static final String DELIMITER = "@";

    public Message(MessageType type, int messageID, int conversationID, String senderId) {
        this.type = type;
        this.messageID = messageID;
        this.conversationID = conversationID;
        this.senderId = senderId;
    }

    public Message(String messageString) {
		String first = messageString.split(DELIMITER+DELIMITER)[0];
        String args[] = first.split(DELIMITER);
        this.type = MessageType.get(args[0]);
        this.messageID = Integer.parseInt(args[1]);
        this.conversationID = Integer.parseInt((args[2]));
        this.senderId = args[3];
	}

    public static Message deSerialize(String string) throws Exception {

        String type = string.split(DELIMITER+DELIMITER)[1].split(DELIMITER + DELIMITER)[0];


        switch(MessageType.get(type)){
            case ERROR:
                break;
            case ACK:
            return new Acknowledge(string);
            case AGENTREQ:
            break;
            case AGENTRES:
            break;
            case CLOCKTICK:
            break;
            case DECREASEHEALTH:
            break;
            case EXCUSEREQ:
            break;
            case EXCUSERES:
            break;
            case FIELDRES:
            break;
            case FIELDREQ:
            break;
            case LAYOUTREQ:
            break;
            case MESSAGE:
            break;
            case PARAMETERREQ:
            break;
            case PARAMETERRES:
            break;
            case REGISTER:
                return new Register(string);
            case REQUEST:
            break;
            case RESPONSE:
            break;
            case TARGETREQ:
            break;
            case TARGETRES:
            break;
            case THROWBOMB:
            break;
            case WHINEREQ:
            break;
            case WHINERES:
            break;
            case UNKNOWN:
            default:

            throw new Exception("Unknown Parameters");
        }

        return null;
    }

    public String mesString(){
        return type.toString() + DELIMITER + messageID + DELIMITER
                + conversationID + DELIMITER + senderId + DELIMITER + DELIMITER ;
    }

	public abstract String serialize();
}