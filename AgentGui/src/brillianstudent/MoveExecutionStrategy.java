/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brillianstudent;

import AgentCommon.Agent;
import Communication.Envelope;
import ExecutionStrategies.ExecutionStrategy;
import Gui.GameStatus;
import Messages.AckNak;
import Messages.Move;
import Messages.Reply;


public class MoveExecutionStrategy extends ExecutionStrategy {
    

    public MoveExecutionStrategy(Agent agent, Envelope envelope) {
        super(agent, envelope);
        
    }

    @Override
    public void run() {
        
       Envelope response =  reliableSendReceive(first);
       if(response == null){
           GameStatus.updateLog("Nothing received for move request");
           return;
       }
       AckNak ack = (AckNak) response.getMessage();
       if(ack.getStatus() == Reply.PossibleStatus.Success){
           agent.getAgentInfo().setLocation(((Move)first.getMessage()).getToSquare());
           GameStatus.updateLog("moved");
       }
       else{
           GameStatus.updateLog("Unable to move "+ ack.getMessage());
       }
       removeFromMap();
    }
    
}
