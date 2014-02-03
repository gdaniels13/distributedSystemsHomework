package messageDefinition;
/*
...<agentType>@agentID
 */


public class Register extends Request {
	public MessageType agentType;
	public String agentID;

    public Register(String requestString) {
        super(requestString);
        String args[] = requestString.split(DELIMITER + DELIMITER);
        args = args[2].split(DELIMITER);
        this.agentType = MessageType.get(args[0]);
        this.agentID = args[1];
    }

    public Register(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType, MessageType agentType, String agentID) {
        super(type, messageID, conversationID, senderId, requestType);
        this.agentType = agentType;
        this.agentID = agentID;
    }

    @Override
    public String serialize() {
        return mesString() + reString() +  agentType.toString() + DELIMITER + agentID.toString();
    }
}