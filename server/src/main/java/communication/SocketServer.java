package communication;

import com.example.shared.commands.Command;
import com.google.gson.Gson;
import game.GameManager;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Socket server that sends and receives commands
 */
public class SocketServer extends WebSocketServer {
    private static final SocketServer instance = new SocketServer(7777);
    private static Gson gson = new Gson();
    private Set<WebSocket> managementConnections;
    private Map<String, Set<WebSocket>> gameConnections;

    public SocketServer(int port) {
        super(new InetSocketAddress(port));
        managementConnections = new HashSet<>();
        gameConnections = new HashMap<>();
    }

    public static SocketServer getInstance() {
        return instance;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");
        String resourceDesc = handshake.getResourceDescriptor();
        String path = getPath(resourceDesc);

        String username = getUsername(resourceDesc);
        conn.setAttachment(username);

        if (path.equals("/management")) {
            managementConnections.add(conn);
            conn.send("Management Successful");
        } else if (path.contains("/game")) {
            String gameId = getGameId(path.substring(5));
            gameConnections.get(gameId).add(conn);
            conn.send("Game:" + gameId);
        }



    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        String path = getPath(conn.getResourceDescriptor());
        System.out.println("Closed conn with " + conn.getResourceDescriptor());
        if (path.equals("/management")) {
            managementConnections.remove(conn);
            System.out.println(conn.getAttachment() + " has left management!");
        } else if (path.contains("/game")) {
            String gameId = getGameId(path.substring(5));
            System.out.println(gameConnections.toString());
            System.out.println(conn.getAttachment() + " has left game: " + gameId);
            gameConnections.get(gameId).remove(conn);

            // Get rid of inactive games
            if (gameConnections.get(gameId).size() == 0){
                System.out.println(gameId + " is inactive. Deleting it.");
                gameConnections.remove(gameId);
                GameManager.getInstance().removeGame(gameId);
            }
        }

    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Got command: " + message);
        Command cmd = new Command(message);
        cmd.execute(ServerFacade.getInstance());
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("Socket Server started!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }

    /**
     * Sends command to all connected to management server "/management"
     *
     * @param cmd CommandToClient to be run on all clients connected to management
     */
    public void broadcastToManagement(Command cmd) {

        String serializedCmd = gson.toJson(cmd);
        System.out.println("Sending cmd to management: " + serializedCmd);
        broadcast(serializedCmd, managementConnections);
    }

    /**
     * Sends command to all connected to game of specific gameId
     *
     * @param cmd    CommandToClient to be run on all clients connected to game at game_id
     * @param gameId Id of game in question
     */
    public void broadcastToGame(Command cmd, String gameId) {
        String serializedCmd = gson.toJson(cmd);
        System.out.println("Sending cmd to game at " + gameId + ": " + serializedCmd);
        broadcast(serializedCmd, gameConnections.get(gameId));
    }

    /**
     * Adds a new game set for users to connect to
     * @param gameId Id of new game
     */
    public void addGame(String gameId) {
        gameConnections.put(gameId, new HashSet<WebSocket>());
    }

    /**
     * Sends a command to a specific user who is connected to /management
     * If user doesn't exist no command will be sent to anywhere
     * @param cmd Command to run
     * @param username Name of connected user
     */
    public void sendToUser(Command cmd, String username) {
        for(WebSocket ws : managementConnections) {
            if(ws.getAttachment().equals(username)){
                String serialized = gson.toJson(cmd);
                System.out.println("Sending cmd to " + username + ": " + serialized);
                ws.send(serialized);
            }
        }
    }

    /**
     * Sends command to a user who is in a game at gameId
     * If user doesn't exist no command will be sent to anywhere
     * @param cmd Command to send
     * @param username Name of user to send omd to
     * @param gameId Id of the game the user is in
     */
    public void sendToUser(Command cmd, String username, String gameId){
        for(WebSocket ws : gameConnections.get(gameId)) {
            if(ws.getAttachment().equals(username)){
                String serialized = gson.toJson(cmd);
                System.out.println("Sending cmd to " + username + ": " + serialized);
                ws.send(serialized);
            }
        }
    }

    private String getPath(String resDesc) {
        return resDesc.split("\\?")[0];
    }

    private String getGameId(String path) {
        return path.split("/")[1];
    }

    private String getUsername(String resDesc) {
        return resDesc.split("user=")[1];
    }

}
