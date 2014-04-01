
import Common.AgentInfo;
import Common.ByteList;
import Messages.JoinGame;



public class Main{
    public static void main(String [] args) throws Exception{
        AgentInfo agentInfo = new AgentInfo((short) 1001, AgentInfo.PossibleAgentType.BrilliantStudent);
        agentInfo.setFirstName("Joe");
        agentInfo.setLastName("Jones");
        agentInfo.setANumber("A00001");
        agentInfo.setAgentStatus(AgentInfo.PossibleAgentStatus.InGame);
        
        JoinGame jg = new JoinGame((short) 10, agentInfo);
        ByteList bytes = new ByteList();
        
        jg.Encode(bytes);
        byte[] b = bytes.GetBytes(bytes.getLength());
        for(int i =0; i<b.length; ++i)
            System.out.println(b[i]);
    }
}