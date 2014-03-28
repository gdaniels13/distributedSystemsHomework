package Agents.AgentCommon;

import Agents.BrilliantStudent.BrilliantStudent;
import Agents.ExcuseGenerator.ExcuseGenerator;
import Agents.TwineGenerator.TwineGenerator;
import Common.ComponentInfo;
import Common.Tick;
import Communication.Communicator;
import Communication.Envelope;
import Communication.EnvelopeQueue;
import Communication.Listener;
import java.util.Observable;
import java.util.concurrent.ConcurrentLinkedQueue;
import registrarClient.GameInfo;

/**
 * Created by gregor on 2/28/14.
 */
public abstract class Agent extends Observable implements Runnable {


    protected Communicator communicator;
    protected Listener listener;
    protected Dispatcher dispatcher;
    protected EnvelopeQueue envelopeQueue;
    protected Config config;
    protected ConcurrentLinkedQueue<Tick> tickQueue;
    protected ComponentInfo componentInfo;
    
    protected int health;
    
    public Communicator getCommunicator() {
        return communicator;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public Listener getListener() {
        return listener;
    }

    public EnvelopeQueue getEnvelopeQueue() {
        return envelopeQueue;
    }

    public Config getConfig() {
        return config;
    }

    public ConcurrentLinkedQueue<Tick> getTickQueue() {
        return tickQueue;
    }

    public static Agent Create(Config config){
        switch(config.getAgentType())
        {
            case "WG":
                return new TwineGenerator(config);
            case "BS":
                return new BrilliantStudent(config);
            case "EG":
                return new ExcuseGenerator(config);
            default:
                throw new IllegalArgumentException(config.getAgentType() + "is not a valid agent Type");
        }
    }

    public Agent(Config config) {
        this.config = config;

        
    }

    public ComponentInfo getComponentInfo() {
        return componentInfo;
    }

    public int gethealth() {
        return health;
    }

    public void sethealth(int health) {
        this.health = health;
        setChanged();
        notifyObservers();
    }

    public void joinGame(){
        JoinGameExecutionStrategy  jge = new JoinGameExecutionStrategy(this);          
    }

    public abstract ExecutionStrategy CreateExecutionStrategy(Envelope cur);

    public void init() {
        this.communicator = new Communicator(config);
        this.envelopeQueue = new EnvelopeQueue();
        this.dispatcher = new Dispatcher(this.envelopeQueue,this);
        this.listener = new Listener(this.communicator,this.envelopeQueue);
        this.tickQueue = new ConcurrentLinkedQueue<>();
        new Thread(dispatcher).start();
        joinGame();
    }
}
