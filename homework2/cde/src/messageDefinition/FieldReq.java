package messageDefinition;


import Resources.PlayingField;

public class FieldReq extends Request {


    public FieldReq(String requestString) {
        super(requestString);

    }

    public FieldReq(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType) {
        super(type, messageID, conversationID, senderId, requestType);
    }

    @Override
    public String serialize() {
        return mesString() + reString();
    }
}