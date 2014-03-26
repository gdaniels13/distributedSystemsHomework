package Agents.AgentCommon;

import Communication.Envelope;
import Messages.AckNak;
import Messages.Reply;

/**
 * Created by gregor on 3/3/14.
 */
public class JoinGameExecutionStrategy extends ExecutionStrategy {
    
    private boolean sentRequest;
    public JoinGameExecutionStrategy(Agent agent, Envelope envelope) {
        super(agent,envelope);
        sentRequest = false;
    }
    
   
    
    @Override
    public void run() {
        if(!sentRequest){
            sendRequest();
        }
        else
        {
            receiveAck();
        }
    }

    private void sendRequest() {
        System.out.println("sending request");
        if(this.first == null){
            this.removeFromMap();
        }
        
        this.agent.communicator.send(first);
        sentRequest = true;
    }

    private void receiveAck() {
        System.out.println("received a response ");
        Envelope env = queue.poll();
        AckNak ack = (AckNak)env.getMessage();
        if(env == null || ack == null){
            //something weird happened
            return;
        }
        
        if(ack.Status!=Reply.PossibleStatus.Success){
            System.out.println("ERROR Failed to join game " + ack.getMessage() + ack.getNote());
            return;
        }
        
        
        
        
        
    }
}
