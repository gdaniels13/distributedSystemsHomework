import sun.security.jca.GetInstance;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by gregor on 2/18/14.
 */
public class MessageQueue {
    ArrayDeque<Envelope> queue;

    private static MessageQueue instance;

    private MessageQueue(){

        queue = new ArrayDeque<>();
    }

    public static MessageQueue getInstance(){
        if(instance == null){
            instance = new MessageQueue();
        }

        return instance;
    }

    private static void init(){
        getInstance();
    }

    public static void push(Envelope e){
        init();
        instance.queue.push(e);
    }

    public static Envelope pop(){
        init();
        return instance.queue.pop();
    }



}
