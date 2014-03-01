package Messages;

import java.io.NotActiveException;
import java.net.UnknownHostException;

import org.omg.CORBA.portable.ApplicationException;

import Common.ByteList;

public class RemoveComponent extends Request
{
	private static short ClassId; 
	public short ComponentId; 
    private static int MinimumEncodingLength;
    
    public RemoveComponent()
    {
    	super(PossibleTypes.RemoveComponent);
    }

    public RemoveComponent(short componentId)
    {
    	super(PossibleTypes.RemoveComponent);
        ComponentId = componentId;
    }

    //new
    public static RemoveComponent Create(ByteList messageBytes) throws ApplicationException, Exception
    {
        RemoveComponent result = null;

        if (messageBytes == null || messageBytes.getRemainingToRead() < RemoveComponent.getMinimumEncodingLength())
            throw new ApplicationException("Invalid message byte array", null);
        else if (messageBytes.PeekInt16() != RemoveComponent.getClassId())
            throw new ApplicationException("Invalid message class id", null);
        else
        {
            result = new RemoveComponent();
            result.Decode(messageBytes);
        }

        return result;
    }
   
	protected RemoveComponent(PossibleTypes type) {
		super(type);
	}
	
		@Override
	public void Encode(ByteList bytes) throws Exception
    {
        bytes.Add(RemoveComponent.getClassId());                              // Write out this class id first
        short lengthPos = bytes.getCurrentWritePosition();    // Get the current write position, so we
                                                         // can write the length here later
        bytes.Add((short)0);                             // Write out a place holder for the length
        super.Encode(bytes);                             // Encode the part of the object defined
                                                         // by the base class
        bytes.Add(ComponentId);
        short length = (short)(bytes.getCurrentWritePosition() - lengthPos - 2);
        bytes.WriteInt16To(lengthPos, length);           // Write out the length of this object        
    }

    @Override 
	public void Decode(ByteList bytes) throws Exception
    {

		short objType = bytes.GetInt16();
        short objLength = bytes.GetInt16();

        bytes.SetNewReadLimit(objLength);
        super.Decode(bytes);
        ComponentId = bytes.GetInt16();
        bytes.RestorePreviosReadLimit();
    }

	public static short getClassId() {
		ClassId =  (short)MESSAGE_CLASS_IDS.RemoveComponent.getValue();
		System.out.println("RemoveComponent.ClassId " + ClassId);
		return ClassId;
	}
	
	public short getComponentId() {
		return ComponentId;
	}

	public void setComponentId(short componentId) {
		ComponentId = componentId;
	}

	public static int getMinimumEncodingLength() {
		MinimumEncodingLength =  4                // Object header
                				+ 2;             // ComponentId
		System.out.println("RemoveComponent.MinimumEncodingLength" + MinimumEncodingLength);
		return MinimumEncodingLength;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MESSAGE_CLASS_IDS MessageTypeId() {
		return Message.MESSAGE_CLASS_IDS.RemoveComponent;
	}
}
