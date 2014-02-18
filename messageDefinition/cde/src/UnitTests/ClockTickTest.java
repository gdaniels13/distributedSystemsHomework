package UnitTests;

import messageDefinition.Acknowledge;
import messageDefinition.ClockTick;
import messageDefinition.Message;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/5/14
 * Time: 11:11 AM
 */
public class ClockTickTest
{

	@Test
	public void testSerialize() throws Exception {
		 ClockTick ack = new ClockTick("RESPONSE@1234@4567@iwinner@@CLOCKTICK@@1234568");

		assertEquals("RESPONSE@1234@4567@iwinner@@CLOCKTICK@@1234568", ack.serialize());
	}

	@Test
	public void testDeSerialize() throws Exception {

		ClockTick ack = (ClockTick) Message.deSerialize("RESPONSE@1234@4567@iwinner@@CLOCKTICK@@1234568");
		assertEquals(ack.messageID, 1234);
		assertEquals(ack.conversationID,4567);
		assertEquals(ack.senderId,"iwinner");
		assertEquals(ack.tick.getId(), 1234568);
	}
}
