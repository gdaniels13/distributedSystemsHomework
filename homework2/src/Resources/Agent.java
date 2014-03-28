package Resources;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil;
import messageDefinition.MessageType;

public class Agent extends Resource{
	private MessageType type;

    public Agent(int id, MessageType type) {
        super(id);
        this.type = type;
    }
    public Agent(String agent){
        super(agent.split(DELIMITER)[1]);
        this.type = MessageType.get(agent.split(DELIMITER)[0]);
    }

    public String toString(){
        return "" + super.getId() + DELIMITER+ type.toString() + DELIMITER;
    }

}