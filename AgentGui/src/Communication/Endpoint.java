package Communication;

import java.net.InetAddress;

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
