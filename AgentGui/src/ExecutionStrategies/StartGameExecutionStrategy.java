/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExecutionStrategies;

import Messages.ReadyReply;
import Messages.Reply;
import AgentCommon.Agent;
import Communication.Envelope;

public class StartGameExecutionStrategy extends ExecutionStrategy {

    public StartGameExecutionStrategy(Agent agent, Envelope envelope) {
        super(agent, envelope);
    }
    
    @Override
    public void run() {
        ReadyReply rr = new ReadyReply(Reply.PossibleStatus.Success, "hello");
        
        rr.setConversationId(first.getMessage().getConversationId());
        Envelope toSend = new Envelope(rr, agent.getConfig().getServerAddress(),agent.getConfig().getServerPort());
        log("sending ready reply");
        Envelope temp = reliableSendReceive(toSend);
        if(temp == null){
            log("got nothing back from start game");
            System.exit(1);
        }
        
        agent.go();
    }
   
}
