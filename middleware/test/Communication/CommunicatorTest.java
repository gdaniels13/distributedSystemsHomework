/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Communication;

import Messages.AckNak;
import Messages.Reply;
import java.net.InetAddress;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gregor
 */
public class CommunicatorTest {
    
    Communicator com1,com2;
    Config con1, con2;
    @Test
    public void testSendReceive() throws Exception {
        con2 = new Config();
                com2 = new Communicator(con2);

        con1 = new Config();
        com1 = new Communicator(con1);
       
        com2.send(new Envelope(new AckNak(Reply.PossibleStatus.Success, 12),new Endpoint(con1.getLocalPort(), InetAddress.getByName("localhost"))));
        
       Envelope env = com1.listen();
        Reply r = (Reply) env.getMessage();
        assertNotNull(r);
        assertEquals(r.Status , Reply.PossibleStatus.Success);
       
        
    }
    
}
