package ExecutionStrategies;

import AgentCommon.Agent;
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

    public static final long TIMEOUT = 100;
    public static final int RETRIES = 5;
    protected long  timeout = TIMEOUT;
    protected int retries = RETRIES;
    protected ConcurrentLinkedQueue<Envelope> queue;
    protected Endpoint recipient;
    protected ConcurrentHashMap<String, ExecutionStrategy> executableMap; // need this so we can remove it from the map when we are finished
    protected Agent agent;
    protected Envelope first;

    public ExecutionStrategy(Agent agent, Envelope envelope) {
        queue = new ConcurrentLinkedQueue<>();
        this.agent = agent;
        this.first = envelope;
        if(envelope != null)
            this.recipient = envelope.getAddress();
    }

    //add an envelope to the queue of the execution strategy
    public synchronized void put(Envelope cur) {
        queue.add(cur);
        notify();
    }

    public synchronized Envelope reliableSendReceive(Envelope env) {
        Envelope toReturn = queue.poll();
            if (toReturn != null) {
                return toReturn;
            }
        
        for (int i = 0; i < retries; ++i) {
            agent.getCommunicator().send(env);
           if(Agent.DEBUG) System.out.println("retry:" + i);
            try {
                wait(timeout);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(ExecutionStrategy.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(Agent.DEBUG)System.out.println("Done Waiting:" +i);
            toReturn = queue.poll();
            if (toReturn != null) {
                return toReturn;
            }
            
        }
        return toReturn;
    }

    public void setExecutableMap(ConcurrentHashMap<String, ExecutionStrategy> executableMap) {
        this.executableMap = executableMap;
    }

    public ConcurrentHashMap<String, ExecutionStrategy> getExecutableMap() {
        return executableMap;
    }

    public void removeFromMap() {
        this.executableMap.remove(this.first.toString());
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

}
