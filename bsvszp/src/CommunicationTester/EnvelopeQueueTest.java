package CommunicationTester;


import Common.ComponentInfo;
import Communication.Envelope;
import Communication.EnvelopeQueue;
import Messages.JoinGame;
import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
        ComponentInfo agentInfo = new ComponentInfo((short) 1001, ComponentInfo.PossibleAgentType.BrilliantStudent);
        JoinGame jg1 = new JoinGame((short) 10, "A00123", "Joe", "Jones", agentInfo);
        Envelope sent = new Envelope(jg1, InetAddress.getByName("localhost"),1234);
        EnvelopeQueue eq = new EnvelopeQueue();

        Envelope e = eq.pop();
        assertNull(e);

        eq.push(sent);
        eq.push(sent);

        e = eq.pop();
        assertNotNull(e);
        assertEquals(e,sent);
        e = eq.pop();
        assertNotNull(e);
        assertEquals(e,sent);
	}
}
