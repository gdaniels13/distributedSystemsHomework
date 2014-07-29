/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExecutionStrategies;

import AgentCommon.Agent;
import Communication.Envelope;
import Messages.AckNak;
import Messages.ChangeStrength;
import Messages.Reply;


public class ChangeStrengthExecutionStrategy extends ExecutionStrategy {

    public ChangeStrengthExecutionStrategy(Agent agent, Envelope envelope) {
        super(agent, envelope);
    }

    @Override
    public void run() {
        if(!agent.verifyServer(first)){
            return;
        }
        ChangeStrength cs = (ChangeStrength) first.getMessage();
        agent.getAgentInfo().setStrength(agent.getAgentInfo().getStrength()+cs.DeltaValue);
        AckNak a = new AckNak(Reply.PossibleStatus.Success);
        Envelope ack= new Envelope(a, recipient);
        agent.getCommunicator().send(ack);
    }
    
}
