package brillianstudent;

import AgentCommon.Agent;
import Common.AgentInfo;
import Common.Bomb;
import Common.FieldLocation;
import Common.Tick;
import Communication.Config;
import Communication.Endpoint;
import Communication.Envelope;
import ExecutionStrategies.EndGameExecutionStrategy;
import ExecutionStrategies.ExecutionStrategy;
import ExecutionStrategies.GetResourceExecutionStrategy;
import ExecutionStrategies.StartGameExecutionStrategy;
import ExecutionStrategies.ThrowBombExecutionStrategy;
import ExecutionStrategies.TickReceiptStrategy;
import Gui.GameStatus;
import Messages.GetResource;
import Messages.Message;
import Messages.Message.MESSAGE_CLASS_IDS;
import static Messages.Message.MESSAGE_CLASS_IDS;
import Messages.Move;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA. User: Greg Daniels A00798340 Date: 2/28/14 Time:
 * 11:43 AM
 */
public class BrilliantStudent extends Agent {

    public BrilliantStudent(Config config) {
        super(config);

    }

    @Override
    public ExecutionStrategy CreateExecutionStrategy(Envelope cur) {
        Message message = cur.getMessage();
        MESSAGE_CLASS_IDS messageType = message.MessageTypeId();

        switch (messageType) {
            case StartGame:
                return new StartGameExecutionStrategy(this, cur);
            case TickDelivery:
                return new TickReceiptStrategy(this, cur);
            case EndGame:
                return new EndGameExecutionStrategy(this, cur);
            default:
                return null;
        }
    }

    @Override
    public void run() {
        requestResourceFromServer(GetResource.PossibleResourceType.GameConfiguration);
        while (!go) {
            //   GameStatus.updateLog("waiting for start of game: "+count);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(BrilliantStudent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        requestGameConfiguration();

        while (go) {
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

    public void requestResource(AgentInfo ai, GetResource.PossibleResourceType type) {
        if (ai == null) {
            switch (type) {
                case Excuse:
                    ai = excuseGeneratorList.getAgentInfo(new Random().nextInt(excuseGeneratorList.Count()));
                    break;
                case WhiningTwine:
                    ai = whiningSpinnerList.getAgentInfo(new Random().nextInt(whiningSpinnerList.Count()));
                    break;
                default:
                    return;
            }
        }
        GetResourceExecutionStrategy es = new GetResourceExecutionStrategy(this, type, new Endpoint(ai.getCommunicationEndPoint()));
        this.dispatcher.startConversation(es);
    }

    public void move(FieldLocation fl){
        Tick t = tickQueue.poll();
        if(t == null){
            GameStatus.updateLog("no ticks available to move");
            return;
        }
        
        Move move = new Move(pid, fl, t);
        Envelope env = new Envelope(move, this.config.getServerEndpoint());
               
        MoveExecutionStrategy ms = new MoveExecutionStrategy(this, env);
        this.dispatcher.startConversation(ms);
    }
    
    public void throwBomb(Bomb b,FieldLocation dest){
        Tick t = tickQueue.poll();
        if(t == null){
            GameStatus.updateLog("unable to throw bomb. No Ticks Available");
            return;
        }
        ExecutionStrategy ex = new ThrowBombExecutionStrategy(this, b, dest,t);
        this.dispatcher.startConversation(ex);
    }
    
}
