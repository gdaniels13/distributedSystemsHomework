package tests.CommunicationTester;

import Common.ComponentInfo;
import Communication.Communicator;
import Agents.AgentCommon.Config;
import Communication.Envelope;
import Messages.JoinGame;

import java.net.InetAddress;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/19/14
 * Time: 5:32 PM
 */
public class CommunicatorTest
{
	@org.junit.Test
	public void testSendReceive() throws Exception
	{
        Config config = new Config(new String("WG localhost 9877 9877 A00798340 Greg Daniels").split(" "));
        Communicator communicator = new Communicator(config);

        ComponentInfo agentInfo = new ComponentInfo((short) 1001, ComponentInfo.PossibleAgentType.BrilliantStudent);
        JoinGame jg1 = new JoinGame((short) 10, "A00123", "Joe", "Jones", agentInfo);

        Envelope sent = new Envelope(jg1, InetAddress.getByName("localhost"),config.getLocalPort());

        communicator.send(sent);
        Envelope received = communicator.listen();
        JoinGame jg2 = (JoinGame) received.getMessage();

        assertEquals(jg1.getGameId(), jg2.getGameId());
        assertEquals(jg1.getANumber(), jg2.getANumber());
        assertEquals(jg1.getFirstName(), jg2.getFirstName());
        assertEquals(jg1.getLastName(), jg2.getLastName());
	}

    //this tests connecting to a remote server.
    //the server should be running check that if this test fails
//    @org.junit.Test
//    public void testSendReceiveRemote() throws Exception
//    {
//        Config config = new Config(new String("WG thingsforreasons.com 9876 10000 A00798340 Greg Daniels").split(" "));
//        Communicator communicator = new Communicator(config);
//
//        ComponentInfo agentInfo = new ComponentInfo((short) 1001, ComponentInfo.PossibleAgentType.BrilliantStudent);
//        JoinGame jg1 = new JoinGame((short) 10, "A00123", "Joe", "Jones", agentInfo);
//
//        Envelope sent = new Envelope(jg1, config.getServerAddress() ,config.getServerPort());
//
//        communicator.send(sent);
//        Envelope received = communicator.listen();
//        JoinGame jg2 = (JoinGame) received.getMessage();
//
//        assertEquals(jg1.getGameId(), jg2.getGameId());
//        assertEquals(jg1.getANumber(), jg2.getANumber());
//        assertEquals(jg1.getFirstName(), jg2.getFirstName());
//        assertEquals(jg1.getLastName(), jg2.getLastName());
//    }
}
