package Agents.BrilliantStudent;

import Agents.AgentCommon.*;
import Communication.Envelope;
import Messages.JoinGame;
import Messages.Message;

import static Messages.Message.MESSAGE_CLASS_IDS;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/28/14
 * Time: 11:43 AM
 */
public class BrilliantStudent extends Agent
{
	public BrilliantStudent(Config config)
	{
		super(config);
	}

    @Override
    public ExecutionStrategy CreateExecutionStrategy(Envelope cur) {
        Message message = cur.getMessage();
        MESSAGE_CLASS_IDS messageType = message.MessageTypeId();


        switch (messageType){
            case JoinGame:
                return new JoinGameExecutionStrategy(this,cur);
            case TickDelivery:
                return  new TickReceiptStrategy(this,cur);
            default:
                return null;
                //do nothing drop the message on the floor
        }
    }

    @Override
    public void run() {

    }
}
