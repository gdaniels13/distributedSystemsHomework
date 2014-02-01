package UnitTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;
import java.awt.*;
import Messages.*;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Greg Daniels A00798340
 * Date: 1/31/14after what does it do
 * Time: 1:16 PM
 */

public class MessageTypeTest
{
	@Test
	public void testToString() throws Exception
	{
		assertEquals(MessageType.ERROR.toString(),"ERROR");
		assertEquals(MessageType.UNKNOWN.toString(),"UNKNOWN");
	}

	@Test
	public void testGetTypeFromString() throws Exception
	{
		assertEquals(MessageType.getTypeFromString("ERROR") ,MessageType.ERROR);
		assertEquals(MessageType.getTypeFromString("errors"),MessageType.UNKNOWN);
	}
}
