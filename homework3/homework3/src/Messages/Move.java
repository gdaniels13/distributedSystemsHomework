package Messages;

import org.omg.CORBA.portable.ApplicationException;
import Common.ByteList;
import Common.FieldLocation;
import Common.Tick;

public class Move  extends Request
{
	private static short ClassId;
	public short ComponentId;
    public FieldLocation ToSquare;
    public Tick EnablingTick; 
    private static int MinimumEncodingLength;
   
	protected Move(PossibleTypes type) {
		super(type);
	}

    public Move() 
    {
    	super(PossibleTypes.Move);
    }

    public Move(short componentId, FieldLocation toSquare, Tick tick)
    {
    	super(PossibleTypes.Move);
        ComponentId = componentId;
        ToSquare = toSquare;
        EnablingTick = tick;
    }

    //new 
    public static Move Create(ByteList messageBytes) throws ApplicationException, Exception
    {
        Move result = null;

        if (messageBytes == null || messageBytes.getRemainingToRead() < Move.getMinimumEncodingLength())
            throw new ApplicationException("Invalid message byte array", null);
        else if (messageBytes.PeekInt16() != Move.getClassId())
            throw new ApplicationException("Invalid message class id", null);
        else
        {
            result = new Move();
            result.Decode(messageBytes);
        }

        return result;
    } 

    @Override 
    public void Encode(ByteList bytes) throws Exception
    {
        bytes.Add(Move.getClassId());                              // Write out this class id first
        short lengthPos = bytes.getCurrentWritePosition();    // Get the current write position, so we
                                                                // can write the length here later
        bytes.Add((short)0);                             // Write out a place holder for the length
        super.Encode(bytes);                              // Encode the part of the object defined
                                                         // by the base class
        bytes.AddObjects(ComponentId, ToSquare, EnablingTick);
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
        ComponentId = bytes.GetInt16();
        ToSquare = (FieldLocation) bytes.GetDistributableObject();
        EnablingTick = (Tick) bytes.GetDistributableObject();
        bytes.RestorePreviosReadLimit();
    }
    
	public static short getClassId()
	{
		ClassId =  (short) MESSAGE_CLASS_IDS.Move.getValue();
		return ClassId;
	}

    public short getComponentId() {
		return ComponentId;
	}

	public void setComponentId(short componentId) {
		ComponentId = componentId;
	}

	public FieldLocation getToSquare() {
		return ToSquare;
	}

	public void setToSquare(FieldLocation toSquare) {
		ToSquare = toSquare;
	}

	public Tick getEnablingTick() {
		return EnablingTick;
	}

	public void setEnablingTick(Tick enablingTick) {
		EnablingTick = enablingTick;
	}

	public static int getMinimumEncodingLength()
	{
		MinimumEncodingLength =  4                // Object header
                				+ 2              // ComponentId
                				+ 1
                				+ 1;
		System.out.println("Move.MinimumEncodingLength" + MinimumEncodingLength );
		return MinimumEncodingLength;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}
}