package Communication;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/20/14
 * Time: 2:20 PM
 */
public class Listener implements Runnable
{

	boolean shouldRun;

	public Listener(){
		shouldRun = false;
	}

	@Override
	public void run()
	{
		//once it starts always listen.
		while(true){
			while(shouldRun){
				Envelope t = Communicator.receive();
				if(t!=null){
					RequestEnvelopeQueue.push(t);
				}
			}
			while(!shouldRun){
				try
				{
					Thread.currentThread().sleep(Config.listenTimeout);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized void stop(){
		shouldRun = false;
	}

	public synchronized void start(){
		shouldRun = true;
	}

}
