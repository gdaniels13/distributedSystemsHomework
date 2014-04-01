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
    public JoinGameExecutionStrategy(Agent agent) {
        super(agent,null);
        sentRequest = false;
        AgentInfo agentInfo = new AgentInfo();
//        JoinGame jg = new JoinGame(
//                agent.config.getGameInfo().getId(), 
//                agent.config.getaNumber(), 
//                agent.config.getFirstName(),
//                agent.config.getLastName(),
//                agentInfo );
//       
        agentInfo.setAgentType(AgentInfo.PossibleAgentType.BrilliantStudent);
//        agentInfo.setAgentStatus(AgentInfo.PossibleAgentStatus.NotInGame);
        agentInfo.setANumber(agent.config.getaNumber());
        agentInfo.setLastName(agent.config.getLastName());
        agentInfo.setFirstName(agent.config.getFirstName());

        JoinGame jg= new JoinGame(agent.config.getGameInfo().getId(), agentInfo);
        
        first = new Envelope(jg, agent.config.getServerAddress(), agent.config.getServerPort());
        this.agent.communicator.send(first);
        first = this.agent.communicator.listen();
        System.out.println("i got something back");
    }
    
   
    
    @Override
    public void run() {
        if(!sentRequest){
            sendRequest();
        }
        else
        {
            receiveAck();
        }
    }

    private void sendRequest() {
        System.out.println("sending request");
        if(this.first == null){
            this.removeFromMap();
        }
        
        this.agent.communicator.send(first);
        sentRequest = true;
    }

    private void receiveAck() {
        System.out.println("received a response ");
        Envelope env = queue.poll();
        AckNak ack = (AckNak)env.getMessage();
        if(env == null || ack == null){
            //something weird happened
            return;
        }
        
        if(ack.Status!=Reply.PossibleStatus.Success){
            System.out.println("ERROR Failed to join game " + ack.getMessage() + ack.getNote());
            return;
        }
        
        
        
        
        
    }
}
