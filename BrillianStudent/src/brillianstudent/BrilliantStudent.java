package brillianstudent;

import AgentCommon.Agent;
import ExecutionStrategies.ExecutionStrategy;
import ExecutionStrategies.StartGameExecutionStrategy;
import ExecutionStrategies.TickReceiptStrategy;
import Common.ComponentInfo;
import Communication.Config;
import Communication.Envelope;
import Messages.JoinGame;
import Messages.Message;
import Messages.Message.MESSAGE_CLASS_IDS;
import static Messages.Message.MESSAGE_CLASS_IDS;
import static java.lang.Thread.sleep;
import java.net.Inet4Address;
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
            default:
                return null;

        }
    }

    @Override
    public void run() {
        while(true){
            
//            System.out.println(health);
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(BrilliantStudent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
