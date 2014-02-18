package messageDefinition;

public class Acknowledge extends Response {
    public Acknowledge(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType) {
        super(type, messageID, conversationID, senderId, requestType);
    }

    public Acknowledge(String messageString) {
        super(messageString);
    }

    @Override
    public String serialize() {
        return this.mesString() + this.resString() ;
    }
}