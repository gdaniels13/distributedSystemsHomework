package messageDefinition;

public class TargetReq extends Request {

    public TargetReq(String requestString) {
        super(requestString);
    }

    public TargetReq(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType) {
        super(type, messageID, conversationID, senderId, requestType);
    }
}