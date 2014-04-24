package ExecutionStrategies;

import AgentCommon.Agent;
import Common.AgentInfo;
import Common.MessageNumber;
import Communication.Envelope;
import Messages.AckNak;
import Messages.JoinGame;
import Messages.Reply;

/**
 * Created by gregor on 3/3/14.
 */
public class JoinGameExecutionStrategy extends ExecutionStrategy {
    
    private boolean sentRequest;
    public JoinGameExecutionStrategy(final Agent agent) {
        super(agent,null);
        sentRequest = false;
        AgentInfo agentInfo = new AgentInfo();
        agentInfo.setId(MessageNumber.LocalProcessId);
        agentInfo.setAgentType(agent.getConfig().getAgentType());
        agentInfo.setAgentStatus(AgentInfo.PossibleAgentStatus.NotInGame);
        agentInfo.setANumber(agent.getConfig().getaNumber());
        agentInfo.setLastName(agent.getConfig().getLastName());
        agentInfo.setFirstName(agent.getConfig().getFirstName());
        
        JoinGame jg= new JoinGame(agent.getConfig().getGameInfo().getId(), agentInfo);
        
        first = new Envelope(jg, agent.getConfig().getServerAddress(), agent.getConfig().getServerPort());
        final JoinGameExecutionStrategy bob = this;
        new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                Envelope e;
                e = agent.getCommunicator().listen();
                if(Agent.DEBUG) System.out.println("got a message from "  + e.getAddress());
                bob.put(e);
            }
        }).start();
        
        first = reliableSendReceive(first);
        if(first != null){
            AckNak ack = (AckNak)first.getMessage();
            if(ack == null)
            {
                agent.setError("result is not an ack");
                System.exit(1);
            }
            else{
                
                if(((AgentInfo)ack.getObjResult())!=null){
                    agent.setAgentInfo((AgentInfo)ack.getObjResult());
                }
                else{
                    agent.setError("unable to join game objresult was null");
                    System.exit(1);

                }
            }
        }
        else{
            log("no Response received");
            System.exit(1);
        }
        log("sending reply");
//        Message replyMessage =// new ReadyReply(Reply.PossibleStatus.Success, "this is a test");
        AckNak replyMessage = new AckNak(Reply.PossibleStatus.Success,agentInfo);
        
        replyMessage.setConversationId(first.getMessage().getConversationId());
        Envelope replyEnvelope = new Envelope(replyMessage, agent.getConfig().getServerAddress(), agent.getConfig().getServerPort());
        agent.communicator.send(replyEnvelope);
        
       // Envelope a = agent.communicator.listen();
        //log(a.getAddress().toString());
//        removeFromMap();
    }
    
   
    
    @Override
    public void run() {}


}
