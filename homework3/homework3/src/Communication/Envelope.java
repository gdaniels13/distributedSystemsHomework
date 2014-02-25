package Communication;

import Messages.Message;

import java.lang.Override;
import java.lang.String;
import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/14/14
 * Time: 2:54 PM
 */
public class Envelope
{
	private Message message;
	private Endpoint address;
	private DatagramPacket packet;

	public Envelope(Message message, InetAddress address, int port)
	{
		this.message = message;
		this.address =  new Endpoint(port,address);
	}

	public Envelope(DatagramPacket info)
	{
		//this.message = new Message(new String(info.getData()));
		this.address = new Endpoint(info.getPort(),info.getAddress());
	}

	public DatagramPacket toDatagramPacket(){

		DatagramPacket p = new DatagramPacket(message.toString().getBytes(),
													message.toString().getBytes().length,address.getAddress(),  address.getPort());
		return p;
	}


	public Message getMessage()
	{
		return message;
	}

	public void setMessage(Message message)
	{
		this.message = message;
	}

	public Endpoint getAddress()
	{
		return address;
	}

	public void setAddress(Endpoint address)
	{
		this.address = address;
	}



	@Override
	public String toString()
	{
		return "Communication.Envelope{" +
						"message=" + message.toString() +
						"\n, address=" + address +
						'}';
	}
}
