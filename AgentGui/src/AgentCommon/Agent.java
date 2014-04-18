package AgentCommon;

import Common.AgentInfo;
import Common.AgentList;
import Common.Bomb;
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
import ExecutionStrategies.ExecutionStrategy;
import ExecutionStrategies.GetResourceExecutionStrategy;
import ExecutionStrategies.JoinGameExecutionStrategy;
import Gui.GameStatus;
import Messages.GetResource;
import java.net.InetAddress;
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
    private String error;
    protected short pid;
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

    public short getPid() {
        return pid;
    }

    public void setPid(short pid) {
        this.pid = pid;
    }

    protected boolean go;
    protected GameConfiguration gameConfiguration;
    protected AgentInfo agentInfo;

    public Agent(Config config) {
        this.config = config;
        this.go = false;
    }

    public void joinGame() {
        JoinGameExecutionStrategy jge = new JoinGameExecutionStrategy(this);
    }

    public abstract ExecutionStrategy CreateExecutionStrategy(Envelope cur);

    public void init() {
        this.communicator = new Communicator(config);
        this.envelopeQueue = new EnvelopeQueue();
        this.dispatcher = new Dispatcher(this.envelopeQueue, this);
        this.listener = new Listener(this.communicator, this.envelopeQueue);
//        new Thread(listener).start();
        this.tickQueue = new ConcurrentLinkedQueue<>();
        this.bombQueue = new ConcurrentLinkedQueue<>();
        this.excuseQueue = new ConcurrentLinkedQueue<>();
        this.twineQueue = new ConcurrentLinkedQueue<>();
        this.newResources = new ConcurrentLinkedQueue<>();
        joinGame();
        new Thread(listener).start();
        new Thread(dispatcher).start();
    }

    public void go() {
        this.go = true;
        GameStatus.updateLog("Now I can Go");
        notifyGui();
    }

    public void stop() {
        this.go = false;
        GameStatus.updateLog("STOPPING");
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

    public void notifyGui() {
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

    public boolean verifyServer(Envelope env) {
        InetAddress mAddress = env.getAddress().getAddress();
        int mPort = env.getAddress().getPort();
        int sPort = config.getServerPort();
        InetAddress sAddress = config.getServerAddress();

        if (mAddress.toString().compareTo(sAddress.toString()) == 0 && sPort == mPort) {
            return true;
        }
        return false;
    }

    public void requestGameConfiguration() {
        GetResourceExecutionStrategy es = new GetResourceExecutionStrategy(this, GetResource.PossibleResourceType.GameConfiguration, config.getServerEndpoint());
        this.dispatcher.startConversation(es);
    }

}
