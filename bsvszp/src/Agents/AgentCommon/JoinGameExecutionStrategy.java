package Agents.AgentCommon;

import Common.AgentInfo;
import Common.ComponentInfo;
import Communication.Envelope;
import Messages.AckNak;
import Messages.JoinGame;
import Messages.Reply;
//import registrarClient.ComponentInfo;

/**
 * Created by gregor on 3/3/14.
 */
public class JoinGameExecutionStrategy extends ExecutionStrategy {
    
    private boolean sentRequest;
    public JoinGameExecutionStrategy(final Agent agent) {
        super(agent,null);
        sentRequest = false;
        AgentInfo agentInfo = new AgentInfo();
        
        agentInfo.setAgentType(AgentInfo.PossibleAgentType.BrilliantStudent);
        agentInfo.setAgentStatus(AgentInfo.PossibleAgentStatus.NotInGame);
        agentInfo.setANumber(agent.config.getaNumber());
        agentInfo.setLastName(agent.config.getLastName());
        agentInfo.setFirstName(agent.config.getFirstName());

        JoinGame jg= new JoinGame(agent.config.getGameInfo().getId(), agentInfo);
        
        first = new Envelope(jg, agent.config.getServerAddress(), agent.config.getServerPort());

        new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                Envelope e;
                e = agent.getCommunicator().listen();
                put(e);
            }
        }).start();
        first = reliableSendReceive(first);
        if(first != null){
            AckNak ack = (AckNak)first.getMessage();
            if(ack == null)
            {
                agent.setError("result is not an ack");
            }
            else{
                if(((AgentInfo)ack.getObjResult())!=null){
                    agent.setAgentInfo((AgentInfo)ack.getObjResult());
                }
                else{
                    agent.setError("unable to join game objresult was null");
                }
                
            }
        }
        else{
            agent.setError("Result was null");         
        }
    }
    
   
    
    @Override
    public void run() {}


}
