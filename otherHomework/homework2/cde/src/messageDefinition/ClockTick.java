package messageDefinition;

import Resources.TimeTick;

public class ClockTick extends Request {
	public TimeTick tick;

    public ClockTick(String requestString) {
        super(requestString);
        String t = requestString.split(DELIMITER + DELIMITER)[2];
        this.tick = new TimeTick(Integer.parseInt(t));
    }

    public ClockTick(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType, TimeTick tick) {
        super(type, messageID, conversationID, senderId, requestType);
        this.tick = tick;
    }

    @Override
    public String serialize() {
        return mesString() + reString() + tick.getId();
    }
}