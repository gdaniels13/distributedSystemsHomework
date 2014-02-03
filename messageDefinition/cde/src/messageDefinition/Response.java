package messageDefinition;

public abstract class Response extends Message {
	public MessageType responseType;

    public Response(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType) {
        super(type, messageID, conversationID, senderId);
        this.responseType = requestType;
    }


    public Response(String messageString) {
        super(messageString);
        String type = messageString.split(DELIMITER+DELIMITER)[1];
        this.responseType = MessageType.get(type);
    }

    public String resString(){
        return responseType.toString() + DELIMITER + DELIMITER;
    }
}