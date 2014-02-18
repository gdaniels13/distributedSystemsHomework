package messageDefinition;

import Resources.Bomb;
import Resources.TimeTick;

public class ThrowBomb extends Request {
	public Bomb bomb;
	public TimeTick tick;

    public ThrowBomb(String requestString) {
        super(requestString);
        String t = requestString.split(DELIMITER + DELIMITER)[2];
        String [] args = t.split(DELIMITER);

        this.bomb = new Bomb(Integer.parseInt(args[0]));
        this.tick = new TimeTick(Integer.parseInt(args[1]));
    }

    public ThrowBomb(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType, Bomb bomb, TimeTick tick) {
        super(type, messageID, conversationID, senderId, requestType);
        this.bomb = bomb;
        this.tick = tick;
    }

    @Override
    public String serialize() {
        return mesString() + reString() + bomb.getId() + DELIMITER + tick.getId();
    }
}