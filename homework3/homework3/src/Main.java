import Common.ComponentInfo;
import Communication.Communicator;
import Communication.Envelope;
import Communication.Listener;
import Messages.JoinGame;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {


    public static void main(String[] args) throws UnknownHostException, InterruptedException
		{
//			Listener listener = new Listener();
//			Thread t = new Thread(listener);
////			listener.start();
////			t.start();
			ComponentInfo agentInfo = new ComponentInfo((short) 1001, ComponentInfo.PossibleAgentType.BrilliantStudent);
			JoinGame jg = new JoinGame((short) 10, "A00123", "Joe", "Jones", agentInfo);

			Envelope e = new Envelope(jg ,InetAddress.getByName("162.248.11.179"),9876);
			Communicator.send(e);
			Thread.sleep(100);
			Envelope ne = Communicator.receive();
			System.out.println(ne.toString());
		}
}
