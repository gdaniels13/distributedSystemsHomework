package brillianstudent;

import AgentCommon.Agent;
import Common.AgentInfo;
import Common.Bomb;
import Common.ComponentInfo;
import Common.Excuse;
import Common.FieldLocation;
import Common.Tick;
import Common.WhiningTwine;
import Communication.Config;
import Communication.Endpoint;
import Communication.Envelope;
import ExecutionStrategies.ChangeStrengthExecutionStrategy;
import ExecutionStrategies.EndGameExecutionStrategy;
import ExecutionStrategies.ExecutionStrategy;
import ExecutionStrategies.GetResourceExecutionStrategy;
import ExecutionStrategies.GetStatusExecutionStrategy;
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
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA. User: Greg Daniels A00798340 Date: 2/28/14 Time:
 * 11:43 AM
 */
public class BrilliantStudent extends Agent {

    private ComponentInfo currentTarget;

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
            case GetStatus:
                return new GetStatusExecutionStrategy(this, cur);
            case ChangeStrength:
                return new ChangeStrengthExecutionStrategy(this, cur);
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
        requestResourceFromServer(GetResource.PossibleResourceType.BrillianStudentList);
        requestResourceFromServer(GetResource.PossibleResourceType.ExcuseGeneratorList);
        requestResourceFromServer(GetResource.PossibleResourceType.WhiningSpinnerList);
//        requestGameConfiguration();
        Random r = new Random();
        while (go) {
            while(auto){
                try {
                    sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BrilliantStudent.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(tickQueue.size()>=1)
                switch(r.nextInt(6)){
                    case 0:
                        move('u');
                        break;
                    case 1:
                        move('d');
                        break;
                    case 2:
                        move('l');
                        break;
                    case 3:
                        move('r');
                        break;
                    case 4:
                        requestResource(null, GetResource.PossibleResourceType.Excuse);
                        break;
                    case 5:
                        requestResource(null, GetResource.PossibleResourceType.WhiningTwine);
                        break;
                    default:
                }
                
                
                
                
                
            }
            
            
            
            
            try {
                sleep(500);
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
                    if(egMap.isEmpty())return;
                    ai = (AgentInfo) egMap.values().toArray()[new Random().nextInt(egMap.size())];
//                    ai = excuseGeneratorList.getAgentInfo(new Random().nextInt(excuseGeneratorList.Count()));
                    break;
                case WhiningTwine:
                    if(wgMap.isEmpty()) return;
//                    ai = whiningSpinnerList.getAgentInfo(new Random().nextInt(whiningSpinnerList.Count()));
                    ai = (AgentInfo) wgMap.values().toArray()[new Random().nextInt(wgMap.size())];
                    break;
                default:
                    return;
            }
        }
        GetResourceExecutionStrategy es = new GetResourceExecutionStrategy(this, type, new Endpoint(ai.getCommunicationEndPoint()));
        this.dispatcher.startConversation(es);
    }

    public void move(FieldLocation fl) {
        Tick t = tickQueue.poll();
        if (t == null) {
            GameStatus.updateLog("no ticks available to move");
            return;
        }

        Move move = new Move(pid, fl, t);
        Envelope env = new Envelope(move, this.config.getServerEndpoint());

        MoveExecutionStrategy ms = new MoveExecutionStrategy(this, env);
        this.dispatcher.startConversation(ms);
    }

    public void move(char dir) {
        short X = agentInfo.getLocation().getX();
        short Y = agentInfo.getLocation().getY();
        switch (dir) {
            case 'd':
                Y += agentInfo.getSpeed();
                break;
            case 'u':
                Y -= agentInfo.getSpeed();
                break;
            case 'l':
                X -= agentInfo.getSpeed();
                break;
            case 'r':
                X += agentInfo.getSpeed();
                break;
        }

        FieldLocation fl = new FieldLocation(X, Y, false);
        move(fl);
    }

    public Bomb makeBomb(){
        return new Bomb(pid, getExcuses(10), getTwine(10),tickQueue.poll());
    }
    
    public void throwBomb(Bomb b, FieldLocation dest) {
        Tick t = tickQueue.poll();
        if (t == null) {
            GameStatus.updateLog("unable to throw bomb. No Ticks Available");
            return;
        }
        ExecutionStrategy ex = new ThrowBombExecutionStrategy(this, b, dest, t);
        this.dispatcher.startConversation(ex);
    }

    public ArrayList<Excuse> getExcuses(int n) {
        n = Math.min(n, excuseQueue.size());
        ArrayList<Excuse> excuses = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            excuses.add(excuseQueue.poll());
        }
        return excuses;
    }

    public ArrayList<WhiningTwine> getTwine(int n) {
        n = Math.min(n, twineQueue.size());
        ArrayList<WhiningTwine> twine = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            twine.add(twineQueue.poll());
        }
        return twine;
    }

}
