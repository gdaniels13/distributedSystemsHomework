package Agents;

import Communication.Config;


/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/20/14
 * Time: 4:08 PM
 */

public class Doer implements Runnable
{
	boolean shouldRun;
	
	public synchronized void start(){
		shouldRun = true;
	}

	public synchronized void stop(){
		shouldRun = true;
	}

	@Override
	public void run()
	{
		while(true){
			while(shouldRun){

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
}
