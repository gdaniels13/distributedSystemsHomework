package messageDefinition;

public class DecreaseHealth extends Request {
	public int ammount;

	public DecreaseHealth(String requestString)
	{
		super(requestString);
		this.ammount = Integer.parseInt(requestString.split(DELIMITER + DELIMITER)[2]);
	}

	public DecreaseHealth(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType, int ammount)
	{
		super(type, messageID, conversationID, senderId, requestType);
		this.ammount = ammount;
	}

	@Override
    public String serialize() {
        return mesString() + reString() + ammount;
    }
}