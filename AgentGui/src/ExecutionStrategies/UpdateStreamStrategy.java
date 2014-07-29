/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExecutionStrategies;

import AgentCommon.Agent;
import Common.AgentList;
import Communication.Envelope;
import Gui.GameStatus;
import Messages.AckNak;
import Messages.AgentListReply;
import Messages.EndUpdateStream;
import Messages.GetResource;
import Messages.Reply;

public class UpdateStreamStrategy extends ExecutionStrategy {

    private boolean started;
    int count;

    public UpdateStreamStrategy(Agent agent, Envelope envelope) {
        super(agent, envelope);
        started = false;
        count = 0;
    }

    @Override
    public void run() {
        if (!started) {
            start();
        }
        Envelope env = queue.poll();
        while (started) {
            if (env != null) {
                try {
                    // GameStatus.updateLog("Updating agent lists");
                    System.out.println("got new list" + count++ + " " + queue.size());

                    if (false) {
                        agent.updateMaps(((AgentListReply) env.getMessage()).getAgents());
                    }
                    else{
                        agent.requestResourceFromServer(GetResource.PossibleResourceType.BrillianStudentList);
                        agent.requestResourceFromServer(GetResource.PossibleResourceType.ExcuseGeneratorList);
                        agent.requestResourceFromServer(GetResource.PossibleResourceType.WhiningSpinnerList);
                        agent.requestResourceFromServer(GetResource.PossibleResourceType.ZombieProfessorList);
                    }
                    env = receive();
                } catch (ClassCastException es) {
                    GameStatus.updateLog("wrong type of message");
                }
            } else {
                env = receive();
            }
        }

    }

    public synchronized Envelope receive() {
        if (queue.peek() != null) {
            return queue.poll();
        }
        try {
            wait(timeout);
        } catch (InterruptedException ex) {
        }
        return queue.poll();
    }

    public void stop() {
        started = false;
        endStream();
        removeFromMap();
    }

    private synchronized void start() {
        while (queue.poll() != null);
        Envelope env = reliableSendReceive(first);
        if (env == null || ((AckNak) env.getMessage()).getStatus() == Reply.PossibleStatus.Failure) {
            GameStatus.updateLog("unable to start stream");
            removeFromMap();
        } else {
            GameStatus.updateLog("Successfully started stream");
            started = true;
        }
    }

    private void endStream() {
        //no end update stream message
        Envelope env = new Envelope(new EndUpdateStream(), recipient);
        reliableSendReceive(env);
    }

}
