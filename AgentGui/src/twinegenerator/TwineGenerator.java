/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package twinegenerator;

import Communication.Config;
import Communication.Envelope;
import ExecutionStrategies.ExecutionStrategy;
import ExecutionStrategies.StartGameExecutionStrategy;
import ExecutionStrategies.TickReceiptStrategy;
import Gui.GameStatus;
import Messages.Request;
import excusegenerator.ExcuseGenerator;
import static java.lang.Thread.sleep;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gregor
 */
public class TwineGenerator extends AgentCommon.Agent{

    private ConcurrentLinkedQueue<ExecutionStrategy> requestQueue;
    public TwineGenerator(Config config) {
        super(config);
        requestQueue = new ConcurrentLinkedQueue<>();
    }

    public ConcurrentLinkedQueue<ExecutionStrategy> getRequests() {
        return requestQueue;
    }

    @Override
    public ExecutionStrategy CreateExecutionStrategy(Envelope cur) {
                Request r = (Request) cur.getMessage();
        if(r == null) return null;
        switch(r.RequestType){
            case StartGame:
                return new StartGameExecutionStrategy(this, cur);
            case GetResource:
                return new TwineRequestExecutionStrategy(this, cur);
            case TickDelivery:
                return new TickReceiptStrategy(this, cur);
            default:
                return null;
        }
    }

    
    
    @Override
    public void run() {
        while(requestQueue.size()<gameConfiguration.getNumberOfTicksRequiredToBuildAnExcuse()){
            try {
                wait(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ExcuseGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            GameStatus.updateLog("I'm still alive");
        }
    }


    
}
