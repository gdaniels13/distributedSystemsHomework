package Messages;

import Common.AgentInfo;

public class StatusReply extends Reply {
	private static short ClassId;  
	
	public AgentInfo Info; 
    public static int MinimumEncodingLength;
    
    
    public AgentInfo getInfo() {
		return Info;
	}

	public void setInfo(AgentInfo info) {
		Info = info;
	}

	public short getClassId() {
		ClassId = (short)MESSAGE_CLASS_IDS.StatusReply.getValue();
		return ClassId;
	}

	public static int getMinimumEncodingLength() {
		MinimumEncodingLength = 4                // Object header
                				+ 1;             // Agent Info;
		return MinimumEncodingLength;
	}


	
    @Override
	public MESSAGE_CLASS_IDS MessageTypeId() {
		return Message.MESSAGE_CLASS_IDS.fromShort(ClassId);
	}
    
    @Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
