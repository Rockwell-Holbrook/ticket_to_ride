package com.example.rholbrook.tickettoride.serverconnection;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

public class ServerProxy {
    private static ServerProxy instance;

    public ServerProxy() { }

    public static synchronized ServerProxy getInstance() {
        if (instance == null) {
            instance = new ServerProxy();
        }
        return instance;
    }

    public void connectToManagementSocket(String username) {
        try {
            WebSocketClient socketClientCommunicator = new SocketClientCommunicator(new URI("ws://localhost:8990/management?user=" + username)) {
                @Override
                public void onMessage( String message ) {

                }

                @Override
                public void onOpen( ServerHandshake handshake ) {

                }

                @Override
                public void onClose( int code, String reason, boolean remote ) {

                }

                @Override
                public void onError( Exception ex ) {

                }
            };

            socketClientCommunicator.connect();
        } catch (URISyntaxException e) {

        }
    }

    public void connectToGameSocket(UUID gameId) {
        try {
            WebSocketClient socketClientCommunicator = new SocketClientCommunicator(new URI("ws://localhost:8990/game/" + gameId)) {
                @Override
                public void onMessage( String message ) {

                }

                @Override
                public void onOpen( ServerHandshake handshake ) {

                }

                @Override
                public void onClose( int code, String reason, boolean remote ) {

                }

                @Override
                public void onError( Exception ex ) {

                }
            };
            socketClientCommunicator.connect();;
        } catch (URISyntaxException e) {

        }
    }
}
