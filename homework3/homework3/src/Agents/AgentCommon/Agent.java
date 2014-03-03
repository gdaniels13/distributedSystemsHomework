package Agents.AgentCommon;

import Agents.BrilliantStudent.BrilliantStudent;
import Agents.ExcuseGenerator.ExcuseGenerator;
import Agents.TwineGenerator.TwineGenerator;
import Common.Tick;
import Communication.Communicator;
import Communication.EnvelopeQueue;
import Communication.Listener;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by gregor on 2/28/14.
 */
public abstract class Agent implements Runnable {
    Communicator communicator;
    Dispatcher dispatcher;
    Listener listener;
    EnvelopeQueue envelopeQueue;
    Config config;
    ConcurrentLinkedQueue<Tick> tickQueue;

    public static Agent Create(Config config){
        switch(config.getAgentType())
        {
            case "WG":
                return new TwineGenerator(config);
            case "BS":
                return new BrilliantStudent(config);
            case "EG":
                return new ExcuseGenerator(config);
            default:
                throw new IllegalArgumentException(config.getAgentType() + "is not a valid agent Type");
        }
    }

    public Agent(Config config) {
        this.config = config;
        this.communicator = new Communicator(config);
        this.envelopeQueue = new EnvelopeQueue();
        this.dispatcher = new Dispatcher(this.envelopeQueue,this);
        this.listener = new Listener(this.communicator,this.envelopeQueue);
        tickQueue = new ConcurrentLinkedQueue<>();

    }


}
