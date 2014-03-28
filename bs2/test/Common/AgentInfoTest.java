package CommonTester;

import static org.junit.Assert.*;
import java.net.InetSocketAddress;
//import junit.framework.Assert;
import org.junit.Test;
import Common.AgentInfo;
import Common.EndPoint;
import Common.StateChange;

public class AgentInfoTest 
{
	private StateChange recentStateChange = null;

	@Test
	public void AgentInfo_CheckConstructors() throws Exception {
		 AgentInfo info = new AgentInfo();
		 assertEquals(0, info.getId());
		 assertNull(info.getANumber());
		 assertNull(info.getFirstName());
		 assertNull(info.getLastName());
         assertEquals((Double)0.0, info.getStrength());
         assertEquals((Double)0.0, info.getSpeed());
         assertNull(info.getLocation());
         assertNull(info.getCommmunicationEndPoint());

         info = new AgentInfo((short) 10, AgentInfo.PossibleAgentType.ExcuseGenerator);
         info.setStrength(20.0);
         assertEquals(10, info.getId());
         assertNull(info.getANumber());
         assertNull(info.getFirstName());
         assertNull(info.getLastName());
         assertEquals((Double)20.0, info.getStrength());
         System.out.println("=== info.getSpeed() === " + info.getSpeed());
         assertEquals((Double)0.0, info.getSpeed());
         assertNull(info.getLocation());
         assertNull(info.getCommmunicationEndPoint());
         assertEquals(AgentInfo.PossibleAgentType.ExcuseGenerator, info.getAgentType());

         InetSocketAddress inetSocketAddress = new InetSocketAddress("129.123.7.24", 1345);
         EndPoint ep = new EndPoint(inetSocketAddress);
         info = new AgentInfo((short) 20, AgentInfo.PossibleAgentType.WhiningSpinner, ep);
         assertEquals(20, info.getId());
         assertNull(info.getANumber());
         assertNull(info.getFirstName());
         assertNull(info.getLastName());
         System.out.println("=== info.getStrength() === " + info.getStrength());
         assertEquals((Double)0.0, info.getStrength());
         assertEquals((Double)0.0, info.getSpeed());
         assertNull(info.getLocation());
         assertSame(ep, info.getCommmunicationEndPoint());
         assertEquals(AgentInfo.PossibleAgentType.WhiningSpinner, info.getAgentType());
	}

}
