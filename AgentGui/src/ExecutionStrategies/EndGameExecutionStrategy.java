/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExecutionStrategies;

import AgentCommon.Agent;
import Communication.Envelope;
import Gui.GameStatus;
import Messages.EndGame;

public class EndGameExecutionStrategy extends ExecutionStrategy {

    public EndGameExecutionStrategy(Agent agent, Envelope envelope) {
        super(agent, envelope);
    }

    @Override
    public void run() {
        //compare endpoints make sure its from the right person
        if (agent.verifyServer(first)) {
            agent.stop();
            EndGame eg = (EndGame) first.getMessage();
            GameStatus.updateLog("Received EndGame from " + first.getAddress().toString());

        } else {
            GameStatus.updateLog("Received EndGame from " + first.getAddress().toString());
        }
    }

}
