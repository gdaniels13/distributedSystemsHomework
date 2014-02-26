package Doer;

import Communication.Envelope;
import Communication.RequestEnvelopeQueue;
import Messages.*;

import java.lang.reflect.Type;

/**
 * Created by gregor on 2/25/14.
 */
public class Doer implements Runnable{
    @Override
    public void run() {
        Envelope cur;
        while(true){
            cur = RequestEnvelopeQueue.pop();
            if(cur != null)
            {
                route(cur);

            }
        }
    }

    private void route(Envelope cur) {
        Class t = cur.getClass();

        if(t == AckNak.class){
             
        }




    }
}
