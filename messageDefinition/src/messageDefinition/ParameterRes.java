package messageDefinition;

import Resources.Parameters;

public class ParameterRes extends Response {
	public Parameters params;

	public ParameterRes(String messageString)
	{
		super(messageString);
		this.params = new Parameters( messageString.split(DELIMITER + DELIMITER)[2]);
	}

	public ParameterRes(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType, Parameters params)
	{
		super(type, messageID, conversationID, senderId, requestType);
		this.params = params;
	}

	@Override
    public String serialize() {
        return mesString() + resString() + params.toString();
    }
}