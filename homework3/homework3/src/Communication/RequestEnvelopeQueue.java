package Communication;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by gregor on 2/18/14.
 * this is a go-between between the receiver and the router
 *
 */

public class RequestEnvelopeQueue
{
    ConcurrentLinkedQueue<Envelope> queue;
    private static RequestEnvelopeQueue instance;

    private RequestEnvelopeQueue(){
        queue = new ConcurrentLinkedQueue<>();
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
        init();
        instance.queue.add(e);
    }

    public static Envelope pop(){
        init();
        return instance.queue.poll();
    }

		public static int getSize(){
			init();
			return instance.queue.size();
		}
}
