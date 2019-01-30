package socket_server;

import org.junit.Test;

public class SocketServerTests {
    @Test
    void canConnect(){
        SocketServer ss = new SocketServer(8990);
    }
}
