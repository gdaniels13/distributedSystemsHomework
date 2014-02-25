package Communications;

import java.net.*;

import static Communications.Config.*;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/19/14
 * Time: 9:47 AM
 */
public class Communicator
{

	private static Communicator instance = null;
	private DatagramSocket socket;
	private int port = 9876;
	private int messageLength = 1024;
	private byte[] receiveBuffer;
  private DatagramPacket receivePacket;

	private Communicator(){
		port = Config.port;
		messageLength = Config.messageLength;
		receiveBuffer = new byte[messageLength];
		receivePacket = new DatagramPacket(receiveBuffer,messageLength);

		try
		{
			socket = new DatagramSocket(port,InetAddress.getByName(Config.address));
		}
		catch(SocketException e)
		{
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		catch(UnknownHostException e)
		{
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

	private static void init(){
		if(instance==null)
		{
			instance = new Communicator();
		}
	}

	public static Envelope getEnvelope(){
		init();

		if(socket.)


		return null;
	}


}


