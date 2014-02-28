package AgentCommon;

import Communication.Endpoint;
import Communication.Envelope;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by gregor on 2/27/14.
 */
public abstract class ExecutionStrategy implements Runnable{
	private ConcurrentLinkedQueue<Envelope> queue;
	private Endpoint recipient;
	private ConcurrentHashMap<String, ExecutionStrategy> executableMap; // need this so we can remove it from the map when we are finished

	public ExecutionStrategy(){
		queue = new ConcurrentLinkedQueue<>();
	}

	//add an envelope to the queue of the execution strategy
	void put(Envelope cur){
		queue.add(cur);
	}

	public static ExecutionStrategy Create(Envelope cur)
	{
		//needs to detect the message type and create the Execution strategy;


		return null;
	}

	public void setExecutableMap(ConcurrentHashMap<String, ExecutionStrategy> executableMap)
	{
		this.executableMap = executableMap;
	}

	public ConcurrentHashMap<String, ExecutionStrategy> getExecutableMap()
	{
		return executableMap;
	}
}
