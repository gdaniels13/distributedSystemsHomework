package AgentCommon;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 2/19/14
 * Time: 2:27 PM
 */

public class Config
{
	private int localPort;
	private int serverPort;
    public InetAddress serverAddress;
	private int messageLength = 1024;
    private String aNumber;

    public int getLocalPort() {
        return localPort;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public InetAddress getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(InetAddress serverAddress) {
        this.serverAddress = serverAddress;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public void setMessageLength(int messageLength) {
        this.messageLength = messageLength;
    }

    public String getaNumber() {
        return aNumber;
    }

    public void setaNumber(String aNumber) {
        this.aNumber = aNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    private String firstName;
    private String lastName;
    private String agentType;


    public Config(int localPort, int serverPort, String serverAddress, String aNumber, String firstName, String lastName)  {
        this.localPort = localPort;
        this.serverPort = serverPort;
        this.aNumber = aNumber;
        this.firstName = firstName;
        this.lastName = lastName;

        try {
            this.serverAddress = InetAddress.getByName(serverAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    public Config(String[] args)  {
        this.agentType = args[0];
        try {
            this.serverAddress = InetAddress.getByName(args[1]);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.serverPort = Integer.parseInt(args[2]);
        this.localPort = Integer.parseInt(args[3]);
        this.aNumber = args[4];
        this.firstName = args[5];
        this.lastName = args[6];
    }

    @Override
    public String toString() {
        return "Config{" +
                "localPort=" + localPort +
                ", serverPort=" + serverPort +
                ", serverAddress=" + serverAddress +
                ", messageLength=" + messageLength +
                ", aNumber='" + aNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", agentType='" + agentType + '\'' +
                '}';
    }
}
