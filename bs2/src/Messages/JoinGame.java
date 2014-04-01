package Messages;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.omg.CORBA.portable.ApplicationException;

import Common.AgentInfo;
import Common.ByteList;
import Common.ComponentInfo;

public class JoinGame extends Request {

    private static final Logger log = Logger.getLogger(JoinGame.class.getName());
    private static short ClassId;
    private short GameId;
    private AgentInfo AgentInfo;
    private static int MinimumEncodingLength;

    public JoinGame() {
        super(PossibleTypes.JoinGame);
    }

    public JoinGame(short gameId, AgentInfo agentInfo) //String aNumber, String firstName, String lastName, ComponentInfo agentInfo)
    {
        super(PossibleTypes.JoinGame);
        setGameId(gameId);
        setAgentInfo(agentInfo);
        /*setANumber(aNumber);
         setFirstName(firstName);
         setLastName(lastName);
         setAgentInfo(agentInfo);*/
        getClassId();
        getMinimumEncodingLength();
    }

    //new 
    public static JoinGame Create(ByteList messageBytes) throws ApplicationException, Exception {
        JoinGame result = null;

        if (messageBytes == null || messageBytes.getRemainingToRead() < getMinimumEncodingLength()) {
            throw new ApplicationException("Invalid message byte array", null);
        } else if (messageBytes.PeekInt16() != JoinGame.ClassId) {
            throw new ApplicationException("Invalid message class id", null);
        } else {
            result = new JoinGame();
            result.Decode(messageBytes);
        }

        return result;
    }

    @Override
    public void Encode(ByteList bytes) throws Exception {
        bytes.Add(getClassId());                              // Write out this class id first

        short lengthPos = bytes.getCurrentWritePosition();    // Get the current write position, so we
        // can write the length here later
        bytes.Add((short) 0);        //bytes.update();                      // Write out a place holder for the length
        bytes.update();
        super.Encode(bytes);        //bytes.update();       // Encode the part of the object defined
        // by the base class

        bytes.AddObjects(getGameId(), getAgentInfo());

        short length = (short) (bytes.getCurrentWritePosition() - lengthPos - 2);
        bytes.WriteInt16To(lengthPos, length);           // Write out the length of this object        
        bytes.update();
    }

    @Override
    public void Decode(ByteList bytes) throws Exception {
        short objType = bytes.GetInt16();
        short objLength = bytes.GetInt16();

        bytes.SetNewReadLimit(objLength);

        super.Decode(bytes);

        GameId = (bytes.GetInt16());

        AgentInfo = ((AgentInfo) bytes.GetDistributableObject());

        bytes.RestorePreviosReadLimit();
    }

    public short getGameId() {
        return GameId;
    }

    public void setGameId(short gameId) {
        GameId = gameId;
    }

    public AgentInfo getAgentInfo() {
        return AgentInfo;
    }

    public void setAgentInfo(AgentInfo agentInfo) {
        AgentInfo = agentInfo;
    }

    public static int getMinimumEncodingLength() {
        MinimumEncodingLength = 4 // Object header
                + 2 // ANumber
                + 2 // FirstName
                + 2 // LastName
                + 1;
        System.out.println("JoinGame.MinimumEncodingLength: " + MinimumEncodingLength);
        return MinimumEncodingLength;

    }

    public short getClassId() {
        ClassId = (short) MESSAGE_CLASS_IDS.JoinGame.getValue();
        System.out.println("JoinGame.ClassId: " + ClassId);
        return ClassId;
    }

    protected JoinGame(PossibleTypes type) {
        super(type);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public MESSAGE_CLASS_IDS MessageTypeId() {
        return Message.MESSAGE_CLASS_IDS.fromShort(ClassId);
    }
}
