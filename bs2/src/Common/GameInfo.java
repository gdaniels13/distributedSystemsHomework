package Common;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.activation.CommandInfo;
import javax.jws.soap.SOAPBinding.ParameterStyle;


public class GameInfo extends ComponentInfo
{
	  public enum GameStatus { 
		  NOT_INITIAlIZED(0),
		  AVAILABLE(1),
		  RUNNING(2), 
		  COMPLETED(3), 
		  DEAD(4);
		  
	  private int value;
	  int getValue()
	  {
		  return value;
	  }
	  GameStatus(int value)
	  {
		  this.value = value; 
	  }
	  
	  public static GameStatus getByValue(int i )
      {
		  GameStatus temp = null;
      	  for(GameStatus dt : GameStatus.values()) 
      	  {	
              if(dt.value == i) 
                  temp = dt;
      	  }
      	  return temp;
      }
	  };
	  
	  public short Id;
	  public String Label;
	  public EndPoint CommmunicationEndPoint;
	  public GameStatus Status;
	  public Date AliveTimestamp;
	  public short NextAgentId;
	  public short MaxAgentId;
	  
	  
	  
	  public GameInfo() 
	  { 
		  AliveTimestamp = new Date();
	  }
	  
	  public GameInfo(short id, String label, EndPoint ep) //, GameStatus status)
      {
          Id = id;
          Label = label;
          CommmunicationEndPoint = ep;
          AliveTimestamp = new Date();
      }
	  
	  public GameInfo(short id, String label, EndPoint ep, String status) 
	  {
          this(id, label, ep);
          short temp = 0;
          Short.parseShort(status, temp);
          Status = GameStatus.getByValue(temp);
	 }

	  public GameInfo(short id, String label, EndPoint ep, GameStatus...param) 
      {
		  this(id, label, ep);
		  if (param.length ==1) 
			  Status = param[0];
		  else
			  Status = GameStatus.NOT_INITIAlIZED;
      }
	  
	  public short getId() {
		return Id;
	}
	public void setId(short id) {
		Id = id;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public EndPoint getCommunicationEndPoint() {
		return CommmunicationEndPoint;
	}
	public void setCommmunicationEndPoint(EndPoint commmunicationEndPoint) {
		CommmunicationEndPoint = commmunicationEndPoint;
	}
	public GameStatus getStatus() {
		return Status;
	}
	public void setStatus(GameStatus status) {
		Status = status;
	}
	public Date getAliveTimestamp() {
		return AliveTimestamp;
	}
	public void setAliveTimestamp(Date aliveTimestamp) {
		AliveTimestamp = aliveTimestamp;
	}
	public short getNextAgentId() {
		return NextAgentId;
	}
	public void setNextAgentId(short nextAgentId) {
		NextAgentId = nextAgentId;
	}
	public short getMaxAgentId() {
		return MaxAgentId;
	}
	public void setMaxAgentId(short maxAgentId) {
		MaxAgentId = maxAgentId;
	}
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date);
		SimpleDateFormat sdf = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		System.out.println(sdf.format(date));
	}	  
}
