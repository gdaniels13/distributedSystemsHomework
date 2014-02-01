package Messages;

/**
 * Created with IntelliJ IDEA.
 * User: gregor
 * Date: 1/31/14
 * Time: 12:58 PM
 * To change this template use File | Settings | File Templates.
 */
public enum MessageType{
	ERROR("ERROR"),
	UNKNOWN("UNKNOWN");

	private String type;

	private MessageType(String t){
		this.type = t;
	}

	public static MessageType getTypeFromString(String t){
		for(MessageType cur : MessageType.values()){
			if(cur.toString() == t){
				return cur;
			}
		}
		return UNKNOWN;
	}

	@Override
	public String toString(){
		return type;
	}
}
