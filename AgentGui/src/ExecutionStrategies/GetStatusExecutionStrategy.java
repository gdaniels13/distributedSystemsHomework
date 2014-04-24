/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExecutionStrategies;

import AgentCommon.Agent;
import Communication.Envelope;
import Messages.Reply;
import Messages.StatusReply;


public class GetStatusExecutionStrategy extends ExecutionStrategy {

    public GetStatusExecutionStrategy(Agent agent, Envelope envelope) {
        super(agent, envelope);
    }

    @Override
    public void run() {
        StatusReply sr = new StatusReply(Reply.PossibleStatus.Success, agent.getAgentInfo(), " ");
                
        Envelope resp = new Envelope(sr, recipient);
        agent.getCommunicator().send(resp);
        removeFromMap();
    }
    
}
