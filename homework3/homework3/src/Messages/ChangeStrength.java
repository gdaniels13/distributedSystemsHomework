package Messages;

import java.io.NotActiveException;
import java.net.UnknownHostException;
import org.omg.CORBA.portable.ApplicationException;
import Common.ByteList;

public class ChangeStrength extends Request
{
	private static short ClassId;
	public short DeltaValue;
    private static int MinimumEncodingLength;
     
  
    public ChangeStrength()
    {
    	super(PossibleTypes.ChangeStrength);
    }

    public ChangeStrength(short deltaValue)
    {
    	super(PossibleTypes.ChangeStrength);
        DeltaValue = deltaValue;
    }

    //new
    public static ChangeStrength Create(ByteList messageBytes) throws ApplicationException, Exception
    {
        ChangeStrength result = null;

        if (messageBytes == null || messageBytes.getRemainingToRead() < MinimumEncodingLength)
            throw new ApplicationException("Invalid message byte array", null);
        else if (messageBytes.PeekInt16() != ClassId)
            throw new ApplicationException("Invalid message class id", null);
        else
        {
            result = new ChangeStrength();
            result.Decode(messageBytes);
        }

        return result;
    }

    public void Encode(ByteList bytes) throws UnknownHostException, NotActiveException, Exception
    {
        bytes.Add(ClassId);                              // Write out this class id first

        short lengthPos = bytes.getCurrentWritePosition();    // Get the current write position, so we
                                                                // can write the length here later
        bytes.Add((short)0);                             // Write out a place holder for the length

        super.Encode(bytes);                              // Encode the part of the object defined
                                                             // by the base class
        bytes.Add(DeltaValue);  

        Integer currentLengthinBytes = (bytes.getCurrentWritePosition() - lengthPos - 2); 
        short length = currentLengthinBytes.shortValue();
        bytes.WriteInt16To(lengthPos, length);           // Write out the length of this object        
    }

    public void Decode(ByteList bytes) throws Exception
    {

        short objType = bytes.GetInt16();
        short objLength = bytes.GetInt16();

        bytes.SetNewReadLimit(objLength);

        super.Decode(bytes);

        DeltaValue = bytes.GetInt16();

        bytes.RestorePreviosReadLimit();
    }
    
    public static short getClassId()
 	{
 		return (short)MESSAGE_CLASS_IDS.ChangeStrength.getValue();
 	}
   
    public short getDeltaValue() {
		return DeltaValue;
	}

	public void setDeltaValue(short deltaValue) {
		DeltaValue = deltaValue;
	}

	public static int getMinimumEncodingLength() {
		MinimumEncodingLength =  4                // Object header
                				+ 2;             // Delta Value
		return MinimumEncodingLength;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
