/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package excusegenerator;

import AgentCommon.Agent;
import Communication.Config;
import ExecutionStrategies.ExecutionStrategy;
import ExecutionStrategies.StartGameExecutionStrategy;
import Messages.Request;

/**
 *
 * @author gregor
 */
public class ExcuseGenerator extends Agent{

    public ExcuseGenerator(Config config) {
        super(config);
        
    }


    @Override
    public ExecutionStrategy CreateExecutionStrategy(Communication.Envelope cur) {
        Request r = (Request) cur.getMessage();
        if(r == null) return null;
        switch(r.RequestType){
            case StartGame:
                return new StartGameExecutionStrategy(this, cur);
            case GetResource:
                return new ExcuseRequestExecutionStrategy(this, cur);
            default:
                return null;
        }
        
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
