/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExecutionStrategies;

import AgentCommon.Agent;
import Common.FieldLocation;
import Common.Tick;
import Communication.Envelope;
import Messages.Move;

/**
 *
 * @author gregor
 */
public class MoveExecutionStrategy extends ExecutionStrategy{
    private final FieldLocation dest;
    private final Tick t;

    public MoveExecutionStrategy(Agent agent, Tick t, FieldLocation dest) {
        super(agent, null);
        this.t = t;
        this.dest = dest;
    }

    @Override
    public void run() {
        Move move = new Move(agent.getPid(), dest, t);
        Envelope e = new Envelope(move, agent.getConfig().getServerAddress(),agent.getConfig().getServerPort());
        e = reliableSendReceive(e);
        
        if(e == null){
            log("unable to move no ack received");
        }
    }
    
}
