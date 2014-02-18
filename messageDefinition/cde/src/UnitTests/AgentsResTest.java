package UnitTests;

import messageDefinition.AgentsRes;
import messageDefinition.Message;
import messageDefinition.MessageType;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/5/14
 * Time: 10:40 AM
 */
public class AgentsResTest
{
	//constructors are implicitly tested
	@Test
	public void testSerialize() throws Exception {
		AgentsRes ack = new AgentsRes("RESPONSE@1234@4567@iwinner@@AGENTRES@@ZOMBIE!!1!!2!!3!!");

		assertEquals("RESPONSE@1234@4567@iwinner@@AGENTRES@@ZOMBIE!!1!!2!!3!!", ack.serialize());

		ack = new AgentsRes("RESPONSE@1234@4567@iwinner@@AGENTRES@@ZOMBIE!!");
		assertEquals("RESPONSE@1234@4567@iwinner@@AGENTRES@@ZOMBIE!!", ack.serialize());
	}

	@Test
	public void testDeSerialize() throws Exception {
		AgentsRes ack = (AgentsRes) Message.deSerialize("RESPONSE@1234@4567@iwinner@@AGENTRES@@ZOMBIE!!1!!2!!3!!");
		assertEquals(ack.messageID, 1234);
		assertEquals(ack.conversationID,4567);
		assertEquals(ack.senderId,"iwinner");
		Assert.assertEquals(ack.agentType, MessageType.ZOMBIE);
		assertEquals(ack.agents[0].getId(),1);
		assertEquals(ack.agents[1].getId(),2);
		assertEquals(ack.agents[2].getId(),3);
	}
}
