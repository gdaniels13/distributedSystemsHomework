package Messages;

import org.omg.CORBA.portable.ApplicationException;
import Common.ByteList;

public class StartGame extends Request
{
	private static short ClassId;
	public short GameId;
    private static int MinimumEncodingLength;
	
   	protected StartGame(PossibleTypes type) {
		super(type);
	}

    public StartGame()
    {
    	super(PossibleTypes.StartGame);
    }

    public StartGame(short gameId)
    {
    	super(PossibleTypes.StartGame);
        GameId = gameId;
    }

    //new
    public static StartGame Create(ByteList bytes) throws ApplicationException, Exception
    {
        StartGame result = null;

        if (bytes == null || bytes.getRemainingToRead() < MinimumEncodingLength)
            throw new ApplicationException("Invalid message byte array", null);
        else if (bytes.PeekInt16() != ClassId)
            throw new ApplicationException("Invalid message class id", null);
        else
        {
            result = new StartGame();
            result.Decode(bytes);
        }

        return result;
    }

    @Override
    public void Encode(ByteList bytes) throws Exception
    {
        bytes.Add(ClassId);                              // Write out this class id first

        short lengthPos = bytes.getCurrentWritePosition();    // Get the current write position, so we
                                                                // can write the length here later
        bytes.Add((short)0);                             // Write out a place holder for the length


        super.Encode(bytes);                              // Encode the part of the object defined
                                                                // by the base class

        bytes.Add(GameId);

        short length = (short)(bytes.getCurrentWritePosition() - lengthPos - 2);
        bytes.WriteInt16To(lengthPos, length);           // Write out the length of this object        
    }

    @Override 
    public void Decode(ByteList bytes) throws ApplicationException, Exception
    {

        short objType = bytes.GetInt16();
        short objLength = bytes.GetInt16();

        bytes.SetNewReadLimit(objLength);

        super.Decode(bytes);

        GameId = bytes.GetInt16();

        bytes.RestorePreviosReadLimit();
    }

  	public short getGameId() {
		return GameId;
	}
	
  	public void setGameId(short gameId) {
		GameId = gameId;
	}
	
  	public static short getClassId() {
		return (short) MESSAGE_CLASS_IDS.StartGame.getValue();
	}
	
  	public static int getMinimumEncodingLength() {
		return 4                // Object header
                + 2;              // GameId
	}
	
  	@Override
	public int compareTo(Object o) {
		return 0;
	}
}