package Messages;

/**
 * Created with IntelliJ IDEA.
 * User: gregor
 * Date: 1/31/14
 * Time: 12:55 PM
 * To change this template use File | Settings | File Templates.
 */

public interface Message
{
	public String Serialize();
	public Message deSerialize(String message);
	public MessageType getType();

}
