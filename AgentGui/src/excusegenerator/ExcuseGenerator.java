/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excusegenerator;

import AgentCommon.Agent;
import static Common.DistributableObject.DISTRIBUTABLE_CLASS_IDS.Excuse;
import Common.Excuse;
import Common.Tick;
import Common.WhiningTwine;
import Communication.Config;
import ExecutionStrategies.EndGameExecutionStrategy;
import ExecutionStrategies.ExecutionStrategy;
import ExecutionStrategies.GetStatusExecutionStrategy;
import ExecutionStrategies.StartGameExecutionStrategy;
import ExecutionStrategies.TickReceiptStrategy;
import Gui.GameStatus;
import Messages.GetResource;
import Messages.Message;
import Messages.Message.MESSAGE_CLASS_IDS;
import Messages.Request;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import twinegenerator.TwineGenerator;
import twinegenerator.TwineRequestExecutionStrategy;

/**
 *
 * @author gregor
 */
public class ExcuseGenerator extends Agent {

    private ConcurrentLinkedQueue<ExcuseRequestExecutionStrategy> requestQueue;

    public ExcuseGenerator(Config config) {
        super(config);
        requestQueue = new ConcurrentLinkedQueue<>();
    }

    public ConcurrentLinkedQueue<ExcuseRequestExecutionStrategy> getRequests() {
        return requestQueue;
    }

    @Override
    public ExecutionStrategy CreateExecutionStrategy(Communication.Envelope cur) {
        Message message = cur.getMessage();
        MESSAGE_CLASS_IDS messageType = message.MessageTypeId();

        switch (messageType) {
            case StartGame:
                return new StartGameExecutionStrategy(this, cur);
            case GetResource:
                return new ExcuseRequestExecutionStrategy(this, cur);
            case TickDelivery:
                return new TickReceiptStrategy(this, cur);
            case EndGame:
                return new EndGameExecutionStrategy(this, cur);
            case GetStatus:
                return new GetStatusExecutionStrategy(this, cur);
            default:
                return null;
        }

    }

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
                        sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ExcuseGenerator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    boolean didWork = true;
                    while (didWork) {
                        didWork = false;
                        if (!requestQueue.isEmpty() && tickQueue.size() > gameConfiguration.getNumberOfTicksRequiredToBuildAnExcuse()) {
                            ExcuseRequestExecutionStrategy tr = requestQueue.poll();
                            ArrayList<Tick> tl = new ArrayList();
                            for (int i = 0; i < gameConfiguration.getNumberOfTicksRequiredToBuildAnExcuse(); ++i) {
                                tl.add(tickQueue.poll());
                            }
                            Excuse ex = new Excuse(pid, tl, null);

                            tr.setExcuse(ex);
                            didWork = true;
                            GameStatus.updateLog("Sent an Excuse to " + tr.getFirst().getAddress());;
                        }
                    }

                }
            }
        }
    }
}
