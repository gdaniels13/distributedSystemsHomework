package AgentCommon;

import Common.Tick;
import Communication.Communicator;
import Communication.Config;
import Communication.EnvelopeQueue;
import Communication.Listener;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by gregor on 2/28/14.
 */
public abstract class Agent {
    Communicator communicator;
    Dispatcher dispatcher;
    Listener listener;
    EnvelopeQueue envelopeQueue;
    Config config;
    ConcurrentLinkedQueue<Tick> tickQueue;


    protected Agent(Config config) {
        this.config = config;
        this.communicator = new Communicator(config);
        this.envelopeQueue = new EnvelopeQueue();
        this.dispatcher = new Dispatcher(this.envelopeQueue);
        this.listener = new Listener(this.communicator,this.envelopeQueue);
        tickQueue = new ConcurrentLinkedQueue<>();
    }
}
