/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExecutionStrategies;

import Messages.AckNak;
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
        AckNak ack = (AckNak)readyReply().getMessage();
        if(ack == null){
            agent.setError("unable to receive ack from server to start the tame");
            System.exit(0);
        }
        agent.go();
    }

    private Envelope readyReply() {
        ReadyReply rep = new ReadyReply(Reply.PossibleStatus.Success, "");
        Envelope reply = new Envelope(rep, recipient);
        Envelope result = reliableSendReceive(reply);
        return result;
    }
    
}
