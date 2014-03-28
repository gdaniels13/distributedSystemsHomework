package UnitTests;

import messageDefinition.Acknowledge;
import messageDefinition.Message;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by gregor on 2/3/14.
 */
public class AcknowledgeTest {

    public void testConstructor() throws Exception{
        Acknowledge ack = new Acknowledge("RESPONSE@1234@4567@iwinner@@ACK@@");

        assertEquals(ack.messageID, 1234);
        assertEquals(ack.conversationID,4567);
        assertEquals(ack.senderId,"iwinner");
    }


    @Test
    public void testSerialize() throws Exception {
        Acknowledge ack = new Acknowledge("RESPONSE@1234@4567@iwinner@@ACK@@");

        assertEquals("RESPONSE@1234@4567@iwinner@@ACK@@", ack.serialize());
    }

    @Test
    public void testDeSerialize() throws Exception {
        Acknowledge ack = (Acknowledge) Message.deSerialize("RESPONSE@1234@4567@iwinner@@ACK@@");
        assertEquals(ack.messageID, 1234);
        assertEquals(ack.conversationID,4567);
        assertEquals(ack.senderId,"iwinner");
    }
}
