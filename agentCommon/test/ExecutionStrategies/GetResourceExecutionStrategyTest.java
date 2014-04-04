/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExecutionStrategies;

import AgentCommon.Agent;
import AgentCommon.Dispatcher;
import Common.AgentInfo;
import Common.Excuse;
import Common.Tick;
import Communication.Communicator;
import Communication.Config;
import Communication.Endpoint;
import Communication.Envelope;
import Communication.EnvelopeQueue;
import Communication.Listener;
import Messages.GetResource;
import Messages.Reply;
import Messages.ResourceReply;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author gregor
 */
public class GetResourceExecutionStrategyTest {

    Agent ag;
    Communication.Communicator otherComm;
    Config oconfig,agconfig;

    public GetResourceExecutionStrategyTest() throws Exception {
        //create brilliant student;
        agconfig = new Config();
        ag = new Agent(agconfig) {
            @Override
            public void init() {
                this.communicator = new Communicator(config);
                this.envelopeQueue = new EnvelopeQueue();
                this.dispatcher = new Dispatcher(this.envelopeQueue, this);
                this.listener = new Listener(this.communicator, this.envelopeQueue);
                this.tickQueue = new ConcurrentLinkedQueue<>();
                this.bombQueue = new ConcurrentLinkedQueue<>();
                this.excuseQueue = new ConcurrentLinkedQueue<>();
                this.twineQueue = new ConcurrentLinkedQueue<>();
                this.newResources = new ConcurrentLinkedQueue<>();
            }

            @Override
            public ExecutionStrategy CreateExecutionStrategy(Envelope cur) {
                return new GetResourceExecutionStrategy(this, cur);
            }

            @Override
            public void run() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        //create second communicator for it to talk to;
        oconfig = new Config();
        otherComm = new Communicator(oconfig);

    }

    /**
     * Test of run method, of class GetResourceExecutionStrategy.
     */
    @Test
    public void testRun() throws UnknownHostException, InterruptedException {
        Messages.GetResource gr = new GetResource((short) 12, GetResource.PossibleResourceType.Excuse, new Tick(1, 1));
        Endpoint ep = new Endpoint(oconfig.getLocalPort(), InetAddress.getByName("localhost"));
        Envelope env = new Envelope(gr, ep);
        EnvelopeQueue eq = new EnvelopeQueue();
        Listener list = new Listener(otherComm, eq);
        new Thread(list).start();
        new Thread(ag.CreateExecutionStrategy(env)).start();

        Envelope e=eq.pop();
        while(e == null){
            wait(500);
            e = eq.pop();
        }
        assertNotNull(e);
        Envelope p= new Envelope( new ResourceReply(Reply.PossibleStatus.Success, new Excuse((short)10, new ArrayList<Tick>(), new Tick((short)1, 12)), ""),e.getAddress());
        otherComm.send(p);
                
        
        wait(1000);
        assertEquals(ag.getExcuseQueue().size(), 1);
        
        
    }

}
