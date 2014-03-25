/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Messages;

import Common.ByteList;
import Common.DistributableObject;
import junit.framework.TestCase;

/**
 *
 * @author gregor
 */
public class AckNakTest extends TestCase {
    
    public AckNakTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of MessageTypeId method, of class AckNak.
     */
    public void testMessageTypeId() {
        System.out.println("MessageTypeId");
        AckNak instance = new AckNak();
        Message.MESSAGE_CLASS_IDS expResult = null;
        Message.MESSAGE_CLASS_IDS result = instance.MessageTypeId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Create method, of class AckNak.
     */
    public void testCreate() throws Exception {
        System.out.println("Create");
        ByteList messageBytes = null;
        AckNak expResult = null;
        AckNak result = AckNak.Create(messageBytes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Encode method, of class AckNak.
     */
    public void testEncode() throws Exception {
        System.out.println("Encode");
        ByteList bytes = null;
        AckNak instance = new AckNak();
        instance.Encode(bytes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Decode method, of class AckNak.
     */
    public void testDecode() throws Exception {
        System.out.println("Decode");
        ByteList bytes = null;
        AckNak instance = new AckNak();
        instance.Decode(bytes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIntResult method, of class AckNak.
     */
    public void testGetIntResult() {
        System.out.println("getIntResult");
        AckNak instance = new AckNak();
        int expResult = 0;
        int result = instance.getIntResult();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIntResult method, of class AckNak.
     */
    public void testSetIntResult() {
        System.out.println("setIntResult");
        int intResult = 0;
        AckNak instance = new AckNak();
        instance.setIntResult(intResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObjResult method, of class AckNak.
     */
    public void testGetObjResult() {
        System.out.println("getObjResult");
        AckNak instance = new AckNak();
        DistributableObject expResult = null;
        DistributableObject result = instance.getObjResult();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setObjResult method, of class AckNak.
     */
    public void testSetObjResult() {
        System.out.println("setObjResult");
        DistributableObject objResult = null;
        AckNak instance = new AckNak();
        instance.setObjResult(objResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessage method, of class AckNak.
     */
    public void testGetMessage() {
        System.out.println("getMessage");
        AckNak instance = new AckNak();
        String expResult = "";
        String result = instance.getMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMessage method, of class AckNak.
     */
    public void testSetMessage() {
        System.out.println("setMessage");
        String message = "";
        AckNak instance = new AckNak();
        instance.setMessage(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMinimumEncodingLength method, of class AckNak.
     */
    public void testGetMinimumEncodingLength() {
        System.out.println("getMinimumEncodingLength");
        int expResult = 0;
        int result = AckNak.getMinimumEncodingLength();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassId method, of class AckNak.
     */
    public void testGetClassId() {
        System.out.println("getClassId");
        AckNak instance = new AckNak();
        short expResult = 0;
        short result = instance.getClassId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class AckNak.
     */
    public void testCompareTo() {
        System.out.println("compareTo");
        Object o = null;
        AckNak instance = new AckNak();
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
