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
    private final int port;
    private final InetAddress address;

    public GetResourceExecutionStrategy(Agent agent,GetResource.PossibleResourceType requestType,Endpoint serverEndpoint ) {
        super(agent, null);
        GetResource gr = new GetResource((short)10, requestType, new Tick());
        first = new Envelope(gr, serverEndpoint);
//        gr = new GetResource
        this.conversationId = gr.getConversationId();
        this.requestType = requestType;
        this.address = serverEndpoint.getAddress();
        this.port = serverEndpoint.getPort();
    }

    @Override
    public void run() {

        
        Envelope env = sendRequest();
        if(env == null)return;
        Reply m = (Reply) env.getMessage();
        if (m.getStatus() == Reply.PossibleStatus.Failure) {
            System.err.println("Failed to get Configuration");
            removeFromMap();
            return;
        } else {
            switch (m.getReplyType()) {
                case ResourceReply:
                    routeResource(((ResourceReply) m).getResource());
                case ConfigurationReply:
                    agent.setSetConfiguration(((ConfigurationReply) m).getConfiguration());
                case PlayingFieldReply:
                    agent.setPlayingField(((PlayingFieldReply) m).getLayout());
                case AgentListReply:
                    routeAgentLists(((AgentListReply) m).getAgents());
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
            case ExcuseGeneratorList:
                agent.setExcuseGeneratorList(list);
            case WhiningSpinnerList:
                agent.setWhiningSpinnerList(list);
            case ZombieProfessorList:
                agent.setZombieProfessorList(list);
        }
    }
}
