package messageDefinition;

import Resources.Agent;

public class AgentsRes extends Response {
	public String agentType;
	public Agent[] agents;

    @Override
    public String serialize() {
        return null;
    }
}