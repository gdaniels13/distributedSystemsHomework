package AgentCommon;


import ExecutionStrategies.ExecutionStrategy;
import ExecutionStrategies.JoinGameExecutionStrategy;

import Common.AgentInfo;
import Common.AgentList;
import Common.Bomb;
import Common.ComponentInfo;
import Common.DistributableObject;
import Common.Excuse;
import Common.GameConfiguration;
import Common.PlayingFieldLayout;
import Common.Tick;
import Common.WhiningTwine;
import Communication.Communicator;
import Communication.Config;
import Communication.Envelope;
import Communication.EnvelopeQueue;
import Communication.Listener;
import java.util.Observable;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by gregor on 2/28/14.
 */
public abstract class Agent extends Observable implements Runnable {
    public static final boolean DEBUG = true;

    public Communicator communicator;
    protected Listener listener;
    protected Dispatcher dispatcher;
    protected EnvelopeQueue envelopeQueue;
    protected Config config;
    protected ConcurrentLinkedQueue<Tick> tickQueue;
    protected ConcurrentLinkedQueue<WhiningTwine> twineQueue;
    protected ConcurrentLinkedQueue<Excuse> excuseQueue;
    protected ConcurrentLinkedQueue<Bomb> bombQueue;
    protected ConcurrentLinkedQueue<DistributableObject> newResources;
    
    
    protected AgentInfo agentInfo;
    private String error;

//    public static Agent Create(Config config){
//        switch(config.getAgentType())
//        {
//            case "WG":
//                return new TwineGenerator(config);
//            case "BS":
//                return new BrilliantStudent(config);
//            case "EG":
//                return new ExcuseGenerator(config);
//            default:
//                throw new IllegalArgumentException(config.getAgentType() + "is not a valid agent Type");
//        }
//    }
    
    private boolean go;
    private boolean stop;
    private GameConfiguration gameConfiguration;
    
     public Agent(Config config) {
        this.config = config;
        this.go = false;
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
//        new Thread(listener).start();
        this.tickQueue = new ConcurrentLinkedQueue<>();
        this.bombQueue = new ConcurrentLinkedQueue<>();
        this.excuseQueue = new ConcurrentLinkedQueue<>();
        this.twineQueue = new ConcurrentLinkedQueue<>();
        this.newResources = new ConcurrentLinkedQueue<>();
        joinGame();
        new Thread(dispatcher).start();
    }

    public void go() {
        this.go = true;
        notifyGui();
    }
    
    public void stop(){
        this.stop = false;
        notifyGui();
    }
    
    public AgentInfo getAgentInfo() {
        return agentInfo;
    }

    public void setAgentInfo(AgentInfo agentInfo) {
        this.agentInfo = agentInfo;
        notifyGui();
    }
    
    
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

    public ConcurrentLinkedQueue<WhiningTwine> getTwineQueue() {
        return twineQueue;
    }

    public ConcurrentLinkedQueue<Excuse> getExcuseQueue() {
        return excuseQueue;
    }

    public ConcurrentLinkedQueue<Bomb> getBombQueue() {
        return bombQueue;
    }

    public String getError() {
        return error;
    }

    public void setError(String message) {
        this.error = message;
        notifyGui();
    }

    private void notifyGui() {
        setChanged();
        notifyObservers();
    }

    public void setSetConfiguration(GameConfiguration configuration) {
        this.gameConfiguration = configuration;
    }

    public GameConfiguration getGameConfiguration() {
        return gameConfiguration;
    }

    public void addResource(DistributableObject configuration) {
        this.newResources.add(configuration);
    }

    public void setPlayingField(PlayingFieldLayout layout) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setBrilliantStudentList(AgentList list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setExcuseGeneratorList(AgentList list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setWhiningSpinnerList(AgentList list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setZombieProfessorList(AgentList list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
