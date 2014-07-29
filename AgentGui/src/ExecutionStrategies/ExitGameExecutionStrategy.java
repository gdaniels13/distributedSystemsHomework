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
import Messages.ExitGame;
import Messages.Reply;


public class ExitGameExecutionStrategy extends ExecutionStrategy {
    public ExitGameExecutionStrategy(Agent agent, Envelope envelope) {
        super(agent, envelope);
    }

    @Override
    public void run() {
        ExitGame eg = new ExitGame((short)10);
        Envelope env = new Envelope(eg, agent.getConfig().getServerEndpoint());
        
        Envelope e = reliableSendReceive(env);
        if(e == null){
            GameStatus.updateLog("No response received for exiting the game try again");
        }
        AckNak ack = (AckNak) e.getMessage();
        if(ack.getStatus() == Reply.PossibleStatus.Failure){
            GameStatus.updateLog("Failed to exit game " + ack.getMessage() +" " + ack.getNote());
        }
        else{
            GameStatus.updateLog("Successfully exited game now stopping agent");
            agent.stop();
        }
        removeFromMap();
    }
    
}
