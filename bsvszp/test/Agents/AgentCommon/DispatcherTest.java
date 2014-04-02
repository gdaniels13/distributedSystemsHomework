package Agents.AgentCommon;

import Agents.AgentCommon.*;
import Agents.BrilliantStudent.BrilliantStudent;
import Common.ComponentInfo;
import Communication.Envelope;
import Messages.JoinGame;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;

/**
 * Created by gregor on 3/3/14.
 */
public class DispatcherTest {
//    @Test
//    public void testRun() throws Exception {
//        Config config = new Config(new String("WG localhost 9876 9876 A00798340 Greg Daniels").split(" "));
//        BrilliantStudent brilliantStudent= new BrilliantStudent(config);
//        Dispatcher dispatcher =  brilliantStudent.getDispatcher();
//        Thread thread = new Thread(dispatcher);
//        JoinGame jg = new JoinGame((short) 123,"asdf","asdfff","asdff",new ComponentInfo((short) 123, ComponentInfo.PossibleAgentType.BrilliantStudent));
//        Envelope envelope = new Envelope(jg,null,1234);
//
//        thread.start();
//
//        dispatcher.getEnvelopeQueue().push(envelope);
//
//        Thread.currentThread().sleep(200);
//
//        ConcurrentHashMap<String,ExecutionStrategy> esMap;
//        esMap = dispatcher.getEsMap();
//        int size  = esMap.size();
//        assertEquals(size,1);  // it inserted the execution strategy into the queue all is good
//        // also means dispatch worked too
//
//        ExecutionStrategy es = esMap.get(jg.getConversationId().toString());
//
//        assertNotNull(es);
//        assertEquals(es.getClass(), JoinGameExecutionStrategy.class);
//    }

}
