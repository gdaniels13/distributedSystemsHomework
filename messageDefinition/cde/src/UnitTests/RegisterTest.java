package UnitTests;

import messageDefinition.Message;
import messageDefinition.MessageType;
import messageDefinition.Register;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gregor on 2/2/14.
 */
public class RegisterTest {

    @Test
    public void testConstructors() throws Exception {

        //test deserializing constructor
        Register reg = new Register("REQUEST@1234@4567@iwinner@@"+
                                "REGISTER@@BRILLIANTSTUDENT@1231");

        assertEquals(reg.agentID, "1231");
        assertEquals(reg.agentType, MessageType.BRILLIANTSTUDENT);
        assertEquals(reg.messageID, 1234);
        assertEquals(reg.conversationID,4567);
        assertEquals(reg.senderId,"iwinner");

        reg = new Register(MessageType.REQUEST,1234,4567,"iwinner",MessageType.REGISTER,MessageType.BRILLIANTSTUDENT,"1231");


        assertEquals(reg.agentID, "1231");
        assertEquals(reg.agentType, MessageType.BRILLIANTSTUDENT);
        assertEquals(reg.messageID, 1234);
        assertEquals(reg.conversationID,4567);
        assertEquals(reg.senderId,"iwinner");

    }


    @Test
    public void testSerialize() throws Exception {
        Register reg = new Register(MessageType.REQUEST,1234,4567,"iwinner",MessageType.REGISTER,MessageType.BRILLIANTSTUDENT,"1231");
        String ser = reg.serialize();
        assertEquals(ser, "REQUEST@1234@4567@iwinner@@REGISTER@@BRILLIANTSTUDENT@1231");
    }

    @Test
    public void testDeSerialize() throws Exception {
        String message = "REQUEST@1234@4567@iwinner@@REGISTER@@BRILLIANTSTUDENT@1231";
        Register reg = (Register) Message.deSerialize(message);
        assertEquals(reg.agentID, "1231");
        assertEquals(reg.agentType, MessageType.BRILLIANTSTUDENT);
        assertEquals(reg.messageID, 1234);
        assertEquals(reg.conversationID,4567);
        assertEquals(reg.senderId,"iwinner");
    }

}
