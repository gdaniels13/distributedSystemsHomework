package Messages;

import java.lang.Override;import java.lang.String; /**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/18/14
 * Time: 11:43 AM
 */
public class Message
{
	private String message;

	@Override
	public String toString()
	{
		return message;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public Message(String message)
	{
		this.message = message;
	}
}
