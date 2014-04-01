package Messages;

import java.io.NotActiveException;
import java.net.UnknownHostException;
import org.omg.CORBA.portable.ApplicationException;
import Common.ByteList;


public abstract class Request extends Message
{
	private short ClassId;
	public enum PossibleTypes
    {
		JoinGame(2),
	    AddComponent(3),
	    RemoveComponent(4),
	    StartGame(5),
	    EndGame(6),
	    GetResource(7),
	    TickDelivery(8),
	    ValidateTick(9),
	    Move(10),
	    ThrowBomb(11),
	    Eat(12),
	    ChangeStrength(13),
	    Collaborate(14),
	    GetStatus(15),
	    ExitGame(16);
     
        private int value;
         
        PossibleTypes(int value)
        {
        	this.value = value;
        }
        public int getValue() {
        	return value;
        }
        public static PossibleTypes convert(byte value) {
        	return PossibleTypes.values()[value];
        }  
        
        public static PossibleTypes fromByte(int b) 
        {
        	PossibleTypes temp = null;
            for(PossibleTypes t : PossibleTypes.values())
            {
                if( t.value== b)
                    temp = t;
            } 
            return temp;  //or throw exception
        }
    }
	
	public PossibleTypes RequestType;
	private static  int MinimumEncodingLength;

    protected Request(PossibleTypes type)
    {
        RequestType = type;
    }

    //new
    public static Request Create(ByteList bytes) throws ApplicationException, Exception
    {
        Request result = null;
        
        if (bytes == null || bytes.getRemainingToRead() < Request.getMinimumEncodingLength())
            throw new ApplicationException("Invalid message byte array", null);

        short msgType = bytes.PeekInt16();
                
        if (msgType == (short) MESSAGE_CLASS_IDS.JoinGame.getValue())
        	result = JoinGame.Create(bytes);
        else if (msgType == (short) MESSAGE_CLASS_IDS.AddComponent.getValue())
        	result = AddComponent.Create(bytes);
        else if (msgType == (short) MESSAGE_CLASS_IDS.RemoveComponent.getValue()) 
        	result = RemoveComponent.Create(bytes);
        else if (msgType == (short) MESSAGE_CLASS_IDS.StartGame.getValue()) 
        	result =  StartGame.Create(bytes);
        else if (msgType == (short) MESSAGE_CLASS_IDS.EndGame.getValue()) 
        	result =  EndGame.Create(bytes);
        else if (msgType == (short) MESSAGE_CLASS_IDS.GetResource.getValue()) 
        	result = GetResource.Create(bytes);
        else if (msgType == (short) MESSAGE_CLASS_IDS.TickDelivery.getValue()) 
        	result = TickDelivery.Create(bytes);
        else if (msgType == (short) MESSAGE_CLASS_IDS.ValidateTick.getValue()) 
        	result = ValidateTick.Create(bytes);
        else if (msgType == (short) MESSAGE_CLASS_IDS.Move.getValue()) 
        	result = Move.Create(bytes) ;
        else if (msgType == (short) MESSAGE_CLASS_IDS.ThrowBomb.getValue()) 
        	result = ThrowBomb.Create(bytes);
        else if (msgType == (short) MESSAGE_CLASS_IDS.Eat.getValue()) 
        	result = Eat.Create(bytes);
        else if (msgType == (short) MESSAGE_CLASS_IDS.ChangeStrength.getValue()) 
        	result = ChangeStrength.Create(bytes);
        else if (msgType == (short) MESSAGE_CLASS_IDS.Collaborate.getValue()) 
        	result = Collaborate.Create(bytes);
        else if (msgType == (short) MESSAGE_CLASS_IDS.GetStatus.getValue()) 
        	result = GetStatus.Create(bytes);
        else
            throw new ApplicationException("Invalid Message Class Id", null);
        return result;
    }
	
	@Override 
    public void Encode(ByteList bytes) throws NotActiveException, UnknownHostException, Exception
    {
        bytes.Add(getClassId());                           // Write out this class id first
       // messageBytes.update();
        short lengthPos = bytes.getCurrentWritePosition();   // Get the current write position, so we
                                                               // can write the length here later
        bytes.Add((short) 0);                           // Write out a place holder for the length
       // messageBytes.update();
        bytes.getRemainingToRead();
        super.Encode(bytes);                             // Encode stuff from base class
       // messageBytes.update();
        bytes.Add((byte)RequestType.getValue());         // Write out a place holder for the length
        //messageBytes.update();
        short length = (short) (bytes.getCurrentWritePosition() - lengthPos - 2);
        bytes.WriteInt16To(lengthPos, length);          // Write out the length of this object        
    }

	@Override 
    public void Decode(ByteList messageBytes) throws Exception
    {
        short objType = messageBytes.GetInt16();
        short objLength = messageBytes.GetInt16();

        messageBytes.SetNewReadLimit(objLength);
        messageBytes.getRemainingToRead();
        super.Decode(messageBytes);
        
        int temp = (int) messageBytes.GetByte();
        RequestType = PossibleTypes.fromByte(temp);
        messageBytes.RestorePreviosReadLimit();
    }
	
 	public short getClassId()
	{
		ClassId =  (short) MESSAGE_CLASS_IDS.Request.getValue();
		System.out.println("Request.ClassId: " + ClassId);
		return ClassId;
	}
  
	public PossibleTypes getRequestType() {
		return RequestType;
	}

	public void setRequestType(PossibleTypes requestType) {
		RequestType = requestType;
	}
	
	public static int getMinimumEncodingLength()
	{
		MinimumEncodingLength = 4                // Object header
								+ 1;             // RequestType
		System.out.println("Request.MinimumEncodingLength" + MinimumEncodingLength);
		return MinimumEncodingLength;
	}
}