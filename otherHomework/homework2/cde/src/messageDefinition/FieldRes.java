package messageDefinition;


import Resources.PlayingField;

public class FieldRes extends Response {
	public PlayingField field;

	public FieldRes(String messageString)
	{
		super(messageString);
		this.field = new PlayingField(messageString.split(DELIMITER + DELIMITER)[2]);
	}

	public FieldRes(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType, PlayingField field)
	{
		super(type, messageID, conversationID, senderId, requestType);
		this.field = field;
	}

	@Override
    public String serialize() {
        return mesString() + resString() + field.toString();
    }
}