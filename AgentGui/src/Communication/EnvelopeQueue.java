package Communication;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by gregor on 2/18/14.
 * this is a go-between between the receiver and the router
 *
 */

public class EnvelopeQueue {
    ConcurrentLinkedQueue<Envelope> eQueue;//queue to pull things outof


    public EnvelopeQueue(){
        this.eQueue = new ConcurrentLinkedQueue<>();
    }

    public synchronized void push(Envelope e) {
            eQueue.add(e);
    }

    public synchronized Envelope pop(){
        return eQueue.poll();
    }
}
