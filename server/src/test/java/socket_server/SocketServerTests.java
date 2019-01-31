package socket_server;

import org.junit.Test;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerEndpointConfig;
import java.util.HashSet;
import java.util.Set;

public class SocketServerTests {
    @Test
    public void canConnect(){
        HashSet<Class<? extends Endpoint>> ourSet = new HashSet<Class<? extends Endpoint>>();
        ourSet.add(SocketServer.class);
        CustomServerAppConfigProvider provider = new CustomServerAppConfigProvider("/serv");
        Set<ServerEndpointConfig> vals = provider.getEndpointConfigs(ourSet);
    }
}
