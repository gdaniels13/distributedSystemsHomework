/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package excusegenerator;

import AgentCommon.Agent;
import Communication.Config;
import ExecutionStrategies.ExecutionStrategy;
import ExecutionStrategies.StartGameExecutionStrategy;
import ExecutionStrategies.TickReceiptStrategy;
import Gui.GameStatus;
import Messages.Message;
import Messages.Message.MESSAGE_CLASS_IDS;
import Messages.Request;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gregor
 */
public class ExcuseGenerator extends Agent{

    public ExcuseGenerator(Config config) {
        super(config);
        
    }


    @Override
    public ExecutionStrategy CreateExecutionStrategy(Communication.Envelope cur) {
        Message message = cur.getMessage();
        MESSAGE_CLASS_IDS messageType = message.MessageTypeId();

        switch(messageType){
            case StartGame:
                return new StartGameExecutionStrategy(this, cur);
            case GetResource:
                return new ExcuseRequestExecutionStrategy(this, cur);
            case TickDelivery:
                return new TickReceiptStrategy(this, cur);
            default:
                return null;
        }
        
    }

    @Override
    public void run() {
        //requestGameConfiguration();
        
        while(true){
            try {
                sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ExcuseGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            GameStatus.updateLog("I'm still alive");
        }
    
    }
    
}
