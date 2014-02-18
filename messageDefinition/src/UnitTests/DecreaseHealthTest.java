package UnitTests;

import messageDefinition.ClockTick;
import messageDefinition.DecreaseHealth;
import messageDefinition.Message;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/5/14
 * Time: 2:23 PM
 */
public class DecreaseHealthTest
{
	@Test
	public void testSerialize() throws Exception {
		ClockTick ack = new ClockTick("REQUEST@1234@4567@iwinner@@DECREASEHEALTH@@1234568");

		assertEquals("REQUEST@1234@4567@iwinner@@DECREASEHEALTH@@1234568", ack.serialize());
	}

	@Test
	public void testDeSerialize() throws Exception {

		DecreaseHealth ack = (DecreaseHealth) Message.deSerialize("REQUEST@1234@4567@iwinner@@DECREASEHEALTH@@1234568");
		assertEquals(ack.messageID, 1234);
		assertEquals(ack.conversationID,4567);
		assertEquals(ack.senderId,"iwinner");
		assertEquals(ack.ammount, 1234568);
	}

}
