package Communications;

import java.io.IOException;
import java.net.*;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/14/14
 * Time: 2:34 PM
 *
 */
public class Receiver implements Runnable
{

	private DatagramSocket listenerSocket;
	private int port = 9876;
	private int messageLength = 1024;
	private byte[] receiveBuffer;
  private DatagramPacket receivePacket;
	private boolean shouldRun;

	public Receiver(int port, int messageLength)
	{
		this.port = port;
		this.messageLength = messageLength;

		try
		{
			listenerSocket = new DatagramSocket(port);
			listenerSocket.setSoTimeout(50);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public Receiver()
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
		this.shouldRun= true;
   	try{
      while(shouldRun)
			{
				try{
					Envelope t = listen();
					EnvelopeQueue.push(t);
				}
				catch(SocketTimeoutException e){
					//do nothing
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		listenerSocket.close();
	}

	public Envelope listen() throws IOException,SocketTimeoutException
	{
		listenerSocket.receive(receivePacket);
		return new Envelope(receivePacket);
	}

	public void setRun(boolean t){
		this.shouldRun = t;
	}

}
