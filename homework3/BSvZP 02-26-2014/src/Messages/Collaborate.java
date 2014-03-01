package Messages;

import java.io.NotActiveException;
import java.net.UnknownHostException;

import org.omg.CORBA.portable.ApplicationException;

import Common.ByteList;
import Common.Tick;

public class Collaborate extends Request
{
	private static short ClassId;
	public Tick EnablingTick; 
    private static int MinimumEncodingLength;

    public Collaborate()
    {
    	super(PossibleTypes.Collaborate);
    }

    public Collaborate(Tick tick)
    {
    	super(PossibleTypes.Collaborate);
        EnablingTick = tick;
    }

    //new 
    public static Collaborate Create(ByteList messageBytes) throws ApplicationException, Exception
    {
        Collaborate result = null;

        if (messageBytes == null || messageBytes.getRemainingToRead() < Collaborate.getMinimumEncodingLength())
            throw new ApplicationException("Invalid message byte array", null);
        else if (messageBytes.PeekInt16() != Collaborate.getClassId())
            throw new ApplicationException("Invalid message class id", null);
        else
        {
            result = new Collaborate();
            result.Decode(messageBytes);
        }
        return result;
    }
    
    @Override
    public void Encode(ByteList bytes) throws Exception
    {
        bytes.Add(Collaborate.getClassId());                              // Write out this class id first

        short lengthPos = bytes.getCurrentWritePosition();    // Get the current write position, so we
                                                                // can write the length here later
        bytes.Add((short)0);                             // Write out a place holder for the length
        super.Encode(bytes);                              // Encode the part of the object defined
                                                     // by the base class

        bytes.Add(EnablingTick);
        Integer lengthinBytes = (bytes.getCurrentWritePosition() - lengthPos - 2);
        short length = lengthinBytes.shortValue();
        bytes.WriteInt16To(lengthPos, length);           // Write out the length of this object        
    }

    @Override
    public void Decode(ByteList bytes) throws Exception
    {
    	short objType = bytes.GetInt16();
        short objLength = bytes.GetInt16();

        bytes.SetNewReadLimit(objLength);

        super.Decode(bytes);

        EnablingTick = (Tick) bytes.GetDistributableObject();

        bytes.RestorePreviosReadLimit();
    }
    
    public Tick getEnablingTick() {
		return EnablingTick;
	}
	
    public void setEnablingTick(Tick enablingTick) {
		EnablingTick = enablingTick;
	}
	
    public static int getMinimumEncodingLength() {
    	MinimumEncodingLength =  4                // Object header
    							+ 1;             // EnablingTick
    	return MinimumEncodingLength;
	}
	
    public static short getClassId()
	{ 
		ClassId =  (short) MESSAGE_CLASS_IDS.Collaborate.getValue();
		return ClassId;
	}
	
    @Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MESSAGE_CLASS_IDS MessageTypeId() {
		return Message.MESSAGE_CLASS_IDS.Collaborate;
	}
}
