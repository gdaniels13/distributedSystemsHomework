package messageDefinition;

import Resources.TimeTick;

public class WhineReq extends Response {
	public TimeTick tick;

    public WhineReq(String messageString) {
        super(messageString);
        this.tick = new TimeTick(Integer.parseInt(messageString.split(DELIMITER + DELIMITER)[2]));
    }

    public WhineReq(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType, TimeTick tick) {
        super(type, messageID, conversationID, senderId, requestType);
        this.tick = tick;
    }

    @Override
    public String serialize() {
        return mesString() + resString() + tick.getId();
    }
}