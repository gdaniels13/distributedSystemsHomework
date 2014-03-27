package Communication;

import Common.ByteList;
import Messages.Message;

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
		ByteList byteList = new ByteList();
        byteList.Add(info.getData());
        try
		{
            this.message = Message.Create(byteList);
        }
		catch(Exception e)
		{
            e.printStackTrace();
        }

        this.packet = info;
        this.address = new Endpoint(info.getPort(),info.getAddress());
	}

	public DatagramPacket toDatagramPacket(){
		ByteList bl = new ByteList();
		try
		{
			message.Encode(bl);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		byte ba[] =  bl.ToBytes();
		DatagramPacket p = new DatagramPacket(ba, ba.length, address.getAddress(),address.getPort());
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


}
