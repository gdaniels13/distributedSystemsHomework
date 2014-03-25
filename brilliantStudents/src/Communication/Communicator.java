package Communication;

import Agents.AgentCommon.Config;

import java.io.IOException;
import java.net.*;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/19/14
 * Time: 4:30 PM
 */
public class Communicator{
	private DatagramSocket socket;
    private DatagramPacket receivePacket;
    private int port;
    private int messageLength;
    private byte[] receiveBuffer;


	public Communicator(Config config){
		port = config.getLocalPort();
		messageLength = config.getMessageLength();
		receiveBuffer = new byte[messageLength];
		receivePacket = new DatagramPacket(receiveBuffer,messageLength);

		try{

				socket = new DatagramSocket(port);	

		}
		catch(SocketException e){
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

	public synchronized Envelope listen(){
		try
		{
			socket.receive(receivePacket);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return new Envelope(receivePacket);
	}

	public synchronized void send(Envelope envelope){
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
