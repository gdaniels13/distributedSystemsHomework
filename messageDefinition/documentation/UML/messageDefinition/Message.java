public abstract class Message {
	public String type;
	public int messageID;
	public int conversationID;
	private String senderId;

	public Message(Object string) {
		throw new UnsupportedOperationException();
	}

	public Message deSerialize(Object string) {
		throw new UnsupportedOperationException();
	}

	public String serialize() {
		throw new UnsupportedOperationException();
	}
}