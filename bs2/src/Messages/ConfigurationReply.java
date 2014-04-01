package Messages;

import java.io.NotActiveException;
import java.net.UnknownHostException;
import org.omg.CORBA.portable.ApplicationException;
import Common.ByteList;
import Common.GameConfiguration;

public class ConfigurationReply extends Reply
{
	private static short ClassId; 
	public GameConfiguration Configuration; 
    private static int MinimumEncodingLength ; 
    
    protected ConfigurationReply() { } 
     
    public ConfigurationReply(PossibleStatus status, GameConfiguration config, String...note)
    {
    	super(Reply.PossibleTypes.ConfigurationReply, status, ((note.length == 1) ? note[0] : null));
    	Configuration = config;
    }

    //new 
    public static ConfigurationReply Create(ByteList messageBytes) throws Exception
    {
        ConfigurationReply result = null;

        if (messageBytes==null || messageBytes.getRemainingToRead()< getMinimumEncodingLength())
            throw new ApplicationException("Invalid message byte array", null);
        if (messageBytes.PeekInt16() != ClassId)
            throw new ApplicationException("Invalid message class id", null);
        else
        {
            result = new ConfigurationReply();
            result.Decode(messageBytes);
        }

        return result;
    }
    
    @Override 
    public void Encode(ByteList bytes) throws NotActiveException, UnknownHostException, Exception
    {
        bytes.Add(ClassId);                           // Write out this class id first

        short lengthPos = bytes.getCurrentWritePosition();   // Get the current write position, so we
                                                            // can write the length here later
        bytes.Add((short) 0);                           // Write out a place holder for the length

        super.Encode(bytes);                             // Encode stuff from base class

        bytes.Add(Configuration);

        short length = (short)(bytes.getCurrentWritePosition() - lengthPos - 2);
        bytes.WriteInt16To(lengthPos, length);          // Write out the length of this object        

    }

    @Override 
    public void Decode(ByteList bytes) throws Exception
    {
        short objType = bytes.GetInt16();
        short objLength = bytes.GetInt16();

        bytes.SetNewReadLimit(objLength);

        super.Decode(bytes);

        Configuration = (GameConfiguration) bytes.GetDistributableObject();
        
        bytes.RestorePreviosReadLimit();
    }

    
	public GameConfiguration getConfiguration() {
		return Configuration;
	}
	public void setConfiguration(GameConfiguration configuration) {
		Configuration = configuration;
	}
	public static int getMinimumEncodingLength() {
		MinimumEncodingLength =  4                // Object header
                   					+ GameConfiguration.getMinimumEncodingLength();
		return MinimumEncodingLength;
	}
	public short getClassId() {
		return (short)MESSAGE_CLASS_IDS.ConfigurationReply.getValue();
	}
	public Message.MESSAGE_CLASS_IDS MessageTypeId() 
	{ 
		return Message.MESSAGE_CLASS_IDS.fromShort(ClassId); 
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
