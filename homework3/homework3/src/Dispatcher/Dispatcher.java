package Dispatcher;

import Communication.Envelope;
import Communication.RequestEnvelopeQueue;
import Messages.Message;
import Messages.Request;

import java.util.concurrent.*;

/**
 * Created by gregor on 2/25/14.
 **/
public class Dispatcher implements Runnable{

		private ConcurrentHashMap<String,ExecutionStrategy> esMap;
		private ExecutorService threadPool;
		public Dispatcher(){
			this.esMap = new ConcurrentHashMap<>();
			threadPool = Executors.newCachedThreadPool();
		}

    @Override
    public void run() {
        Envelope cur;
        while(true){
            cur = RequestEnvelopeQueue.pop();
            if(cur != null)
            {
                dispatch(cur);
            }
        }
    }

    private void dispatch(Envelope cur) {
			Message message = cur.getMessage();
			int classId = message.getClassId();
			String conversationId = message.getConversationId().toString();

			//find the object that was handling that conversation
			ExecutionStrategy es = esMap.get(conversationId);


			if(es != null){ // conversation exists put the envelope on that objects queue and execute it
				//add code here to add things new execution strategies based on the
				es.put(cur);
				threadPool.execute(es);
			}
			else // no conversation of that ID it should be a request
			{
				Request req = (Request)message;

				if(req==null)
				{
					//it wasn't a request we are safe to stop
					return;
				}

				if(req.getConversationId().Equals(req.getMessageNr())){
					//new Conversation create Correct ExecutionStrategy object and put it in the map
					es = ExecutionStrategy.Create(cur);
					es.setExecutableMap(esMap);
					esMap.put(req.getConversationId().toString(), es);
					threadPool.execute(es);
				}

			}

    }
}
