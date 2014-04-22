/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinegenerator;

import AgentCommon.Agent;
import Common.WhiningTwine;
import Communication.Envelope;
import Gui.GameStatus;
import Messages.GetResource;
import Messages.Reply;
import Messages.ResourceReply;

/**
 *
 * @author gregor
 */
public class TwineRequestExecutionStrategy extends ExecutionStrategies.ExecutionStrategy {

    public TwineRequestExecutionStrategy(Agent agent, Envelope envelope) {
        super(agent, envelope);
        GameStatus.updateLog("Got request from " + recipient);
    }

    @Override
    public void run() {
        
//        while(twine ==null){
//            try {
//                wait(50);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(TwineRequestExecutionStrategy.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        WhiningTwine twine = ((TwineGenerator)agent).getTwineQueue().poll();
        Reply.PossibleStatus status ;
        String message;
        if(twine == null){
            status = Reply.PossibleStatus.Failure;
            message = "no twine for you";
        }
        else
        {
            message = "here have a piece of twine";
            status = Reply.PossibleStatus.Success;
            twine.setRequestTick(((GetResource)first.getMessage()).getEnablingTick());
        }
        
        ResourceReply rr = new  ResourceReply(status, twine, message);
        rr.setConversationId(conversationId);
        Envelope e = new Envelope(rr, recipient);
        
        agent.getCommunicator().send(e);
        removeFromMap();
    }


//
//    public synchronized void setTwine(WhiningTwine twine) {
//        this.twine = twine;
//        GetResource gr = (GetResource)first.getMessage();
//        twine.setRequestTick(gr.getEnablingTick());
//        notifyAll();
//    }
}
