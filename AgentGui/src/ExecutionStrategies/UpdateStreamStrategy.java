/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExecutionStrategies;

import AgentCommon.Agent;
import Common.AgentList;
import Communication.Envelope;
import Gui.GameStatus;
import Messages.AckNak;
import Messages.AgentListReply;
import Messages.Reply;


public class UpdateStreamStrategy extends ExecutionStrategy {

    private boolean started;
    public UpdateStreamStrategy(Agent agent, Envelope envelope) {
        super(agent, envelope);
        started = false;
        
    }

    @Override
    public void run() {
        if(!started){
            start();
        }
        else{
            agent.updateMaps(((AgentListReply)queue.poll().getMessage()).getAgents());
        }
    }
    
    public void stop(){
        started = false; 
        endStream();
        removeFromMap();
    }

    private void start() {
        Envelope env = reliableSendReceive(first);
        if(env == null || ((AckNak)env.getMessage()).getStatus() == Reply.PossibleStatus.Failure){
            GameStatus.updateLog("unable to start stream");
            removeFromMap();
        }
        else{
            GameStatus.updateLog("Successfully started stream");
        }
    }

    private void endStream() {
        //no end update stream message
    }
    
}
