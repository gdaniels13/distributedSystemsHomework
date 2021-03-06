package Communication;


/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/20/14
 * Time: 2:20 PM
 */
public class Listener implements Runnable
{

    Communicator communicator;
    EnvelopeQueue envelopeQueue;

    public Listener(Communicator communicator,EnvelopeQueue envelopeQueue){
        this.communicator = communicator;
        this.envelopeQueue = envelopeQueue;
    }

    @Override
    public void run()
    {
        //once it starts always listen.
        while(true){
            Envelope t = communicator.listen();
            if(t!=null){
                envelopeQueue.push(t);
//                System.out.println("************" + t.getMessage());
//                System.out.println("got a message : " + t.getMessage().getConversationId());
            }
        }
    }

    public Communicator getCommunicator() {
        return communicator;
    }

    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }

    public EnvelopeQueue getEnvelopeQueue() {
        return envelopeQueue;
    }

    public void setEnvelopeQueue(EnvelopeQueue envelopeQueue) {
        this.envelopeQueue = envelopeQueue;
    }
}
