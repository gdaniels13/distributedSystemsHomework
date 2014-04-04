package UnitTests;

import messageDefinition.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/5/14
 * Time: 10:27 AM
 */
public class AgentsReqTest
{

	//constructors are implicitly tested
	@Test
	public void testSerialize() throws Exception {
		AgentsReq ack = new AgentsReq("RESPONSE@1234@4567@iwinner@@AGENTREQ@@ZOMBIE");

		assertEquals("RESPONSE@1234@4567@iwinner@@AGENTREQ@@ZOMBIE", ack.serialize());

	}

	@Test
	public void testDeSerialize() throws Exception {
		AgentsReq ack = (AgentsReq) Message.deSerialize("RESPONSE@1234@4567@iwinner@@AGENTREQ@@ZOMBIE");
		assertEquals(ack.messageID, 1234);
		assertEquals(ack.conversationID,4567);
		assertEquals(ack.senderId,"iwinner");
		assertEquals(ack.agentType,MessageType.ZOMBIE);
	}
}
