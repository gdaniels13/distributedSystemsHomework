package Communication;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/19/14
 * Time: 2:27 PM
 */

public class Config
{
	private int port=9834;
	private int messageLength = 1024;

    public int getPort() {
        return port;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public void setMessageLength(int messageLength) {
        this.messageLength = messageLength;
    }

    public Config(int port) {
        this.port = port;
    }
}
