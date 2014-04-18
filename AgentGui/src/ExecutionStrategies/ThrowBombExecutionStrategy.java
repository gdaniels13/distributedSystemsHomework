/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExecutionStrategies;

import AgentCommon.Agent;
import Common.Bomb;
import Common.FieldLocation;
import Common.Tick;
import Communication.Envelope;
import Messages.AckNak;
import Messages.Reply;
import Messages.ThrowBomb;


public class ThrowBombExecutionStrategy extends ExecutionStrategy {
    private final Tick t;
    private final FieldLocation dest;
    private final Bomb b;

    public ThrowBombExecutionStrategy(Agent agent, Bomb b,FieldLocation dest, Tick t) {
        super(agent, null);
        this.b = b;
        this.dest = dest;
        this.t = t;
    }

    @Override
    public void run() {
        ThrowBomb tb = new ThrowBomb(agent.getPid(), b, dest, t);
        Envelope e = new Envelope(tb,agent.getConfig().getServerAddress(),agent.getConfig().getServerPort());
        e = reliableSendReceive(e);
        if(e == null){
            log("unable to throw bomb no ack received");
        }
        else{
            AckNak ack = (AckNak) e.getMessage();
            if(ack == null){
                log("got something weird");
                return;
            }
            if(ack.getStatus() == Reply.PossibleStatus.Failure){
                log("failed to throw bomb");
                return;
            }
            log("successfully threw bomb");
        }
    }
}
