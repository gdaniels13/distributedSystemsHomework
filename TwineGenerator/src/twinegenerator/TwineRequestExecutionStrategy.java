/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package twinegenerator;

import AgentCommon.Agent;
import Communication.Envelope;

/**
 *
 * @author gregor
 */
public class TwineRequestExecutionStrategy extends ExecutionStrategies.ExecutionStrategy{

    public TwineRequestExecutionStrategy(Agent agent, Envelope envelope) {
        super(agent, envelope);
    }

    @Override
    public void run() {
        
    }
    
}
