package brillianstudent;

import AgentCommon.Agent;
import ExecutionStrategies.ExecutionStrategy;
import ExecutionStrategies.StartGameExecutionStrategy;
import ExecutionStrategies.TickReceiptStrategy;
import Communication.Config;
import Communication.Envelope;
import ExecutionStrategies.EndGameExecutionStrategy;
import Gui.GameStatus;
import Messages.Message;
import static Messages.Message.MESSAGE_CLASS_IDS;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/28/14
 * Time: 11:43 AM
 */
public class BrilliantStudent extends Agent
{
    public BrilliantStudent(Config config)
    {
            super(config);
            
    }

    @Override
    public ExecutionStrategy CreateExecutionStrategy(Envelope cur) {
        Message message = cur.getMessage();
        MESSAGE_CLASS_IDS messageType = message.MessageTypeId();


        switch (messageType){
            case StartGame:
                return new StartGameExecutionStrategy(this, cur);
            case TickDelivery:
                return  new TickReceiptStrategy(this,cur);
            case EndGame:
                return new EndGameExecutionStrategy(this, cur);
            default:
                return null;
        }
    }

    @Override
    public void run() {
        while(!go){
             //   GameStatus.updateLog("waiting for start of game: "+count);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(BrilliantStudent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        requestGameConfiguration();
        
        while(go){
            try {
                sleep(500);
//                dispatcher.startConversation(null);
                //GameStatus.updateLog("i'm a brainless brilliant student");
//            System.out.println("blah");
            } catch (InterruptedException ex) {
                Logger.getLogger(BrilliantStudent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        GameStatus.updateLog("Game Over");

    }

   
    
    
}
