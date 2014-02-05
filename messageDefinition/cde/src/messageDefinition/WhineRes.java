package messageDefinition;

import Resources.Whine;

public class WhineRes extends Response {
	public Whine whine;

    public WhineRes(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType, Whine wine) {
        super(type, messageID, conversationID, senderId, requestType);
        this.whine = wine;
    }

    public WhineRes(String messageString) {
        super(messageString);
        whine = new Whine(messageString.split(DELIMITER + DELIMITER)[2]);
    }

    @Override
    public String serialize() {
        return mesString() + resString() + whine.getId();
    }
}