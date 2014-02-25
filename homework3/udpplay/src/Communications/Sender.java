package Communications;

import java.net.*;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/18/14
 * Time: 8:43 AM
 */
public class Sender
{
	public void send(Envelope envelope){
		try 	{

		DatagramSocket clientSocket = new DatagramSocket();

      InetAddress IPAddress = envelope.getAddress().getAddress();
			int port = envelope.getAddress().getPort();
		 	byte[] sendData= envelope.getMessage().toString().getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
      clientSocket.send(sendPacket);
			clientSocket.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
