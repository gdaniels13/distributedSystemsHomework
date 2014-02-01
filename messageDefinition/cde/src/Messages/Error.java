package Messages;

/**
 * Created with IntelliJ IDEA.
 * User: gregor
 * Date: 1/31/14
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Error implements Message
{
	protected MessageType type;
	protected String message;
	public Error()
	{
		this.type = MessageType.ERROR;
		this.message = null;
	}

	public Error(String message)
	{
		this.type = MessageType.ERROR;
		this.message = message;
	}

	@Override
	public String Serialize()
	{
		return this.toString();  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public Message deSerialize(String message)
	{
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public MessageType getType()
	{
		return this.type;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public String toString()
	{
		return this.type + "," +  this.message;
	}
}
