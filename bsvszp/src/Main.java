
import Common.AgentInfo;
import Common.ByteList;
import Messages.JoinGame;



public class Main{
    public static void main2(String [] args) throws Exception{
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
    
    public static void main(String[] args){
        System.out.println(angle(12,15));
        
    }
    
    
    public static double angle(int hours, int minutes){
        if (hours == 12)
            hours = 0;
        return Math.abs((float) (30*hours - 5.5*minutes));
        
    }
}