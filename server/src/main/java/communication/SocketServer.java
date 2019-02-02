package communication;

import com.example.shared.commands.CommandToClient;
import com.example.shared.commands.CommandToServer;
import com.google.gson.Gson;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

/**
 * Socket server that sends and receives commands
 * TODO Implement methods for command pattern
 */
public class SocketServer extends WebSocketServer {
    private static Gson gson = new Gson();
    private Set<WebSocket> managementConnections;

    public SocketServer( int port ) {
        super( new InetSocketAddress( port ) );
        managementConnections = new HashSet<>();
    }

    public SocketServer( InetSocketAddress address ) {
        super( address );
    }

    @Override
    public void onOpen( WebSocket conn, ClientHandshake handshake ) {
        System.out.println( conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!" );
        String resourceDesc = handshake.getResourceDescriptor();
        String path = getPath(resourceDesc);

        if (path.equals("/management")) {
            String username = getUsername(resourceDesc);
            conn.setAttachment(username);
            managementConnections.add(conn);
        }

    }

    @Override
    public void onClose( WebSocket conn, int code, String reason, boolean remote ) {

        System.out.println( conn + " has left the room!" );
    }

    @Override
    public void onMessage( WebSocket conn, String message ) {
        CommandToServer cmd = new CommandToServer(message);
        cmd.execute();
    }


    public static void main( String[] args ) throws InterruptedException , IOException {
        int port = 8887; // 843 flash policy port
        try {
            port = Integer.parseInt( args[ 0 ] );
        } catch ( Exception ex ) {
        }
        SocketServer s = new SocketServer( port );
        s.start();
        System.out.println( "ChatServer started on port: " + s.getPort() );

        BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
        while ( true ) {
            String in = sysin.readLine();
            s.broadcast( in );
            if( in.equals( "exit" ) ) {
                s.stop(1000);
                break;
            }
        }
    }
    @Override
    public void onError( WebSocket conn, Exception ex ) {
        ex.printStackTrace();
        if( conn != null ) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }

    /**
     * Sends command to all connected to management server "/management"
     * @param cmd CommandToClient to be run on all clients connected to management
     */
    public void broadcastToManagement(CommandToClient cmd){
        String serializedCmd = gson.toJson(cmd);
        broadcast(serializedCmd, managementConnections);
    }

    /**
     * Sends command to all connected to game of specific gameId
     * @param cmd CommandToClient to be run on all clients connected to game at game_id
     * @param gameId Id of game in question
     */
    public void broadcastToGame(CommandToClient cmd, String gameId){

    }

    private String getPath(String resDesc) {
        return resDesc.split("\\?")[0];
    }

    private String getUsername(String resDesc) {
        return resDesc.split("user=")[1];
    }

}
