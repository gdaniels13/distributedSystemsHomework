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
    DatagramPacket receivePacket;


	public Listener()
	{
		receiveBuffer = new byte[messageLength];
        receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

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
            while(true)
			{
                listenerSocket.receive(receivePacket);
				String sentence = new String( receivePacket.getData());
				System.out.println("RECEIVED: " + sentence);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
}
