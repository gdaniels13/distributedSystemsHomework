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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author gregor
 */
public class ListenerTest {
    
    @Test
    public void testListen() throws Exception {
        EnvelopeQueue envelopeQueue = new EnvelopeQueue();
         Config con1 = new Config();
        Communicator com1 = new Communicator(con1);
        Listener listener = new Listener(com1, envelopeQueue);
        
         Config con2 = new Config();
         Communicator com2 = new Communicator(con2);
        
         com2.send(new Envelope(new AckNak(Reply.PossibleStatus.Success, 12),new Endpoint(con1.getLocalPort(), InetAddress.getByName("localhost"))));
                

        new Thread(listener).start();
        wait(1000);
        
        assertNotNull(listener.getEnvelopeQueue().pop());
        
    
    
    }
    
}
