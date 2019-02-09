package com.example.rholbrook.tickettoride.serverconnection;

import android.util.Log;
import com.example.rholbrook.tickettoride.main.MainActivityModel;
import com.example.shared.commands.Command;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;

public class SocketClientCommunicator extends WebSocketClient {
    private ClientFacade facadeCallback;

    public SocketClientCommunicator(URI uri) {
        super(uri);
        facadeCallback = ClientFacade.getInstance();
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println(handshakedata.getHttpStatusMessage());
        System.out.println(handshakedata.getHttpStatus());
    }

    @Override
    public void onMessage(String message) {
        String cutMessage = message.substring(0,5);
        System.out.println(message);
        if (message.equals("Management Successful")){
            //Unnecessary Response
        } else if (cutMessage.equals("Came:")) {
            String gameId = message.substring(5);
            ClientFacade.getInstance().joinedGame(gameId);
        } else {
            Command cmd = new Command(message);
            cmd.execute(facadeCallback);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected from Websocket: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

}
