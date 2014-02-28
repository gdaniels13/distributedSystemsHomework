package CommunicationTester;

import AgentCommon.Config;
import Common.ComponentInfo;
import Communication.*;
import Messages.JoinGame;
import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.*;

/**
 * Created by gregor on 2/28/14.
 */
public class ListenerTest {
    @Test
    public void testRun() throws Exception {
        //setup
        Config config = new Config(9874);

        EnvelopeQueue eq = new EnvelopeQueue();
        Communicator communicator = new Communicator(config);
        Listener listener = new Listener(communicator,eq);
        Thread t = new Thread(listener);
        ComponentInfo agentInfo = new ComponentInfo((short) 1001, ComponentInfo.PossibleAgentType.BrilliantStudent);
        JoinGame jg1 = new JoinGame((short) 10, "A00123", "Joe", "Jones", agentInfo);

        Envelope sent = new Envelope(jg1, InetAddress.getByName("localhost"),config.getLocalPort());

        t.start(); // spin up the thread;

        communicator.send(sent);
        communicator.send(sent);

        Envelope e = eq.pop();
        while(e== null){
            e = eq.pop();
        }

        assertNotNull(e);
        JoinGame jg2 = (JoinGame) e.getMessage();
        assertEquals(jg1.getGameId(), jg2.getGameId());
        assertEquals(jg1.getANumber(), jg2.getANumber());
        assertEquals(jg1.getFirstName(), jg2.getFirstName());
        assertEquals(jg1.getLastName(), jg2.getLastName());


        e = eq.pop();
        while(e== null){
            e = eq.pop();
        }

        assertNotNull(e);
        jg2 = (JoinGame) e.getMessage();
        assertEquals(jg1.getGameId(), jg2.getGameId());
        assertEquals(jg1.getANumber(), jg2.getANumber());
        assertEquals(jg1.getFirstName(), jg2.getFirstName());
        assertEquals(jg1.getLastName(), jg2.getLastName());
    }
}
