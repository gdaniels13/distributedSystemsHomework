package Common;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.portable.ApplicationException;

public class Bomb extends DistributableObject
{
	private static short ClassId;
	public short CreatorId;
    public ArrayList<Excuse> Excuses;
    public ArrayList<WhiningTwine> Twine; 
    public Tick BuiltOnTick; 
    private static int MinimumEncodingLength; 
       
    public Bomb()
    {
        Excuses = new ArrayList<Excuse>();
        Twine = new ArrayList<WhiningTwine>();
    }
	
    public Bomb(short creatorId, ArrayList<Excuse> excuses, ArrayList<WhiningTwine> twine, Tick builtOnTick)
    {
        setCreatorId(creatorId);
        setExcuses(excuses);
        setTwine(twine);
        setBuiltOnTick(builtOnTick);
    }

    public static Bomb Create(ByteList bytes) throws Exception
    {
        Bomb result = new Bomb();
        result.Decode(bytes);
        return result;
    }

	public short getClassId()
	{ 
		ClassId =  (short) DISTRIBUTABLE_CLASS_IDS.Bomb.getValue();
		return ClassId;
	}
    
	public short getCreatorId() {
		return CreatorId;
	}

	public void setCreatorId(short creatorId) {
		CreatorId = creatorId;
	}

	public ArrayList<Excuse> getExcuses() {
		return Excuses;
	}

	public void setExcuses(ArrayList<Excuse> excuses) {
		Excuses = excuses;
	}

	public List<WhiningTwine> getTwine() {
		return Twine;
	}

	public void setTwine(ArrayList<WhiningTwine> twine) {
		Twine = twine;
	}

	public Tick getBuiltOnTick() {
		return BuiltOnTick;
	}

	public void setBuiltOnTick(Tick builtOnTick) {
		BuiltOnTick = builtOnTick;
	}

	public static int getMinimumEncodingLength() {
		MinimumEncodingLength = 4               // Object header
                				+ 2            // CreatorId
                				+ 2            // Excuses
                				+ 2            // Twine
                				+ 1;           //Tick.getMinimumEncodingLength();
		return MinimumEncodingLength;
	}

	@Override
	public void Encode(ByteList bytes) throws UnknownHostException, Exception 
	{
		 bytes.Add(getClassId());                             // Write out the class type

         short lengthPos = bytes.getCurrentWritePosition();   // Get the current write position, so we
                                                         // can write the length here later
         bytes.Add((short) 0);                           // Write out a place holder for the length
         bytes.Add(CreatorId);                           // Write out Creator's Id

         if (Excuses == null) Excuses = new ArrayList<Excuse>();
         bytes.Add((short)(Excuses.size()));
         for (Excuse excuse : Excuses)
             excuse.Encode(bytes);

         if (Twine == null) Twine = new ArrayList<WhiningTwine>();
         bytes.Add((short)(Twine.size()));
         for (WhiningTwine twine : Twine)
             twine.Encode(bytes);

         if (BuiltOnTick == null) BuiltOnTick = new Tick(0, 0);
         BuiltOnTick.Encode(bytes);

         short length = (short) (bytes.getCurrentWritePosition() - lengthPos - 2);
         
         bytes.WriteInt16To(lengthPos, length);
		      // Write out the length of this object        
		
	}

	@Override
	protected void Decode(ByteList bytes) throws Exception 
	{
		if (bytes == null || bytes.getRemainingToRead() < getMinimumEncodingLength())
			throw new ApplicationException("Invalid byte array", null);
		else if (bytes.PeekInt16() != getClassId())
				throw new ApplicationException("Invalid class id", null);
			 else
				{
				    short objType = bytes.GetInt16();
				    short objLength = bytes.GetInt16();

				    bytes.SetNewReadLimit(objLength);

				    CreatorId = bytes.GetInt16();

				    Excuses = new ArrayList<Excuse>();
				    int count = bytes.GetInt16();
				    for (int i = 0; i < count; i++)
				        Excuses.add(Excuse.Create(bytes));

				    Twine = new ArrayList<WhiningTwine>();
				    count = bytes.GetInt16();
				    for (int i = 0; i < count; i++)
				        Twine.add(WhiningTwine.Create(bytes));

				    BuiltOnTick = (Tick) Tick.Create(bytes);

				    bytes.RestorePreviosReadLimit();
				}
	}

}
