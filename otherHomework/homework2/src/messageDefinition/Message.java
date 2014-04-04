package messageDefinition;

/*
Message will look like this
@ are used as delimeters

<type>@<messageID>@<conversationID>@<senderId>@@
 */


import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

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
                return new AgentsReq(string);
            case AGENTRES:
								return new AgentsRes(string);
            case CLOCKTICK:
                return new ClockTick(string);
            case DECREASEHEALTH:
							return new DecreaseHealth(string);
            case EXCUSEREQ:
							return new ExcuseReq(string);
            case EXCUSERES:
							return new ExcuseReq(string);
            case FIELDRES:
							return new FieldRes(string);
            case FIELDREQ:
							return new FieldReq(string);
            case LAYOUTREQ:
							return new LayoutReq(string);
            case PARAMETERREQ:
							return new ParameterReq(string);
						case PARAMETERRES:
							return new ParameterRes(string);
						case REGISTER:
							return new Register(string);
						case TARGETREQ:
							return new TargetReq(string);
						case TARGETRES:
							return new TargetRes(string);
						case THROWBOMB:
							return  new ThrowBomb(string);
						case WHINEREQ:
							return new WhineReq(string);
						case WHINERES:
							return new WhineRes(string);

					case REQUEST:
					case RESPONSE:
					case MESSAGE:
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