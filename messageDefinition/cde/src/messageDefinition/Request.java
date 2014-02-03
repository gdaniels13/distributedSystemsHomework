package messageDefinition;

/*
request will look like this

....@@<requestType>@@.....
 */


public abstract class Request extends Message {
	public MessageType requestType;


    public Request(String requestString){
        super(requestString);
        String type = requestString.split(DELIMITER+DELIMITER)[1];
        this.requestType = MessageType.get(type);
    }

    public Request(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType) {
        super(type, messageID, conversationID, senderId);
        this.requestType = requestType;
    }



    public String reString(){
        return requestType.toString() + DELIMITER + DELIMITER;
    }

}