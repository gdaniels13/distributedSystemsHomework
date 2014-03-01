package Messages;

import java.io.NotActiveException;
import java.net.UnknownHostException;

import org.omg.CORBA.portable.ApplicationException;

import Common.ByteList;
import Common.DistributableObject;
import Messages.Message;

public class AckNak extends Reply
{
	 private static short ClassId;
	 
	 public int IntResult;
     public DistributableObject ObjResult;
     public String Message;
     private static int MinimumEncodingLength;

     
     public Message.MESSAGE_CLASS_IDS MessageTypeId()
     { 
    	 return Messages.Message.MESSAGE_CLASS_IDS.AckNak;
     }
     
     
     protected AckNak() { }

     public AckNak(PossibleStatus status, int intResult, DistributableObject objResult, String message, String note) 
     {
    	 super(Reply.PossibleTypes.AckNak, status, note);
         IntResult = intResult;
         ObjResult = objResult;
         Message = message;
     }

     public AckNak(PossibleStatus status, int intResult) 
     {
    	 this(status, intResult, null, "", "");
     }
     
     public AckNak(PossibleStatus status, int intResult, String message) 
     {
    	 this(status, intResult, null, message, "");
     }
     
     public AckNak(PossibleStatus status, DistributableObject objResult)
     {
    	 this(status, 0, objResult, "", ""); 
     }
     
     public AckNak(PossibleStatus status, DistributableObject objResult, String message)
     { 
    	 this(status, 0, objResult, message, "");
     }

     //new
     public static AckNak Create(ByteList messageBytes) throws ApplicationException, Exception
     {
         AckNak result = null;

         if (messageBytes==null || messageBytes.getRemainingToRead() < AckNak.getMinimumEncodingLength())
             throw new ApplicationException("Invalid message byte array", null);
         if (messageBytes.PeekInt16() != AckNak.getClassId())
             throw new ApplicationException("Invalid message class id", null);
         else
         {
             result = new AckNak();
             messageBytes.update();
             result.Decode(messageBytes);
         }

         return result;
     }
     
     public void Encode(ByteList bytes) throws NotActiveException, UnknownHostException, Exception
     {
         bytes.Add(AckNak.getClassId());                           // Write out this class id first
         bytes.update();
         
         short lengthPos = bytes.getCurrentWritePosition();   // Get the current write position, so we
                                                             // can write the length here later
         bytes.Add((short) 0);                           // Write out a place holder for the length
         bytes.update();
         
         super.Encode(bytes);                             // Encode stuff from base class

         if (Message == null)
        	 Message = "";
         bytes.AddObjects(getIntResult(), getObjResult(), getMessage());
         bytes.update();
         
         short length = (short) (bytes.getCurrentWritePosition() - lengthPos - 2);
         bytes.WriteInt16To(lengthPos, length);          // Write out the length of this object        

     }

     public void Decode(ByteList bytes) throws Exception
     {
         short objType = bytes.GetInt16();
         short objLength = bytes.GetInt16();

         bytes.SetNewReadLimit(objLength);
         bytes.update();
         super.Decode(bytes);

         IntResult = bytes.GetInt32();
         ObjResult = bytes.GetDistributableObject();
         Message = bytes.GetString();

         bytes.RestorePreviosReadLimit();
     }
	
     public int getIntResult() {
		return IntResult;
	}

     public void setIntResult(int intResult) {
		IntResult = intResult;
	}

     public DistributableObject getObjResult() {
		return ObjResult;
	}

     public void setObjResult(DistributableObject objResult) {
		ObjResult = objResult;
	}

     public String getMessage() {
		return Message;
	}

     public void setMessage(String message) {
		Message = message;
	}

     public static int getMinimumEncodingLength() {
    	 MinimumEncodingLength =  4                // Object header
    			 					+ 2              // IntResult
    			 					+ 1              // ObjResult
    			 					+ 2              // Message
    			 					+ 1;
    	 return MinimumEncodingLength;
	}

     
	public static short getClassId() {
		ClassId = (short)MESSAGE_CLASS_IDS.AckNak.getValue();
		return ClassId;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

}
