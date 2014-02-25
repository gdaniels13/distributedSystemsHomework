package UnitTests;

import Communication.Communicator;
import Communication.Config;
import Communication.Envelope;
import Messages.Message;

import java.net.InetAddress;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/19/14
 * Time: 5:32 PM
 */
public class CommunicatorTest
{
	@org.junit.Test
	public void testSendReceive() throws Exception
	{
		Message m = new Message("blah test message");
		Envelope sent = new Envelope(m, InetAddress.getByName(Config.address),Config.port);

		Envelope received = Communicator.receive();

		assertEquals(null,received); //make sure we return null on timeout

		Communicator.send(sent); //send the message

		received = Communicator.receive();
		byte[] temp = new byte[Config.messageLength];
		assertEquals(m.getMessage(), received.getMessage().getMessage().substring(0,m.getMessage().length()));
		assertEquals(sent.getAddress().getAddress(),received.getAddress().getAddress());
	}
}
