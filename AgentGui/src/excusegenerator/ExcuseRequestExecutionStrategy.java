/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package excusegenerator;

import AgentCommon.Agent;
import Common.Excuse;
import Common.WhiningTwine;
import Communication.Envelope;
import ExecutionStrategies.ExecutionStrategy;
import Gui.GameStatus;
import Messages.GetResource;
import Messages.Reply;
import Messages.ResourceReply;
import java.util.logging.Level;
import java.util.logging.Logger;
import twinegenerator.TwineGenerator;
import twinegenerator.TwineRequestExecutionStrategy;

/**
 *
 * @author gregor
 */
public class ExcuseRequestExecutionStrategy extends ExecutionStrategy{

private Excuse excuse;
    public ExcuseRequestExecutionStrategy(Agent agent, Envelope envelope) {
        super(agent, envelope);
        excuse = null;
        ((ExcuseGenerator)agent).getRequests().add(this);
//        ((TwineGenerator)agent).notify();
        GameStatus.updateLog("Got request from " + recipient);
    }

    @Override
    public synchronized void run() {
        while(excuse ==null){
            try {
                wait(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(TwineRequestExecutionStrategy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ResourceReply rr = new  ResourceReply(Reply.PossibleStatus.Success, excuse, "hello");
        rr.setConversationId(conversationId);
        Envelope e = new Envelope(rr, recipient);
        
        agent.getCommunicator().send(e);
        
    }


    public Excuse getExcuse() {
        return excuse;
    }

    public synchronized void setExcuse(Excuse excuse) {
        this.excuse = excuse;
        GetResource gr = (GetResource)first.getMessage();
        excuse.setRequestTick(gr.getEnablingTick());
        notifyAll();
    }
}
