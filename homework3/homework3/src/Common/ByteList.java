package Common;
import java.io.NotActiveException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.awt.Window.Type;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.omg.CORBA.portable.ApplicationException;

public class ByteList
{
	private static final Logger log = Logger.getLogger(ByteList.class.getName()); 
	private short _addCurrentSection = (short) 0;
	private short _addCurrentOffset = (short)0;
	private short _readCurrentPosition = (short)0;
	private Stack<Short> _readLimitStack = new Stack<Short>();
	private int RemainingToRead; 
	private short CurrentWritePosition;
    private static final int SECTION_SIZE = 1024;
    private ArrayList<byte[]> _sections = new ArrayList<byte[]>();
    
    public ArrayList<byte[]> get_sections() {
		return _sections;
	}

    public ByteList()
    {
       _sections.add(new byte[SECTION_SIZE]);
    }

    public ByteList(Object...items) throws NotActiveException, UnknownHostException, Exception
    {
        _sections.add(new byte[SECTION_SIZE]);
        AddObjects(items);
    }
       
    public void WriteInt16To(int writePosition, short value) throws UnknownHostException
    {
        if (writePosition >= 0 && writePosition < getLength() - 2)
        {
            int sectionIdx = writePosition / SECTION_SIZE;
            int sectionOffset = writePosition - sectionIdx * SECTION_SIZE;
                                   
            byte[] bytes = BitConverter.getBytes(value);
            System.arraycopy(bytes, 0,                               // Source
            				_sections.get(sectionIdx), sectionOffset,   				 // Destination
            				bytes.length );                    	     // Length Short.SIZE
           
        }
    }
    
    public void CopyFromBytes(byte[] bytes)
    {
        Clear();
        Add(bytes);
    }

    public void AddObjects(Object... items) throws NotActiveException, UnknownHostException, Exception
    {
        for (Object item :items)
            AddObject(item);
    }

    public void AddObject(Object item) throws Exception
    {
        if (item != null)
        {
        	if (item instanceof ByteList)
                Add((ByteList)item);
            else if (item instanceof Boolean)
                Add((boolean)item);
            else if (item instanceof Byte)
                Add((byte)item);
            else if (item instanceof Character)
                Add((char)item);
            else if (item instanceof Short)
                Add((short)item);
            else if (item instanceof Integer)
                Add((Integer)item);
            else if (item instanceof Long)
                Add((long)item);
            else if (item instanceof Double)
                Add((double)item);
            else if (item instanceof Float)
                Add((float)item);
            else if (item instanceof String) 
            	Add((String)item);
            else if (item instanceof byte[])
                Add((byte[])item);
            else if (item instanceof DistributableObject)
            	Add((DistributableObject)item);
            else
               throw new NotActiveException();
        }
        else
        	Add((DistributableObject)item);
    }

    public void Add(ByteList value)
    {
        if (value != null)
        {
            for (int i = 0; i <= value._addCurrentSection; i++)
            {
                if (i < value._addCurrentSection)
                   	Add(value._sections.get(i), 0, SECTION_SIZE);
                else
                	Add(value._sections.get(i), 0, value._addCurrentOffset);
            }
        }
    }

    public void Add(byte value)
    {
        Add(new byte[] { value });
    }

    public void Add(Boolean value)
    {
        if (value)
            Add(new byte[] { 1 });
        else
            Add(new byte[] { 0 });
    }

    public void Add(char value)
    {
        Add(BitConverter.getBytes(value));
    }
       
    public void Add(short value)
    {
    	Add(BitConverter.getBytes(value));
    }

    public void Add(int value)
    {
        Add(BitConverter.getBytes(value));
    }

    public void Add(long value)
    {
        Add(BitConverter.getBytes(value));
    }

    public void Add(double value)
    {
    	byte[] bytes = BitConverter.getBytes(value);
    	 if (BitConverter.IsLittleEndian)
         	ArrayUtils.reverse(bytes);
        Add(bytes);
    }

    public void Add(float value)
    {
    	byte[] bytes = BitConverter.getBytes(value);
    	if (BitConverter.IsLittleEndian)
        	ArrayUtils.reverse(bytes);
    	Add(bytes);
      }

    public void Add(String value) throws UnsupportedEncodingException, ApplicationException 
    {
        if (value != null)
        {        	
        	byte[] bytes = value.getBytes(Charset.forName("UTF-16"));
           	Add((short) bytes.length);
            Add(bytes);
        }
        else
            Add((short) 0);
    }

    public void Add(byte[] value)
    {
        if (value != null)
            Add(value, 0, value.length);
    }

    public void Add(byte[] value, int offset, int length)
    {
        if (value != null)
        {
            int additionalBytesNeeded = _addCurrentOffset + getLength() - SECTION_SIZE;
            Grow(additionalBytesNeeded);

            int cnt = 0;
            while (cnt < length)
            {
                short blockSize = (short) Math.min(SECTION_SIZE - _addCurrentOffset, length - cnt);
                System.arraycopy(value, offset + cnt,
                				_sections.get(_addCurrentSection), _addCurrentOffset,
                                blockSize);

                cnt += blockSize;
                _addCurrentOffset += blockSize;
                if (_addCurrentOffset == SECTION_SIZE)
                {
                    _addCurrentOffset = 0;
                    _addCurrentSection++;
                }
            }
        }
    }

    public void Add(DistributableObject obj) throws UnknownHostException, Exception
    {
        if (obj == null)
            Add(false);
        else
        {
            Add(true);
            obj.Encode(this);
        }
    }
    
    public void ResetRead()
    {
        _readCurrentPosition = (short)0;
    }

    public ByteList GetByteList(int length) throws ApplicationException
    {
        ByteList result = new ByteList();
        result.CopyFromBytes(GetBytes(length));
        return result;
    }

    public byte GetByte() throws ApplicationException
    {
        return GetBytes(1)[0];
    }

    public Boolean GetBool() throws ApplicationException
    {
        return (GetBytes(1)[0] == 0) ? false : true;
    }

    public char GetChar() throws Exception
    {
        return BitConverter.toChar(GetBytes(2), 0);
    }

    public short GetInt16() throws Exception
    {
        return BitConverter.toInt16(GetBytes(2), 0);
    }

    public short PeekInt16() throws Exception
    {
       	short result = BitConverter.toInt16(GetBytes(2), 0);
    	_readCurrentPosition = (short) (_readCurrentPosition - 2);   // Move the current read position back two bytes
        return result;
    }

    public Integer GetInt32() throws Exception
    {
        return BitConverter.toInt32(GetBytes(4), 0);
    }

    public long GetInt64() throws Exception
    {
        return BitConverter.toInt64(GetBytes(8), 0);
    }

    public double GetDouble() throws Exception
    {
    	byte[] bytes = GetBytes(8);
        if (BitConverter.IsLittleEndian)
        	ArrayUtils.reverse(bytes);
    	return BitConverter.toDouble(bytes, 0);
    }

    public float GetFloat() throws Exception
    {
        byte[] bytes = GetBytes(4);
        if (BitConverter.IsLittleEndian)
            ArrayUtils.reverse(bytes);
        return BitConverter.toSingle(bytes, 0);
    }

    public String GetString() throws Exception
    {
        String result = "";
        short length = GetInt16();
        if (length > 0)
        	result = new String(GetBytes(length), "UTF-16");
        			//org.apache.commons.codec.binary.StringUtils.newStringUtf16(GetBytes(length));
        return result;
    }
    
    public int getRemainingToRead() 
    {
    	int tmpMax = (_readLimitStack.size() == 0) ? this.getLength() : _readLimitStack.peek();
    	RemainingToRead =  (_readCurrentPosition >= tmpMax) ? 0 : tmpMax - _readCurrentPosition;
    	return RemainingToRead;
	}

    public byte[] GetBytes(int length) throws ApplicationException
    {
        if ((_readLimitStack.size() > 0) && ((_readCurrentPosition + length) > _readLimitStack.peek()))
            throw new ApplicationException("Attempt to read beyond read limit", null);
        
        byte[] result = new byte[length];
        int bytesRead = 0;

        while (bytesRead < length)
        {
            int sectionIndex = _readCurrentPosition / SECTION_SIZE;
            int sectionOffset = _readCurrentPosition - sectionIndex * SECTION_SIZE;

            int cnt = Math.min(SECTION_SIZE - sectionOffset, length - bytesRead);
            System.arraycopy(_sections.get(sectionIndex), sectionOffset, 
            		result, bytesRead, 
            		cnt);

            sectionOffset = 0;
            _readCurrentPosition = ((short) ((_readCurrentPosition +  (short)cnt)));
            bytesRead += cnt;
        }

        return result;
    }
    
    public DistributableObject GetDistributableObject() throws ApplicationException
    {
        DistributableObject obj = null;
        boolean isPresent= GetBool();
		
        if (isPresent)
			try {
				obj = DistributableObject.Create(this);
			} catch (Exception e) {	}

        return obj;
    }

    public byte[] ToBytes()
    {
        int bytesRead = 0;
        byte[] bytes = new byte[getLength()];

        for (int i = 0; i <= _addCurrentSection; i++)
        {
            int sectionBytes = SECTION_SIZE;
            if (i == _addCurrentSection)
                sectionBytes = _addCurrentOffset;
            System.arraycopy(_sections.get(i), 0, bytes, bytesRead, sectionBytes);
            bytesRead += sectionBytes;
        }

        return bytes;
    }
  
    public void SetNewReadLimit(short length)
    {
    	_readLimitStack.push((short) (_readCurrentPosition + length));
    }

    public void RestorePreviosReadLimit()
    {
        if (_readLimitStack.size() > 0)
            _readLimitStack.pop();
    }

    public void ClearMaxReadPosition()
    {
        _readLimitStack.clear();
    }

    public void Clear()
    {
        _sections.clear();
        _readLimitStack.clear();
        _addCurrentSection = 0;
        _addCurrentOffset = 0;
        _readCurrentPosition = (short) 0;
    }
    
    public byte getByteValue(int index) throws Exception
    {
       	if ((index < 0) || (index >= getLength()))
                throw new IndexOutOfBoundsException();
    	int[] holder = {index};
    	holder[0] = index;
        byte[] section = GetSection(holder);
        return section[index];
    }

    public void setByteValue(byte value, int index[]) throws ApplicationException
    {
    	
    	if (index[0] < 0)
    		throw new IndexOutOfBoundsException();
    	int[] holder = {index[0]};
    	Grow(index[0]);
        byte[] section = GetSection(holder);
        section[index[0]] = value;
    }

    public int getLength()
    {
    	return  _addCurrentSection * SECTION_SIZE + _addCurrentOffset;
    }


    public Boolean IsMore()
    {
        int tmpMax = (_readLimitStack.size() == 0) ? this.getLength() : _readLimitStack.peek();
        return (_readCurrentPosition >= tmpMax) ? false : true;
    }

    public void Log() 
    {
    	log.setLevel(Level.INFO);
    	for (int i = 0; i < this.getLength(); i++)
        {
        	try {
				log.info(Integer.toString(i) + "\t= " + this.getByteValue(i));
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
        }
    }

    // Private Methods
  
    private void Grow(int additionBytesNeeded)
    {
        int sectionIndex = (additionBytesNeeded / SECTION_SIZE) +  1;
        for (int i = _sections.size() - 1; i < sectionIndex; i++)
            _sections.add(new byte[SECTION_SIZE]);
    }

    private byte[] GetSection(int[] container) throws ApplicationException
    {
        int sectionIndex = container[0] / SECTION_SIZE;

        if (sectionIndex >= _sections.size())
            throw new ApplicationException(null, null);

        container[0] -= sectionIndex * SECTION_SIZE;
        return _sections.get(sectionIndex);
    }
       
    static public String GetString(byte[] bytes, int[] container, Boolean isNullTerminated) throws Exception
    {
       	short length = BitConverter.toInt16(bytes, container[0]);
       	container[0] += 2;
        String result = new String(bytes, container[0], length, "UTF-16"); 
        container[0] += length;
        if (isNullTerminated)
        	container[0]++;
        return result;
    }
   
    public short getCurrentWritePosition() 
    {
    	CurrentWritePosition = (short) (_addCurrentSection * SECTION_SIZE + _addCurrentOffset);
    	return CurrentWritePosition;
	}
}