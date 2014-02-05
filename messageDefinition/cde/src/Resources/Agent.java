package Resources;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil;
import messageDefinition.Message;
import messageDefinition.MessageType;

public class Agent extends Resource{
	private MessageType type;

    public Agent(int id, MessageType type) {
        super(id);
        this.type = type;
    }
    public Agent(String agent){
        super(agent.split(Message.DELIMITER)[0]);
        this.type = MessageType.get(agent.split(Message.DELIMITER)[1]);
    }

    public String toString(){
        return "" + super.getId() + Message.DELIMITER+ type.toString();
    }
}