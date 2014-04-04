package Agents.TwineGenerator;

import Agents.AgentCommon.Agent;
import Agents.AgentCommon.Config;
import Agents.AgentCommon.ExecutionStrategy;
import Communication.Envelope;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/28/14
 * Time: 11:44 AM
 */
public class TwineGenerator extends Agent
{
	public TwineGenerator(Config config)
	{
		super(config);
	}

    @Override
    public ExecutionStrategy CreateExecutionStrategy(Envelope cur) {
        return null;
    }

    @Override
    public void run() {

    }
}
