package messageDefinition;


import Resources.Excuse;

public class ExcuseRes extends Response {
	public Excuse excuse;

	public ExcuseRes(String messageString)
	{
		super(messageString);
		this.excuse = new Excuse(messageString.split(DELIMITER + DELIMITER)[2]);
	}

	public ExcuseRes(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType, Excuse excuse)
	{
		super(type, messageID, conversationID, senderId, requestType);
		this.excuse = excuse;
	}

	@Override
    public String serialize() {
        return mesString() + resString() + excuse.toString();
    }
}