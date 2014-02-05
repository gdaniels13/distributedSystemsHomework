package messageDefinition;

public class AgentsReq extends Response {
	public MessageType agentType;

    public AgentsReq(String messageString) {
        super(messageString);
        String type = messageString.split(DELIMITER+DELIMITER)[2];
         this.agentType = MessageType.get(type);
    }

    public AgentsReq(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType, MessageType agentType) {
        super(type, messageID, conversationID, senderId, requestType);
        this.agentType = agentType;
    }

    @Override
    public String serialize() {
        return mesString() + resString() + agentType.toString();
    }
}