package messageDefinition;

public class LayoutReq extends Request {
    public LayoutReq(String requestString) {
        super(requestString);
    }

    public LayoutReq(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType) {
        super(type, messageID, conversationID, senderId, requestType);
    }

}