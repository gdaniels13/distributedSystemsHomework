package CommonTester;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import junit.framework.Assert;

import org.junit.Test;
import org.omg.CORBA.portable.ApplicationException;

import Common.ByteList;
import Common.Tick;

public class TickTest {

	@Test
	public void TickTester_CheckConstructors()
    {
        Tick tick1 = new Tick(10, 20L);
        System.out.println(tick1.getLogicalClock());
        assertEquals(10, tick1.getLogicalClock());
        assertEquals(20, tick1.getHashCode());

        tick1 = new Tick();
        Tick tick2 = new Tick();
        System.out.println("tick1.getLogicalClock() = " + tick1.getLogicalClock());
        System.out.println("tick2.getLogicalClock() = " + tick2.getLogicalClock());
        
        assertEquals(tick1.getLogicalClock() + 1 , tick2.getLogicalClock());
        //assertThat(tick1.getHashCode(), !(equals(Long.valueOf(tick2.getHashCode()))));
        //assertNotEquals(tick1.getHashCode(), tick2.getHashCode());
    }

	@Test
    public void TickTester_CheckProperties()
    {
        Tick tick1 = new Tick();
        tick1.setLogicalClock(100);
        assertEquals(100, tick1.getLogicalClock());
        //assertNotEquals(100, tick1.getHashCode());

        Tick tick2 = new Tick();
        tick2.setLogicalClock(tick1.getLogicalClock() + 1);
        assertEquals(101, tick2.getLogicalClock());
        //assertNotEquals(tick1.getHashCode(), tick2.getHashCode());
    }

	@Test
    public void TickTester_CheckEncodeDecode() throws UnknownHostException, Exception
    {
        Tick tick1 = new Tick();
        tick1.setLogicalClock(100);
        ByteList bytes = new ByteList();
        tick1.Encode(bytes);
        Tick tick2 = Tick.Create(bytes);
        assertEquals(tick1.getLogicalClock(), tick2.getLogicalClock());
        assertEquals(tick1.getHashCode(), tick2.getHashCode());

        tick1.setLogicalClock(0);
        bytes = new ByteList();
        tick1.Encode(bytes);
        tick2 = Tick.Create(bytes);
        assertEquals(tick1.getLogicalClock(), tick2.getLogicalClock());
        assertEquals(tick1.getHashCode(), tick2.getHashCode());

        tick1.setLogicalClock(Integer.MAX_VALUE);
        bytes = new ByteList();
        tick1.Encode(bytes);
        tick2 = Tick.Create(bytes);
        assertEquals(tick1.getLogicalClock(), tick2.getLogicalClock());
        assertEquals(tick1.getHashCode(), tick2.getHashCode());

        bytes.Clear();
        tick1.Encode(bytes);
        bytes.GetByte();            // Read one byte, which will throw the length off
        try
        {
        	tick2 = Tick.Create(bytes);
        	fail("Expected an exception to be thrown");
        }
        catch (ApplicationException e) {}

        bytes.Clear();
        tick1.Encode(bytes);
        bytes.Add((byte)100);       // Add a byte
        bytes.GetByte();            // Read one byte, which will make the ID wrong
        try
        {
        	tick2 = Tick.Create(bytes);
        	fail("Expected an exception to be thrown");
        }
        catch (ApplicationException e) {}

    }

	@Test
    public void TickTester_CheckOtherMethods()
    {
        Tick tick1 = new Tick(10, 10L);
        assertEquals(10, tick1.getLogicalClock());
        assertEquals(10, tick1.getHashCode());
        assertFalse(tick1.IsValid());

        for (int i = 0; i < 100; i++)
        {
            tick1 = new Tick();
            assertTrue(tick1.IsValid());
        }

    }


}
