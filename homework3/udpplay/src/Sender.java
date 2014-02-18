import java.io.IOException;
import java.net.*;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/18/14
 * Time: 8:43 AM
 */
public class Sender
{
	public void send(String message){
		try 	{

		DatagramSocket clientSocket = new DatagramSocket();

      InetAddress IPAddress = InetAddress.getByName("localhost");

		 	byte[] sendData;
      byte[] receiveData = new byte[1024];
      String sentence = message;
      sendData = sentence.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
      clientSocket.send(sendPacket);


//			//receive the response
//			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//			clientSocket.receive(receivePacket);
//			String modifiedSentence = new String(receivePacket.getData());
//			System.out.println("FROM SERVER:" + modifiedSentence);
//			clientSocket.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
