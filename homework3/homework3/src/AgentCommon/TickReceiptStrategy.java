package AgentCommon;

import Common.Tick;
import Communication.Envelope;
import Messages.TickDelivery;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by gregor on 2/28/14.
 */
public class TickReceiptStrategy extends ExecutionStrategy{

	protected ConcurrentLinkedQueue<Tick> tickQueue;



	public TickReceiptStrategy(ConcurrentLinkedQueue<Tick> tickQueue)
	{
		this.tickQueue = tickQueue;
	}


	@Override
	public void run()
	{
		Envelope e = queue.poll();
		while(e !=null){
			Tick t = ((TickDelivery)e.getMessage()).getCurrentTick();
			tickQueue.add(t);
		}
	}
}
