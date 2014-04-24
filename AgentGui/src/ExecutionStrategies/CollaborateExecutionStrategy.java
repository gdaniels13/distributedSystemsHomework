/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExecutionStrategies;

import AgentCommon.Agent;
import Communication.Envelope;
import Gui.GameStatus;
import Messages.AckNak;
import Messages.Reply;


public class CollaborateExecutionStrategy extends ExecutionStrategy {

    public CollaborateExecutionStrategy(Agent agent, Envelope envelope) {
        super(agent, envelope);
        
    }

    @Override
    public void run() {
        Envelope res = reliableSendReceive(first);
       
        if(res == null){
            GameStatus.updateLog("No response received");
        }
        
        AckNak ack = (AckNak) res.getMessage();
        if(ack.getStatus() == Reply.PossibleStatus.Failure){
            GameStatus.updateLog("failed to get collab info " + ack.getMessage() +" " + ack.getNote());
        }
        else{
            GameStatus.updateLog("got the colaboration");
        }
        
        removeFromMap();
    }
    
}
