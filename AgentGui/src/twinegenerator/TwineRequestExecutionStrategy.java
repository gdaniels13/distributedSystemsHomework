/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinegenerator;

import AgentCommon.Agent;
import Common.WhiningTwine;
import Communication.Envelope;
import Messages.Reply;
import Messages.ResourceReply;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gregor
 */
public class TwineRequestExecutionStrategy extends ExecutionStrategies.ExecutionStrategy {

    private int tickToTwineRatio;
    private WhiningTwine twine;
    public TwineRequestExecutionStrategy(Agent agent, Envelope envelope) {
        super(agent, envelope);
        ((TwineGenerator)agent).getRequests().add(this);
        twine = null;
        ((TwineGenerator)agent).notify();
    }

    @Override
    public synchronized void run() {
        while(twine ==null){
            try {
                wait(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(TwineRequestExecutionStrategy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ResourceReply rr = new  ResourceReply(Reply.PossibleStatus.Success, twine, "hello");
        rr.setConversationId(conversationId);
        Envelope e = new Envelope(rr, recipient);
        
        agent.getCommunicator().send(e);
        
    }


    public WhiningTwine getTwine() {
        return twine;
    }

    public synchronized void setTwine(WhiningTwine twine) {
        this.twine = twine;
        notifyAll();
    }
}
