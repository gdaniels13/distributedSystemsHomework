package Messages;

import java.io.NotActiveException;
import java.net.UnknownHostException;

import org.omg.CORBA.portable.ApplicationException;

import Common.ByteList;

public class GetStatus extends Request
{
	private static short ClassId;
	private static  int MinimumEncodingLength;

	public GetStatus() 
    {
    	super(PossibleTypes.GetStatus);
    }

    //new
    public static GetStatus Create(ByteList messageBytes) throws ApplicationException, Exception
    {
        GetStatus result = null;

        if (messageBytes == null || messageBytes.getRemainingToRead() < GetStatus.getMinimumEncodingLength())
            throw new ApplicationException("Invalid message byte array", null);
        else if (messageBytes.PeekInt16() != GetStatus.getClassId())
            throw new ApplicationException("Invalid message class id", null);
        else
        {
            result = new GetStatus();
            result.Decode(messageBytes);
        }

        return result;
    }
	
    @Override
    public void Encode(ByteList bytes) throws Exception
    {
        bytes.Add(GetStatus.getClassId());                              // Write out this class id first

        short lengthPos = bytes.getCurrentWritePosition();    // Get the current write position, so we
                                                                // can write the length here later
        bytes.Add((short)0);                             // Write out a place holder for the length


        super.Encode(bytes);                              // Encode the part of the object defined
                                                         // by the base class
        short length = (short) (bytes.getCurrentWritePosition() - lengthPos - 2);
        bytes.WriteInt16To(lengthPos, length);           // Write out the length of this object        
    }

    @Override
    public void Decode(ByteList bytes) throws Exception
    {

        short objType = bytes.GetInt16();
        short objLength = bytes.GetInt16();

        bytes.SetNewReadLimit(objLength);

        super.Decode(bytes);

        bytes.RestorePreviosReadLimit();
    }
    
	public static short getClassId() {
		ClassId =  (short)MESSAGE_CLASS_IDS.GetStatus.getValue();
		return ClassId;
		}
	 
     public static int getMinimumEncodingLength() {
    	 MinimumEncodingLength =  4;				// Object header
    	 return MinimumEncodingLength;
	}

	protected GetStatus(PossibleTypes type) {
		super(type);
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}
}