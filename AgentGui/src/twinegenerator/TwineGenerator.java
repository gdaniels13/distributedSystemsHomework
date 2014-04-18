/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package twinegenerator;

import Communication.Config;
import Communication.Envelope;
import ExecutionStrategies.ExecutionStrategy;
import ExecutionStrategies.StartGameExecutionStrategy;
import Messages.Request;

/**
 *
 * @author gregor
 */
public class TwineGenerator extends AgentCommon.Agent{

    public TwineGenerator(Config config) {
        super(config);
    }

    @Override
    public ExecutionStrategy CreateExecutionStrategy(Envelope cur) {
                Request r = (Request) cur.getMessage();
        if(r == null) return null;
        switch(r.RequestType){
            case StartGame:
                return new StartGameExecutionStrategy(this, cur);
            case GetResource:
                return new TwineRequestExecutionStrategy(this, cur);
            default:
                return null;
        }
    }

    
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
