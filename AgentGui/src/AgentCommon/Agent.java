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
import ExecutionStrategies.ExitGameExecutionStrategy;
import ExecutionStrategies.GetResourceExecutionStrategy;
import ExecutionStrategies.JoinGameExecutionStrategy;
import ExecutionStrategies.UpdateStreamStrategy;
import Gui.GameStatus;
import Messages.GetResource;
import Messages.StartUpdateStream;
import java.net.InetAddress;
import java.util.HashMap;
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
    protected boolean go;
    protected GameConfiguration gameConfiguration;
    protected AgentInfo agentInfo;
    protected PlayingFieldLayout fieldLayout;
    protected AgentList brilliantStudentList;
    protected AgentList excuseGeneratorList;
    protected AgentList whiningSpinnerList;
    protected AgentList zombieList;
    protected HashMap<Integer, AgentInfo> bsMap;
    protected HashMap<Integer, AgentInfo> zMap;
    protected HashMap<Integer, AgentInfo> egMap;
    protected HashMap<Integer, AgentInfo> wgMap;
    protected boolean auto;
    public HashMap<Integer, AgentInfo> getBsMap() {
        return bsMap;
    }

    public HashMap<Integer, AgentInfo> getzMap() {
        return zMap;
    }

    public HashMap<Integer, AgentInfo> getEgMap() {
        return egMap;
    }

    public HashMap<Integer, AgentInfo> getWgMap() {
        return wgMap;
    }

    public void updateMaps(AgentList al) {
        for (int i = 0; i < al.Count(); i++) {
            AgentInfo a = al.getAgentInfo(i);
            switch (a.getAgentType()) {
                case BrilliantStudent:
                    insert(bsMap, a);
                    break;
                case ExcuseGenerator:
                    insert(egMap, a);
                    break;
                case WhiningSpinner:
                    insert(wgMap, a);
                    break;
                case ZombieProfessor:
                    insert(zMap, a);
                    break;
            }
        }
    }

    
    private void insert(HashMap<Integer, AgentInfo> map, AgentInfo ai) {
       int key = ai.getId();
       map.put(key, ai);
    }

    public PlayingFieldLayout getFieldLayout() {
        return fieldLayout;
    }

    public AgentList getBrilliantStudentList() {
        return brilliantStudentList;
    }

    public AgentList getExcuseGeneratorList() {
        return excuseGeneratorList;
    }

    public AgentList getWhiningSpinnerList() {
        return whiningSpinnerList;
    }

    public AgentList getZombieList() {
        return zombieList;
    }

    public short getPid() {
        return pid;
    }

    public void setPid(short pid) {
        this.pid = pid;
    }

    public Agent(Config config) {
        this.config = config;
        this.go = false;
        this.bsMap = new HashMap<>();
        this.egMap = new HashMap<>();
        this.wgMap = new HashMap<>();
        this.zMap = new HashMap<>();
    }

    public void joinGame() {
        JoinGameExecutionStrategy jge = new JoinGameExecutionStrategy(this);
    }

    public abstract ExecutionStrategy CreateExecutionStrategy(Envelope cur);

    public void init() {
        this.auto = true;
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
        notifyGui();
    }

    public GameConfiguration getGameConfiguration() {
        return gameConfiguration;
    }

    public void addResource(DistributableObject configuration) {
        this.newResources.add(configuration);
    }

    public void setPlayingField(PlayingFieldLayout layout) {
        this.fieldLayout = layout;
        notifyGui();

    }

    public void setBrilliantStudentList(AgentList list) {
        this.brilliantStudentList = list;
        notifyGui();

    }

    public void setExcuseGeneratorList(AgentList list) {
        this.excuseGeneratorList = list;
        notifyGui();

    }

    public void setWhiningSpinnerList(AgentList list) {
        this.whiningSpinnerList = list;
        notifyGui();

    }

    public void setZombieProfessorList(AgentList list) {
        this.zombieList = list;
        notifyGui();

    }

    public void exitGame() {
        ExecutionStrategy es = new ExitGameExecutionStrategy(this, null);
        this.dispatcher.startConversation(es);
    }

    public boolean verifyServer(Envelope env) {
        InetAddress mAddress = env.getAddress().getAddress();
        int mPort = env.getAddress().getPort();
        int sPort = config.getServerPort();
        InetAddress sAddress = config.getServerAddress();

        if (mAddress.toString().compareTo(sAddress.toString()) == 0 && sPort == mPort) {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void requestResourceFromServer(GetResource.PossibleResourceType type) {
        GetResourceExecutionStrategy es = new GetResourceExecutionStrategy(this, type, config.getServerEndpoint());
        this.dispatcher.startConversation(es);
    }

     public void startUpdateStream(){
         StartUpdateStream ss = new StartUpdateStream();
         Envelope env = new Envelope(ss, config.getServerEndpoint());
         
         dispatcher.startConversation(new UpdateStreamStrategy(this,env));
     }
     public void stopUpdateStream(){
         for(ExecutionStrategy es : dispatcher.getEsMap().values()){
             try{
                 ((UpdateStreamStrategy)es).stop();
             }
             catch(ClassCastException e){
                 //nothing
             }
         }
     }


    public void toggleAuto() {
        auto = !auto;
    }
}
