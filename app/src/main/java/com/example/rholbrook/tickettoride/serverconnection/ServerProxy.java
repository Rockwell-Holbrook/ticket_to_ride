package com.example.rholbrook.tickettoride.serverconnection;

import com.example.shared.interfaces.IServer;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

public class ServerProxy implements IServer {
    private static ServerProxy instance;
    private SocketClientCommunicator socketClientCommunicator;

    public ServerProxy() { }

    public static synchronized ServerProxy getInstance() {
        if (instance == null) {
            instance = new ServerProxy();
        }
        return instance;
    }

    public void connectToManagementSocket(String username) {
        try {
            if (socketClientCommunicator != null) {
                socketClientCommunicator.close();
            }
            socketClientCommunicator = new SocketClientCommunicator(new URI("ws://localhost:8990/management?user=" + username));
            socketClientCommunicator.connect();
        } catch (URISyntaxException e) {

        }
    }

    public void connectToGameSocket(String gameId, String username) {
        try {
            if (socketClientCommunicator != null) {
                socketClientCommunicator.close();
            }
            socketClientCommunicator = new SocketClientCommunicator(new URI("ws://localhost:8990/game/" + gameId + "?user=" + username));
            socketClientCommunicator.connect();
        } catch (URISyntaxException e) {

        }
    }

    @Override
    public void createGame(String username) {

    }

    @Override
    public void joinGame(String username, String gameId) {

    }

    @Override
    public void startGame(String gameId) {

    }

    @Override
    public void sendChat(String username, String gameId, String message) {

    }
}