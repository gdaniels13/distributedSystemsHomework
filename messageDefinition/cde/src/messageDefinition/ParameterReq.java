package messageDefinition;

public class ParameterReq extends Request {
    public ParameterReq(String requestString) {
        super(requestString);
    }

    public ParameterReq(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType) {
        super(type, messageID, conversationID, senderId, requestType);
    }


}