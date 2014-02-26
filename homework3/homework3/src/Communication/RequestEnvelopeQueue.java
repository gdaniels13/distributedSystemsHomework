package Communication;

import Common.MessageNumber;
import Messages.Message;
import com.sun.xml.internal.bind.v2.util.QNameMap;
import sun.security.jca.GetInstance;

import java.awt.image.ConvolveOp;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by gregor on 2/18/14.
 * this is a go-between between the receiver and the router
 *
 */

public class RequestEnvelopeQueue
{
    ConcurrentHashMap<String,String> qMap; //keep a record of valid conversations
    ConcurrentLinkedQueue<Envelope> eQueue;//queue to pull things outof

    private static RequestEnvelopeQueue instance;

    private RequestEnvelopeQueue(){
        qMap = new ConcurrentHashMap<>();
        this.eQueue = new ConcurrentLinkedQueue<>();
    }

    public static RequestEnvelopeQueue getInstance(){
        if(instance == null){
            instance = new RequestEnvelopeQueue();
        }
        return instance;
    }

    private static void init(){
        getInstance();
    }

    public static void push(Envelope e){
        getInstance();
        instance.insert(e);
    }

    private void insert(Envelope e) {
        String conversationId = e.getMessage().getConversationId().toString();
        String messageNr = e.getMessage().getMessageNr().toString();
        String t = instance.qMap.get(conversationId);

        if(t==null && conversationId.compareTo(messageNr) == 0){ //new Message insert new Queue
            qMap.put(conversationId,t);
            eQueue.add(e);
        }
        else if(t!= null){ //conversation id exists add the message to the queue
            eQueue.add(e);
        }
    }

    public static Envelope pop(){
        init();
        return instance.eQueue.poll();
    }
}
