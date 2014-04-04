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
//        Envelope e = queue.poll();
//            Tick t = ((TickDelivery) e.getMessage()).getCurrentTick();
//            tickQueue.add(t);
//            removeFromMap();
    }
}
