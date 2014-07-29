package AgentCommon;

import ExecutionStrategies.ExecutionStrategy;
import Communication.Envelope;
import Communication.EnvelopeQueue;
import ExecutionStrategies.UpdateStreamStrategy;
import Gui.GameStatus;
import Messages.Message;
import Messages.Request;

import java.util.concurrent.*;

/**
 * Created by gregor on 2/25/14.
 *
 */
public class Dispatcher implements Runnable {

    private ConcurrentHashMap<String, ExecutionStrategy> esMap;
    private EnvelopeQueue envelopeQueue;
    private Agent agent;
    private ExecutorService threadPool;

    public Dispatcher(EnvelopeQueue envelopeQueue, Agent agent) {
        this.esMap = new ConcurrentHashMap<>();
        this.envelopeQueue = envelopeQueue;
        threadPool = Executors.newCachedThreadPool();
        this.agent = agent;
    }

    @Override
    public void run() {
        Envelope cur;
        long messagecount =0;
        while (true) {

            cur = envelopeQueue.pop();
            if (cur != null) {
//                System.out.println("dispatching a message");
                if (cur.getMessage().getClassId() != Message.MESSAGE_CLASS_IDS.TickDelivery.getValue()) {
//                    GameStatus.updateLog("received : " + cur.getMessage().MessageTypeId().toString()
//                            + " from " + cur.getAddress().toString());
                }
                System.out.println("dispatching message #" + messagecount ++);
                dispatch(cur);
                
            }

            try {
                Thread.currentThread().sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startConversation(ExecutionStrategy es) {
        esMap.put(es.getConversationId().toString(), es);
        es.setExecutableMap(esMap);
        threadPool.execute(es);
    }

    public void startConversation(Envelope cur) {
        ExecutionStrategy es = agent.CreateExecutionStrategy(cur);
        es.setExecutableMap(esMap);
        esMap.put(cur.getMessage().getConversationId().toString(), es);
        threadPool.execute(es);
    }

    public synchronized void dispatch(Envelope cur) {
        Message message = cur.getMessage();
        String conversationId = message.getConversationId().toString();

        //find the object that was handling that conversation
        ExecutionStrategy es = esMap.get(conversationId);

        if (es != null) { // conversation exists put the envelope on that objects queue and execute it
            //add code here to add things new execution strategies based on the
            es.put(cur);
//            if(es instanceof UpdateStreamStrategy)
//                threadPool.execute(es);
        } else // no conversation of that ID it should be a request
        {
            Request req;
            try {

                req = (Request) message;
            } catch (ClassCastException e) {
                return;
            }

            if (req.getConversationId().Equals(req.getMessageNr())) {
                //new Conversation create Correct ExecutionStrategy object and put it in the map
                es = agent.CreateExecutionStrategy(cur);
                if (es == null) {
                    return;
                }
                es.setExecutableMap(esMap);
                esMap.put(req.getConversationId().toString(), es);
                threadPool.execute(es);
            }
        }
    }

    public ConcurrentHashMap<String, ExecutionStrategy> getEsMap() {
        return esMap;
    }

    public ExecutorService getThreadPool() {
        return threadPool;
    }

    public EnvelopeQueue getEnvelopeQueue() {
        return envelopeQueue;
    }

    public Agent getAgent() {
        return agent;
    }
}
