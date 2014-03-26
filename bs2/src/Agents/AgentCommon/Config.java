package Agents.AgentCommon;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.ws.WebServiceClient;
import registrarClient.EndPoint;
import registrarClient.GameInfo;
import registrarClient.GameInfoGameStatus;

/**
 * Created with IntelliJ IDEA. User: Greg Daniels A00798340 Date: 2/19/14 Time:
 * 2:27 PM
 */
public class Config {

    private int localPort;
    private int serverPort;
    public InetAddress serverAddress;
    private int messageLength = 1024;
    private String aNumber;
    private String firstName;
    private String lastName;
    private String agentType;
    private GameInfo gameInfo;

    public Config(int localPort, String aNumber, String firstName, String lastName) throws Exception {
        this.localPort = localPort;
        this.serverPort = serverPort;
        this.aNumber = aNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        pickfirstGame();

//        try {
//            this.serverAddress = InetAddress.getByName(serverAddress);
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
    }

    public Config(String[] args) throws Exception {
        this.agentType = args[0];
//        try {
//            this.serverAddress = InetAddress.getByName(args[1]);
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        this.serverPort = Integer.parseInt(args[2]);
        this.localPort = Integer.parseInt(args[3]);
        this.aNumber = args[4];
        this.firstName = args[5];
        this.lastName = args[6];
        pickfirstGame();
    }

    private void pickfirstGame() throws Exception {
        List<GameInfo> games = WebServerClient.getGames(GameInfoGameStatus.AVAILABLE).getGameInfo();
        if (games == null || games.size() == 0) {
            throw new Exception("No games found");
        } else {
            this.gameInfo = games.get(0);
            JAXBElement<EndPoint> ep = gameInfo.getCommunicationEndPoint();
            EndPoint value = ep.getValue();
            byte [] bytes = BigInteger.valueOf(value.getAddress()).toByteArray();
            reverseArray(bytes);
            this.serverAddress = InetAddress.getByAddress(bytes);
            this.serverPort = value.getPort();
        }
        System.out.println("picked game" + gameInfo.getLabel().getValue());
    }

    public void reverseArray(byte[] bytes){
        int begin = 0;
        int end = bytes.length-1;
        while(begin<end){
            byte temp = bytes[begin];
            bytes[begin] = bytes[end];
            bytes[end] = temp;
            end--;
            begin++;
        }
    }
    
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

    @Override
    public String toString() {
        return "Config{"
                + "localPort=" + localPort
                + ", serverPort=" + serverPort
                + ", serverAddress=" + serverAddress
                + ", messageLength=" + messageLength
                + ", aNumber='" + aNumber + '\''
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", agentType='" + agentType + '\''
                + '}';
    }

    public GameInfo getGameInfo() {
        return this.gameInfo;
    }

}
