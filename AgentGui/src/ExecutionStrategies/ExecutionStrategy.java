package ExecutionStrategies;

import AgentCommon.Agent;
import Common.MessageNumber;
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

    

    public static final long TIMEOUT = 5000;
    public static final int RETRIES = 3;

    public Envelope getFirst() {
        return first;
    }

    public void setFirst(Envelope first) {
        this.first = first;
    }
    protected long timeout;
    protected int retries ;
    protected ConcurrentLinkedQueue<Envelope> queue;
    protected Endpoint recipient;
    protected ConcurrentHashMap<String, ExecutionStrategy> executableMap; // need this so we can remove it from the map when we are finished
    protected Agent agent;
    protected Envelope first;
    protected MessageNumber conversationId;
    public ExecutionStrategy(Agent agent, Envelope envelope) {
        if(envelope != null){
            this.conversationId = envelope.getMessage().getConversationId();
        }
        retries = RETRIES;
        timeout = TIMEOUT;
        queue = new ConcurrentLinkedQueue<>();
        this.agent = agent;
        this.first = envelope;
        if (envelope != null) {
            this.recipient = envelope.getAddress();
            this.conversationId = envelope.getMessage().getConversationId();
        }
    }

    //add an envelope to the queue of the execution strategy
    public synchronized void put(Envelope cur) {
        queue.add(cur);
        notifyAll();
    }

    
    public synchronized Envelope reliableSendReceive(Envelope env) {
        Envelope toReturn = queue.poll();
        if (toReturn != null) {
            return toReturn;
        }

        for (int i = 0; i < retries; ++i) {
            agent.getCommunicator().send(env);
            try {
                wait(timeout);

            } catch (InterruptedException ex) {
                Logger.getLogger(ExecutionStrategy.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            toReturn = queue.poll();
            if (toReturn != null) {
                return toReturn;
            }
            log("finished attempt " + i);
        }
        log("exiting send receive");
        return toReturn;
    }
    
    public MessageNumber getConversationId() {
        return conversationId;
    }

    public static void log(String message) {
        if (Agent.DEBUG) {
            System.out.println(message);
        }
    }

    public void setExecutableMap(ConcurrentHashMap<String, ExecutionStrategy> executableMap) {
        this.executableMap = executableMap;
    }

    public ConcurrentHashMap<String, ExecutionStrategy> getExecutableMap() {
        return executableMap;
    }

    public void removeFromMap() {
        this.executableMap.remove(this.first.getMessage().toString());
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
