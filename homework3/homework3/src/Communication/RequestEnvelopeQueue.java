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
    ConcurrentHashMap<String,ConcurrentLinkedQueue<Envelope>> qMap;


    private static RequestEnvelopeQueue instance;

    private RequestEnvelopeQueue(){
        qMap = new ConcurrentHashMap<>();
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
        String conversationId =e.getMessage().getConversationId().toString();
        String messageNr = e.getMessage().getMessageNr().toString();
        ConcurrentLinkedQueue<Envelope> t = instance.qMap.get(conversationId);

        if(t==null && conversationId.compareTo(messageNr) == 0){ //new Message insert new Queue
            t = new ConcurrentLinkedQueue<>();
            t.add(e);
            qMap.put(conversationId,t);
        }
        else if(t!= null){ //conversation id exists add the message to the queue
            t.add(e);
        }

    }

    public static Envelope pop(MessageNumber mn){

    }


}
