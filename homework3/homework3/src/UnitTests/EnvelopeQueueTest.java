package UnitTests;


import Communication.Envelope;
import Communication.RequestEnvelopeQueue;
import Messages.Message;
import org.junit.Test;

import java.net.InetAddress;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/18/14
 * Time: 6:41 PM
 */
public class EnvelopeQueueTest
{
	@Test
	public void testPushPop() throws Exception
	{
		Message message = new Message("testMessage");
		int port= 9368;
		InetAddress address = InetAddress.getByName("localhost");

		Envelope e = new Envelope(message,address,port);

		assertEquals(0, RequestEnvelopeQueue.getSize());
		RequestEnvelopeQueue.push(e);
		assertEquals(1, RequestEnvelopeQueue.getSize());
		RequestEnvelopeQueue.push(e);
		assertEquals(2, RequestEnvelopeQueue.getSize());
		Envelope temp = RequestEnvelopeQueue.pop();

		assertEquals(e.toString(),temp.toString());
		assertEquals(1,RequestEnvelopeQueue.getSize());
		RequestEnvelopeQueue.pop();
		assertEquals(0,RequestEnvelopeQueue.getSize());

		Envelope t = RequestEnvelopeQueue.pop();
		assertEquals(null,t);

	}
}
