/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinegenerator;

import Common.Tick;
import Common.WhiningTwine;
import Communication.Config;
import Communication.Envelope;
import ExecutionStrategies.EndGameExecutionStrategy;
import ExecutionStrategies.ExecutionStrategy;
import ExecutionStrategies.GetStatusExecutionStrategy;
import ExecutionStrategies.StartGameExecutionStrategy;
import ExecutionStrategies.TickReceiptStrategy;
import Gui.GameStatus;
import Messages.GetResource;
import Messages.Request;
import excusegenerator.ExcuseGenerator;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gregor
 */
public class TwineGenerator extends AgentCommon.Agent {

//    private ConcurrentLinkedQueue<TwineRequestExecutionStrategy> requestQueue;
    private ConcurrentLinkedQueue<WhiningTwine> twineQueue;
    
    public TwineGenerator(Config config) {
        super(config);
//        requestQueue = new ConcurrentLinkedQueue<>();
        twineQueue = new ConcurrentLinkedQueue<>();
    }

    public ConcurrentLinkedQueue<WhiningTwine> getTwineQueue(){
        return twineQueue;
    }
    
//    public ConcurrentLinkedQueue<TwineRequestExecutionStrategy> getRequests() {
//        return requestQueue;
//    }

    @Override
    public ExecutionStrategy CreateExecutionStrategy(Envelope cur) {
        Request r = (Request) cur.getMessage();
        if (r == null) {
            return null;
        }
        switch (r.RequestType) {
            case StartGame:
                return new StartGameExecutionStrategy(this, cur);
            case EndGame:
                return new EndGameExecutionStrategy(this, cur);
            case GetResource:
                return new TwineRequestExecutionStrategy(this, cur);
            case TickDelivery:
                return new TickReceiptStrategy(this, cur);
            case GetStatus:
                return new GetStatusExecutionStrategy(this, cur);
            default:
                return null;
        }
    }
     
/*
    public void run2() {
        requestResourceFromServer(GetResource.PossibleResourceType.GameConfiguration);
        while (true) {
            GameStatus.updateLog("waiting for start game");
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(TwineGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            synchronized (this) {
                while (go) {
                    try {
                        sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ExcuseGenerator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    boolean didWork = true;
                    while (didWork) {
                        didWork = false;
                        if (!requestQueue.isEmpty() && tickQueue.size() > gameConfiguration.NumberOfTicksRequiredToBuildTwine) {
                            TwineRequestExecutionStrategy tr = requestQueue.poll();
                            ArrayList<Tick> tl = new ArrayList();
                            for (int i = 0; i < gameConfiguration.getNumberOfTicksRequiredToBuildAnExcuse(); ++i) {
                                tl.add(tickQueue.poll());
                            }
                            WhiningTwine wt = new WhiningTwine(pid, tl, null);

                            tr.setTwine(wt);
                            didWork = true;
                            GameStatus.updateLog("Sent a Twine to " + tr.getFirst().getAddress());;
                        }
                    }
                }
            }
        }
    }
    */
    
    @Override
    public void run() {
        requestResourceFromServer(GetResource.PossibleResourceType.GameConfiguration);
        while (true) {
            GameStatus.updateLog("waiting for start game");
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(TwineGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            synchronized (this) {
                while (go) {
                    try {
                        sleep(gameConfiguration.getTickInterval());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ExcuseGenerator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    boolean didWork = true;
                    while (didWork) {
                        didWork = false;
                        if (tickQueue.size() > gameConfiguration.getNumberOfTicksRequiredToBuildTwine()) {
                            ArrayList<Tick> tl = new ArrayList();
                            for (int i = 0; i < gameConfiguration.getNumberOfTicksRequiredToBuildAnExcuse(); ++i) {
                                tl.add(tickQueue.poll());
                            }
                            WhiningTwine wt = new WhiningTwine(pid, tl, null);
                            twineQueue.add(wt);
                            didWork = true;
                        }
                    }
                }
            }
        }
    }
}
