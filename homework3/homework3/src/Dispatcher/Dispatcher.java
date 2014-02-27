package Dispatcher;

import Communication.Envelope;
import Communication.RequestEnvelopeQueue;

/**
 * Created by gregor on 2/25/14.
 */
public class Dispatcher implements Runnable{

    @Override
    public void run() {
        Envelope cur;
        while(true){
            cur = RequestEnvelopeQueue.pop();
            if(cur != null)
            {
                dispatch(cur);
            }
        }
    }

    private void dispatch(Envelope cur) {


    }


}
