import java.io.IOException;
import java.net.*;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/14/14
 * Time: 2:34 PM
 */
public class Listener implements Runnable
{

	DatagramSocket listenerSocket;
	int port = 9876;
	int messageLength = 1024;
	byte[] receiveBuffer;
	public Listener()
	{

		receiveBuffer = new byte[messageLength];
		try
		{
			listenerSocket = new DatagramSocket(port);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void run()
	{
		try{
			System.out.println("running");
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			while(true)
			{
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				listenerSocket.receive(receivePacket);

				String sentence = new String( receivePacket.getData());
				System.out.println("RECEIVED: " + sentence);
				InetAddress IPAddress = receivePacket.getAddress();
				//sender
				int port = receivePacket.getPort();
				String capitalizedSentence = sentence.toUpperCase();
				sendData = capitalizedSentence.getBytes();
				DatagramPacket sendPacket =
								new DatagramPacket(sendData, sendData.length, IPAddress, port);
				listenerSocket.send(sendPacket);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
}
