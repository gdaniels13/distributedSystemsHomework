package Messages;

import org.omg.CORBA.portable.ApplicationException;
import Common.ByteList;
import Common.Tick;

public class GetResource extends Request 
{
	 private static short ClassId;
	 public short GameId;
     public PossibleResourceType GetType; 
     public Tick EnablingTick;
     private static int MinimumEncodingLength;
		
	 public enum PossibleResourceType
     {
         GameConfiguration(1),
         PlayingFieldLayout(2),
         BrillianStudentList(3),
         ExcuseGeneratorList(4),
         WhiningSpinnerList(5),
         ZombieProfessorList(6),
         Excuse(7),
         WhiningTwine(8),
         Tick(9);
         private int value;
         PossibleResourceType(int value)
         {
        	 this.value = value;
         }
         
         public int getValue() {
 			return value;
 		}
         public static PossibleResourceType convert(byte value) {
             return PossibleResourceType.values()[value];
         }     
     }
     
     public GetResource()
     {
    	 super(PossibleTypes.GetResource);
     }

     public GetResource(short gameId, PossibleResourceType type, Tick tick)
     {
    	 super(PossibleTypes.GetResource);
         GameId = gameId;
         GetType = type;
         EnablingTick = tick;
     }

     //new
     public static GetResource Create(ByteList messageBytes) throws ApplicationException, Exception
     {
         GetResource result = null;

         if (messageBytes == null || messageBytes.getRemainingToRead() < GetResource.getMinimumEncodingLength())
             throw new ApplicationException("Invalid message byte array", null);
         else if (messageBytes.PeekInt16() != GetResource.getClassId())
             throw new ApplicationException("Invalid message class id", null);
         else
         {
             result = new GetResource();
             result.Decode(messageBytes);
         }

         return result;
     }

    @Override
     public void Encode(ByteList bytes) throws Exception
     {
         bytes.Add(GetResource.getClassId());                              // Write out this class id first

         short lengthPos = bytes.getCurrentWritePosition();    // Get the current write position, so we
                                                                 // can write the length here later
         bytes.Add((short)0);                             // Write out a place holder for the length


         super.Encode(bytes);                              // Encode the part of the object defined
         													// by the base class
         bytes.AddObjects(GameId, (byte) GetType.getValue(), EnablingTick);
         int lengthinBytes = (bytes.getCurrentWritePosition() - lengthPos - 2);
         short length = (short) lengthinBytes;
         bytes.WriteInt16To(lengthPos, length);           // Write out the length of this object        
     }

     public void Decode(ByteList bytes) throws ApplicationException, Exception
     {

         short objType = bytes.GetInt16();
         short objLength = bytes.GetInt16();

         bytes.SetNewReadLimit(objLength);

         super.Decode(bytes);

         GameId = bytes.GetInt16();
         GetType = PossibleResourceType.convert(bytes.GetByte());
         EnablingTick = (Tick) bytes.GetDistributableObject();

         bytes.RestorePreviosReadLimit();
     }

     public static int getMinimumEncodingLength()
     {        MinimumEncodingLength =  4                // Object header
                    					+ 2              // GameId
                    					+ 1              // GetType
                    					+ 1;
     	return MinimumEncodingLength;
     }
  	
     public static short getClassId()
 	{ 
 		ClassId = (short) MESSAGE_CLASS_IDS.GetResource.getValue(); 
    	return ClassId;
 	}
	
     public short getGameId() {
		return GameId;
	}

	 public void setGameId(short gameId) {
		GameId = gameId;
	}

	 public PossibleResourceType getGetType() {
		return GetType;
	}

	 public void setGetType(PossibleResourceType getType) {
		GetType = getType;
	}

	 public Tick getEnablingTick() {
		return EnablingTick;
	}

	 public void setEnablingTick(Tick enablingTick) {
		EnablingTick = enablingTick;
	}

	 protected GetResource(PossibleTypes type) {
		super(type);
	
	}

	@Override
	 public int compareTo(Object o) {
			return 0;
	}

}
