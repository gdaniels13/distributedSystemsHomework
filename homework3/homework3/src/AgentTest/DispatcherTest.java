package AgentTest;

import Agents.AgentCommon.Agent;
import Agents.AgentCommon.Config;
import Agents.AgentCommon.Dispatcher;
import Agents.BrilliantStudent.BrilliantStudent;
import Communication.Envelope;
import Communication.EnvelopeQueue;
import Messages.Eat;
import org.junit.Test;

/**
 * Created by gregor on 3/3/14.
 */
public class DispatcherTest {
    @Test
    public void testRun() throws Exception {
//        Config config = new Config(new String("WG localhost 9876 9876 A00798340 Greg Daniels").split(" "));
        EnvelopeQueue envelopeQueue = new EnvelopeQueue();

        Dispatcher dispatcher =  new Dispatcher(envelopeQueue,null);
        Thread thread = new Thread(dispatcher);

        Eat eat = new Eat();
        Envelope envelope = new Envelope(eat,null,1234);

        envelopeQueue.push(envelope);




        thread.start();

    }

    @Test
    public void testDispatch() throws Exception {

    }
}
