package Common;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

import org.omg.CORBA.portable.ApplicationException;

public class AgentList extends DistributableObject implements Iterable<AgentInfo>
{
	private static short ClassId; 
   	private ArrayList<AgentInfo> agents = new ArrayList<AgentInfo>();
    private Object myLock = new Object();
    private static int MinimumEncodingLength;
   
    public short getClassId() {
		ClassId = (short) DISTRIBUTABLE_CLASS_IDS.AgentList.getValue();
		return ClassId;
	}

    public static int getMinimumEncodingLength() {
    	MinimumEncodingLength = 4              // Object header
                				+ 2;           // Components
    	return MinimumEncodingLength;
	}

    public AgentList() { }
  
    //new 
    public static AgentList Create(ByteList bytes) throws ApplicationException, Exception
    {
        AgentList result = new AgentList();
        result.Decode(bytes);
        return result;
    }

    public void Dispose()
    {
        Clear();
    }
	
    public int Count() 
    {
    	int result = 0;
    	synchronized (myLock) {result = agents.size(); }
        return result;
        
    }

    public AgentInfo getAgentInfo(int index)
    {
        AgentInfo result = null;
        if (index >= 0 && index < agents.size())
                result = agents.get(index);
            return result;
    }

    public void Add(AgentInfo agentInfo)
    {
    	synchronized (myLock) 
    	{
    		agents.add(agentInfo);
    	}
    }

    public void Remove(AgentInfo agentInfo)
    {
        if (agentInfo != null)
            Remove(agentInfo.getId());
    }

    public  void Remove(short agentId)
    {
    	synchronized (myLock) 
    	{
    		for (AgentInfo agent : agents)
    		{	if (agent.getId() == agentId)
    			{
    				agents.remove(agent);
    				break;
    			}
    		}
    	}
    }

    public void Clear()
    {
    	synchronized (myLock)
    	{
    		agents.clear();
    	}
        
    }
		
	@Override
	public Iterator<AgentInfo> iterator(){
		return agents.iterator();
	}
	
	@Override
	public void Encode(ByteList bytes) throws UnknownHostException, Exception {
		 bytes.Add(getClassId());                             // Write out the class type
		 bytes.update();
         short lengthPos = bytes.getCurrentWritePosition();   // Get the current write position, so we
         bytes.update();                                  // can write the length here later

         bytes.Add((short) 0);                           // Write out a place holder for the length
         bytes.update();
         synchronized (myLock)
         {
             bytes.Add((short)(agents.size()));
             bytes.update();
             for (AgentInfo component : agents)
             {
            	 bytes.Add(component);
            	 bytes.update();
             }
         }
         short  length = (short)(bytes.getCurrentWritePosition() - lengthPos - 2);
         bytes.WriteInt16To(lengthPos, length);          // Write out the length of this object    
		
	}

	@Override
	protected void Decode(ByteList bytes) throws ApplicationException, Exception {
		 if (bytes == null || bytes.getRemainingToRead() < getMinimumEncodingLength())
             throw new ApplicationException("Invalid byte array", null);
         else if (bytes.PeekInt16() != getClassId())
             throw new ApplicationException("Invalid class id", null);
         else
         {
             short objType = bytes.GetInt16();
            
             short objLength = bytes.GetInt16();
           
             bytes.SetNewReadLimit(objLength);
             bytes.update();
             synchronized (myLock)
             {
                 Clear();
              
                 short count = bytes.GetInt16();
                
                 for (int i = 0; i < count; i++)
                     agents.add((AgentInfo)bytes.GetDistributableObject());
             }

             bytes.RestorePreviosReadLimit();
         }
		
	}

}
