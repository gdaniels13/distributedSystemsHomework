import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/18/14
 * Time: 8:52 AM
 */
public class Temp
{
	public static void main(String [] args) throws SocketException, UnknownHostException
	{
		Listener listener = new Listener();
		Sender sender = new Sender();
		new Thread(listener).start();
		while(true){
			sender.send(Temp.getInput());
		}
	}

	public static String getInput()
	{
		BufferedReader inFromUser =
						new BufferedReader(new InputStreamReader(System.in));
		System.out.println("enter a string");
		try
		{
			return inFromUser.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return "something weird happened";
	}
}
