package Common;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.portable.ApplicationException;

public class WhiningTwine extends DistributableObject
{
	private static short ClassId;
    public short CreatorId;
    public ArrayList<Tick> Ticks;
    public Tick RequestTick;
    private static int MinimumEncodingLength;
   
    public WhiningTwine()
    {
        Ticks = new ArrayList<Tick>();
    }

    public WhiningTwine(short creatorId, ArrayList<Tick> ticks, Tick requestTick)
    {
        setCreatorId(creatorId);
        Ticks = ticks;
        RequestTick = requestTick;
    }

    //new 
    public static WhiningTwine Create(ByteList bytes) throws Exception
    {
        WhiningTwine result = new WhiningTwine();
        result.Decode(bytes);
        return result;
    }
    
    public short getCreatorId() {
		return CreatorId;
	}

	public void setCreatorId(short creatorId) {
		CreatorId = creatorId;
	}

	public List<Tick> getTicks() {
		return Ticks;
	}

	public void setTicks(ArrayList<Tick> ticks) {
		Ticks = ticks;
	}

	public Tick getRequestTick() {
		return RequestTick;
	}

	public void setRequestTick(Tick requestTick) {
		RequestTick = requestTick;
	}

	public static int getMinimumEncodingLength() {
		MinimumEncodingLength =  4              // Object header
                					+ 2            // Id
                					+ 2            // List of ticks
                					+ Tick.getMinimumEncodingLength();
		return MinimumEncodingLength;
	}

	public static short getClassId() {
		ClassId =  (short)DISTRIBUTABLE_CLASS_IDS.WhiningTwine.getValue();
		return ClassId;
	}
	
	@Override
	public void Encode(ByteList bytes) throws UnknownHostException, Exception 
	{
		 bytes.Add(getClassId());                             // Write out the class type

         short lengthPos = bytes.getCurrentWritePosition();   // Get the current write position, so we
                                                         // can write the length here later

         bytes.Add((short) 0);                           // Write out a place holder for the length

         bytes.Add(CreatorId);                           // Write out Creator's Id

         if (Ticks == null) Ticks = new ArrayList<Tick>();    // Write out the ticks that made up this whining twine
         Integer temp = Ticks.size();
         bytes.Add(temp.shortValue());
         for (Tick tick : Ticks)
             bytes.Add(tick);

         bytes.Add(RequestTick);

         short length = (short)(bytes.getCurrentWritePosition() - lengthPos - 2);
         
         bytes.WriteInt16To(lengthPos, length);
		       // Write out the length of this object 
		
	}

	@Override
	protected void Decode(ByteList bytes) throws Exception
	{
		if ((bytes == null) || (bytes.getRemainingToRead() < getMinimumEncodingLength()))
			throw new ApplicationException("Invalid byte array", null);
		else if (bytes.PeekInt16() != getClassId())
			throw new ApplicationException("Invalid class id", null);
			else
			{
				     short objType = bytes.GetInt16();
				     short objLength = bytes.GetInt16();

				     bytes.SetNewReadLimit(objLength);

				     CreatorId = bytes.GetInt16();
				     Ticks = new ArrayList<Tick>();
				     int count = bytes.GetInt16();
				     for (int i = 0; i < count; i++)
				         Ticks.add((Tick) bytes.GetDistributableObject());
				     
				     RequestTick = (Tick) bytes.GetDistributableObject();

				     bytes.RestorePreviosReadLimit();
			 }
	}

}
