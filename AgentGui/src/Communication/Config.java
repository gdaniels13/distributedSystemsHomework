package Communication;


import AgentCommon.Agent;
import Common.AgentInfo;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import webService.GameInfoAlt;
import webService.GameInfoGameStatus;


/**
 * Created with IntelliJ IDEA. User: Greg Daniels A00798340 Date: 2/19/14 Time:
 * 2:27 PM
 */
public class Config {

    private int localPort;
    private int serverPort;
    public InetAddress serverAddress;
    private int messageLength = 8192;
    private String aNumber;
    private String firstName;
    private String lastName;
    private AgentInfo.PossibleAgentType agentType;
    private GameInfoAlt gameInfo;
    private boolean autoSelect;
    private Endpoint serverEndpoint;
    public Config() throws Exception {
    }

    public Config(String[] args) throws Exception {
        switch (args[0]) {
            case "BS":
                this.agentType = AgentInfo.PossibleAgentType.BrilliantStudent;
                break;
            case "EG":
                this.agentType = AgentInfo.PossibleAgentType.ExcuseGenerator;
                break;
            case "WG":
                this.agentType = AgentInfo.PossibleAgentType.WhiningSpinner;
                break;
        }
        if (args[1].compareTo("Y") == 0) {
            autoSelect = true;
            pickGame();
            this.serverEndpoint = new Endpoint(serverPort, serverAddress);
        } else {
            autoSelect = false;
        }

        this.aNumber = args[2];
        this.firstName = args[3];
        this.lastName = args[4];
        this.serverEndpoint = new Endpoint(serverPort, serverAddress);

        
    }

    private void pickGame() throws Exception {
        List<GameInfoAlt> games = RegistryClient.getGames(GameInfoGameStatus.AVAILABLE).getGameInfoAlt();
        if (games == null || games.isEmpty()) {
            throw new Exception("No games found");
        } else {
            if (!autoSelect) {
                //getGame(games);
                return;
            } else {

                for (GameInfoAlt gi : games) {
                    if (gi.getLabel().getValue().compareTo("greg") == 0) {
                        this.gameInfo = gi;
                    }
                }
                if(this.gameInfo == null){
                    this.gameInfo = games.get(new Random().nextInt(games.size()));
                }
//                this.gameInfo = games.get(0);
            }
            String[] address = gameInfo.getCommunicationEndPoint().getValue().split(":");
            this.serverAddress =InetAddress.getByName(address[0]);
            this.serverPort = Integer.parseInt(address[1]);
            
//            JAXBElement<EndPoint> ep = gameInfo.getCommunicationEndPoint();
//            EndPoint value = ep.getValue();
//            byte[] bytes = BigInteger.valueOf(value.getAddress()).toByteArray();
//            reverseArray(bytes);
//            this.serverAddress = InetAddress.getByAddress(bytes);
//            this.serverPort = value.getPort();
        }

        if(Agent.DEBUG)System.out.println("picked game " + gameInfo.getLabel().getValue());
    }


    public Endpoint getServerEndpoint() {
        return serverEndpoint;
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
    
    public void setServerPort(int port){
        this.serverPort = port;
        this.serverEndpoint.setPort(port);
    }
    public InetAddress getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(InetAddress serverAddress) {
        this.serverAddress = serverAddress;
        this.serverEndpoint.setAddress(serverAddress);
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

    public AgentInfo.PossibleAgentType getAgentType() {
        return agentType;
    }

    public void setAgentType(AgentInfo.PossibleAgentType agentType) {
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

    public GameInfoAlt getGameInfo() {
        return this.gameInfo;
    }

    public void setGameInfo(GameInfoAlt gameInfo) {
        try {
            String ep[] = gameInfo.getCommunicationEndPoint().getValue().split(":");
            this.serverAddress = InetAddress.getByName(ep[0]);
            this.serverPort = Integer.parseInt(ep[1]);
            this.gameInfo = gameInfo;
            this.serverEndpoint = new Endpoint(serverPort, serverAddress);

        } catch (UnknownHostException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
