package Agents.AgentCommon;

import Communication.Endpoint;
import Communication.Envelope;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by gregor on 2/27/14.
 */
public abstract class ExecutionStrategy implements Runnable {
    public static final int TIMEOUT = 500;
    public static final int RETRIES = 3;
    protected ConcurrentLinkedQueue<Envelope> queue;
    protected Endpoint recipient;
    protected ConcurrentHashMap<String, ExecutionStrategy> executableMap; // need this so we can remove it from the map when we are finished
    protected Agent agent;
    protected Envelope first;

    public ExecutionStrategy(Agent agent, Envelope envelope) {
        queue = new ConcurrentLinkedQueue<>();
        this.agent = agent;
        this.first = envelope;
    }

    //add an envelope to the queue of the execution strategy
    public synchronized void put(Envelope cur) {
        queue.add(cur);
    }

    public void setExecutableMap(ConcurrentHashMap<String, ExecutionStrategy> executableMap) {
        this.executableMap = executableMap;
    }

    
    public synchronized Envelope reliableSendReceive(Envelope env){
        for(int i =0; i<RETRIES; ++i){
            agent.communicator.send(env);
            
            try {
                wait(TIMEOUT);
            } catch (InterruptedException ex) {
                Logger.getLogger(ExecutionStrategy.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }
        return null;
    }
    
    
    public ConcurrentHashMap<String, ExecutionStrategy> getExecutableMap() {
        return executableMap;
    }

    public void removeFromMap() {
        this.executableMap.remove(this.first.toString());
    }
}
