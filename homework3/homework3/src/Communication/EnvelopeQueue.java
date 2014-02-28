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

public class EnvelopeQueue {
    ConcurrentLinkedQueue<Envelope> eQueue;//queue to pull things outof


    public EnvelopeQueue(){
        this.eQueue = new ConcurrentLinkedQueue<>();
    }

    public void push(Envelope e) {
            eQueue.add(e);
    }

    public Envelope pop(){
        return eQueue.poll();
    }
}
