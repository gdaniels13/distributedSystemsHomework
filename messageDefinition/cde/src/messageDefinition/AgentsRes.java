package messageDefinition;

import Resources.Agent;
import Resources.Resource;
import messageDefinition.MessageType;

public class AgentsRes extends Response {
	public MessageType agentType;
	public Agent[] agents;


	public AgentsRes(MessageType type, int messageID, int conversationID, String senderId, MessageType requestType, MessageType agentType, Agent[] agents)
	{
		super(type, messageID, conversationID, senderId, requestType);
		this.agentType = agentType;
		this.agents = agents;
	}

	public AgentsRes(String messageString)
	{
		super(messageString);
		String args [] = messageString.split(Message.DELIMITER +Message.DELIMITER);
		args = args[2].split(Resource.DELIMITER);
		this.agentType = MessageType.get(args[0]);

		this.agents = new Agent[args.length-1];
		for(int i =1; i<args.length; ++i){
			this.agents[i-1] = new Agent(args[0] + Resource.DELIMITER +args[i]);
		}
	}

	@Override
	public String serialize(){
		StringBuilder toReturn = new StringBuilder();

		toReturn.append(mesString());
		toReturn.append(resString());

		toReturn.append(agentType.toString());
		toReturn.append(Resource.DELIMITER);

		for(Agent t : agents){
			toReturn.append(t.getId());
			toReturn.append(Resource.DELIMITER);
		}

		return toReturn.toString();
	}
}