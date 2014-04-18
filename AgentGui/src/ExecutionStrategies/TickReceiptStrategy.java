package ExecutionStrategies;

import AgentCommon.Agent;
import Common.Tick;
import Communication.Envelope;
import Messages.TickDelivery;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by gregor on 2/28/14.
 */
public class TickReceiptStrategy extends ExecutionStrategy {


    public TickReceiptStrategy(Agent agent, Envelope cur) {
        super(agent, cur);

    }

    @Override
    public void run() {
            Tick t = ((TickDelivery) first.getMessage()).getCurrentTick();
            agent.getTickQueue().add(t);
            agent.notifyGui();
            log("got tick");
            removeFromMap();
    }
}
