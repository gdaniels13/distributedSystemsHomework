package Communication;

import java.io.IOException;
import java.net.*;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/19/14
 * Time: 4:30 PM
 */
public class Communicator
{

	private static Communicator instance = null;
	private DatagramSocket socket;
	private int port;
	private int messageLength;
	private byte[] receiveBuffer;
  private DatagramPacket receivePacket;


	private Communicator(){
		port = Config.port;
		messageLength = Config.messageLength;
		receiveBuffer = new byte[messageLength];
		receivePacket = new DatagramPacket(receiveBuffer,messageLength);

		try
		{
			socket = new DatagramSocket(port);
		}
		catch(SocketException e)
		{
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
//		catch(UnknownHostException e)
//		{
//			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//		}
	}

	private static void init(){
		if(instance==null)
		{
			instance = new Communicator();
		}
	}

	public static Envelope receive(){
		init();
		return instance.listen(Config.listenTimeout);
	}

	public Envelope listen(int listenTimeout)
	{
		try
		{
			socket.setSoTimeout(Config.listenTimeout);
			socket.receive(receivePacket);
		}
		catch(SocketTimeoutException e){
			return null;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return new Envelope(receivePacket);
	}

	public static void send(Envelope e){
		init();
		instance.sender(e);
	}

	private void sender(Envelope envelope){
		try
		{
			DatagramPacket d = envelope.toDatagramPacket();
			socket.send(d);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
