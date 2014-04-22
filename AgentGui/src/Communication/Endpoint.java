package Communication;

import Common.EndPoint;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA. User: Greg Daniels A00798340 Date: 2/18/14 Time:
 * 1:16 PM
 */
public class Endpoint {

    private int port;
    private InetAddress address;

    public Endpoint(int port, InetAddress address) {
        this.port = port;
        this.address = address;
    }

    public Endpoint(EndPoint communicationEndPoint) {
        this.port = communicationEndPoint.GetIPEndPoint().getPort();
        this.address = toInetAddress(communicationEndPoint.getAddress());
    }

    InetAddress toInetAddress(int addr) {
        byte[] bytes = BigInteger.valueOf(addr).toByteArray();
        byte[] t = new byte[]{bytes[3],bytes[2], bytes[1],bytes[0]};
        try {
            return InetAddress.getByAddress(t);
        } catch (UnknownHostException ex) {
            return null;
        }
    }

//    private byte[] toIPByteArray(int addr){
//        byte[] toReturn =  new byte[]{(byte)addr,(byte)(addr>>>8),(byte)(addr>>>16),(byte)(addr>>>24)};
//        return toReturn;
//    }
//    private InetAddress toInetAddress(int addr){
//    try {
//        return InetAddress.getByAddress(toIPByteArray(addr));
//    } catch (UnknownHostException e) {
//        //should never happen
//        return null;
//    }
//}
public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    @Override
        public String toString() {
        return address + ":" + port;
    }
}
