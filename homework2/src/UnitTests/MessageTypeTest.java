package UnitTests;

import messageDefinition.Message;
import messageDefinition.MessageType;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by gregor on 2/2/14.
 */
public class MessageTypeTest {
    @Test
    public void testGet() throws Exception {
        assertEquals(MessageType.MESSAGE,MessageType.get("MESSAGE"));
        assertEquals(MessageType.REQUEST,MessageType.get("REQUEST"));
        assertEquals(MessageType.BRILLIANTSTUDENT,MessageType.get("BRILLIANTSTUDENT"));
        assertEquals(MessageType.ACK,MessageType.get("ACK"));
        assertEquals(MessageType.REQUEST,MessageType.get("REQUEST"));
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(MessageType.MESSAGE.toString(),"MESSAGE");
        assertEquals(MessageType.ACK.toString(),"ACK");
        assertEquals(MessageType.EXCUSEGEN.toString(),"EXCUSEGEN");
        assertEquals(MessageType.BRILLIANTSTUDENT.toString(),"BRILLIANTSTUDENT");
    }
}
