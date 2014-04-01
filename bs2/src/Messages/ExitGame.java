package Messages;

import java.io.NotActiveException;
import java.net.UnknownHostException;

import org.omg.CORBA.portable.ApplicationException;

import Common.ByteList;

public class ExitGame extends Request 
{
	private static short ClassId; 
	public short GameId; 
    private static int MinimumEncodingLength; 
    
    
    
    
	public ExitGame() { super(PossibleTypes.ExitGame); }
	
	protected ExitGame(short gameId)
    {
    	super(PossibleTypes.ExitGame);
    	GameId = gameId;
    }
	
	public static ExitGame Create(ByteList messageBytes) throws Exception
    {
        ExitGame result = null;

        if (messageBytes == null || messageBytes.getRemainingToRead() < getMinimumEncodingLength())
            throw new ApplicationException("Invalid message byte array", null);
        else if (messageBytes.PeekInt16() != ClassId)
            throw new ApplicationException("Invalid message class id", null);
        else
        {
            result = new ExitGame();
            result.Decode(messageBytes);
        }

        return result;
    }
	
	
	  @Override 
	  public void Encode(ByteList bytes) throws NotActiveException, UnknownHostException, Exception
      {
          bytes.Add(ClassId);                              // Write out this class id first

          short lengthPos = bytes.getCurrentWritePosition();    // Get the current write position, so we
                                                                  // can write the length here later
          bytes.Add((short)0);                             // Write out a place holder for the length


          super.Encode(bytes);                              // Encode the part of the object defined
                                                                  // by the base class
          bytes.AddObjects(GameId);

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

          GameId = bytes.GetInt16();

          bytes.RestorePreviosReadLimit();
      }
      
	public short getClassId() {
    	ClassId = (short)MESSAGE_CLASS_IDS.JoinGame.getValue();
    	return ClassId;
	}
	
	public short getGameId() {
		return GameId;
	}

	public void setGameId(short gameId) {
		GameId = gameId;
	}

	public static int getMinimumEncodingLength() {
		MinimumEncodingLength =  4                // Object header
                					+ 2              // GameId
                					+ 1;
		return MinimumEncodingLength;
	}

		
	@Override
	public Message.MESSAGE_CLASS_IDS MessageTypeId() 
	{ 
		return Message.MESSAGE_CLASS_IDS.fromShort(ClassId); 
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
