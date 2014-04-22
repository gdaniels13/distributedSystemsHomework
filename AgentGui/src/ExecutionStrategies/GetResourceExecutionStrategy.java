/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExecutionStrategies;

import AgentCommon.Agent;
import Common.AgentList;
import Common.DistributableObject;
import Common.Excuse;
import Common.Tick;
import Common.WhiningTwine;
import Communication.Endpoint;
import Communication.Envelope;
import Gui.GameStatus;
import Messages.AgentListReply;
import Messages.ConfigurationReply;
import Messages.GetResource;
import Messages.PlayingFieldReply;
import Messages.Reply;
import Messages.ResourceReply;
import java.net.InetAddress;

public class GetResourceExecutionStrategy extends ExecutionStrategy {

    private final GetResource.PossibleResourceType requestType;

    public GetResourceExecutionStrategy(Agent agent, GetResource.PossibleResourceType requestType, Endpoint serverEndpoint) {
        super(agent, null);


        GetResource gr = new GetResource((short) 10, requestType, new Tick());
        this.conversationId = gr.getConversationId();
                first = new Envelope(gr, serverEndpoint);

//        gr = new GetResource
        this.requestType = requestType;
    }

    @Override
    public void run() {
        
           if (requestType == GetResource.PossibleResourceType.Excuse || requestType == GetResource.PossibleResourceType.WhiningTwine) {
            ((GetResource)first.getMessage()).setEnablingTick(agent.getTickQueue().poll());
            if(((GetResource)first.getMessage()).getEnablingTick() == null){
                GameStatus.updateLog("cannot request resource: no ticks available");
            }
        }


        Envelope env = sendRequest();
        if (env == null) {
            return;
        }
        
        Reply m = (Reply) env.getMessage();
        if (m.getStatus() == Reply.PossibleStatus.Failure) {
            GameStatus.updateLog("Failed to get Resource " + m.getNote());
            removeFromMap();
        } else {
            switch (m.getReplyType()) {
                case ResourceReply:
                    routeResource(((ResourceReply) m).getResource());
                    break;
                case ConfigurationReply:
                    agent.setSetConfiguration(((ConfigurationReply) m).getConfiguration());
                    break;
                case PlayingFieldReply:
                    agent.setPlayingField(((PlayingFieldReply) m).getLayout());
                    break;
                case AgentListReply:
                    routeAgentLists(((AgentListReply) m).getAgents());
                    break;
                case StatusReply:
                    break;
                default:
            }
//            agent.addResource(cr.getConfiguration());
            removeFromMap();
        }
    }

    private Envelope sendRequest() {
        Envelope env = reliableSendReceive(first);
        if (env == null) {
            GameStatus.updateLog("Nothing received ");
//            System.exit(1);
        }
        return env;
    }

    private void routeResource(DistributableObject resource) {
        if (resource instanceof Excuse) {
            agent.getExcuseQueue().add((Excuse) resource);
        } else if (resource instanceof WhiningTwine) {
            agent.getTwineQueue().add((WhiningTwine) resource);
        }
    }

    private GetResource.PossibleResourceType getListType() {
        GetResource gr = (GetResource) first.getMessage();
        return gr.getGetType();
    }

    private void routeAgentLists(AgentList list) {
        switch (getListType()) {
            case BrillianStudentList:
                agent.setBrilliantStudentList(list);
                break;
            case ExcuseGeneratorList:
                agent.setExcuseGeneratorList(list);
                break;
            case WhiningSpinnerList:
                agent.setWhiningSpinnerList(list);
                break;
            case ZombieProfessorList:
                agent.setZombieProfessorList(list);
                break;
        }
    }
}
