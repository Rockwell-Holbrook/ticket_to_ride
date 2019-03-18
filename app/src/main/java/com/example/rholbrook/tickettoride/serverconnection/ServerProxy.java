package com.example.rholbrook.tickettoride.serverconnection;

import android.util.Log;
import com.example.rholbrook.tickettoride.main.Authentication;
import com.example.shared.commands.Command;
import com.example.shared.interfaces.IServer;
import com.example.shared.model.*;
import com.google.gson.Gson;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ServerProxy implements IServer {
    private final String TAG = "ticket_to_ride";


    private static ServerProxy instance;
    private SocketClientCommunicator socketClientCommunicator;
    private static Gson gson = new Gson();

    public ServerProxy() { }

    public static synchronized ServerProxy getInstance() {
        if (instance == null) {
            instance = new ServerProxy();
        }
        return instance;
    }

    public void connectToManagementSocket(String username) throws URISyntaxException {
        try {
            if (socketClientCommunicator != null) {
                socketClientCommunicator.close();
            }
            String url = "ws://10.0.2.2:7777/management?user=" + username;
            socketClientCommunicator = new SocketClientCommunicator(new URI(url));
            socketClientCommunicator.connect();
        } catch (URISyntaxException e) {
            throw e;
        }
    }

    public void connectToGameSocket(String gameId, String username) throws URISyntaxException {
        try {
            if (socketClientCommunicator != null) {
                socketClientCommunicator.close();
            }
            String url = "ws://10.0.2.2:7777/game/" + gameId + "?user=" + username;
            socketClientCommunicator = new SocketClientCommunicator(new URI(url));
            socketClientCommunicator.connect();
        } catch (URISyntaxException e) {
            throw e;
        }
    }

    @Override
    public void getGameList(String username) {
        String methodNane = "getGameList";
        String[] paramTypes = {String.class.getName()};
        Object[] paramValues = {username};
        socketClientCommunicator.send(gson.toJson(new Command(methodNane, paramTypes, paramValues)));
    }

    @Override
    public void createGame(Player host, int maxPlayers, String gameName) {
        String methodName = "createGame";
        String[] paramTypes = {Player.class.getName(), int.class.getName(), String.class.getName()};
        Object[] paramValues = {host, maxPlayers, gameName};
        socketClientCommunicator.send(gson.toJson(new Command(methodName, paramTypes, paramValues)));
    }

    @Override
    public void joinGame(String gameId, Player player) {
        String methodName = "joinGame";
        String[] paramTypes = {String.class.getName(), Player.class.getName()};
        Object[] paramValues = {gameId, player};
        socketClientCommunicator.send(gson.toJson(new Command(methodName, paramTypes, paramValues)));
    }

    @Override
    public void startGame(String gameId) {
        String methodName = "startGame";
        String[] paramTypes = {String.class.getName()};
        Object[] paramValues = {gameId};
        socketClientCommunicator.send(gson.toJson(new Command(methodName, paramTypes, paramValues)));
    }

    @Override
    public void getPlayerList(String gameId) {
        String methodName = "getPlayerList";
        String[] paramTypes = {String.class.getName()};
        Object[] paramValues = {gameId};
        socketClientCommunicator.send(gson.toJson(new Command(methodName, paramTypes, paramValues)));
    }

    @Override
    public void sendChat(Chat chat, String gameId, boolean gameStarted) {
        String methodName = "sendChat";
        String[] paramTypes = {Chat.class.getName(), String.class.getName(), boolean.class.getName()};
        Object[] paramValues = {chat, gameId, gameStarted};
        socketClientCommunicator.send(gson.toJson(new Command(methodName, paramTypes, paramValues)));
    }

    @Override
    public void getChatHistory(String gameId, String username, boolean gameStarted) {
        String methodName = "getChatHistory";
        String[] paramTypes = {String.class.getName()};
        Object[] paramValues = {gameId};
        socketClientCommunicator.send(gson.toJson(new Command(methodName, paramTypes, paramValues)));
    }

    @Override
    public void getGameHistory(String gameId) {
        String methodName = "getGameHistory";
        String[] paramTypes = {String.class.getName()};
        Object[] paramValues = {gameId};
        socketClientCommunicator.send(gson.toJson(new Command(methodName, paramTypes, paramValues)));
    }

    @Override
    public void readyToInitialize(String gameId, String username) {
        String methodName = "readyToInitialize";
        String[] paramTypes = {String.class.getName(), String.class.getName()};
        Object[] paramValues = {gameId, username};
        socketClientCommunicator.send(gson.toJson(new Command(methodName, paramTypes, paramValues)));
    }

    @Override
    public void initializeComplete(String gameId, String username) {
        String methodName = "initializeComplete";
        String[] paramTypes = {String.class.getName(), String.class.getName()};
        Object[] paramValues = {gameId, username};
        socketClientCommunicator.send(gson.toJson(new Command(methodName, paramTypes, paramValues)));
    }

    @Override
    public void ticketsReturned(String gameId, String username, ArrayList<Ticket> returned) {
        String methodName = "ticketsReturned";
        String[] paramTypes = {String.class.getName(), String.class.getName(), ArrayList.class.getName()};
        Object[] paramValues = {gameId, username, returned};
        socketClientCommunicator.send(gson.toJson(new Command(methodName, paramTypes, paramValues)));
    }

    @Override
    public void turnEnded(String gameID, String username) {
        String methodName = "turnEnded";
        String[] paramTypes = {String.class.getName(), String.class.getName()};
        Object[] paramValues = {gameID, username};
        socketClientCommunicator.send(gson.toJson(new Command(methodName, paramTypes, paramValues)));
    }

    @Override
    public void getCard(String gameId, String username, int index) {
        String methodName = "getCard";
        String[] paramTypes = {String.class.getName(), String.class.getName(), int.class.getName()};
        Object[] paramValues = {gameId, username, index};
        socketClientCommunicator.send(gson.toJson(new Command(methodName, paramTypes, paramValues)));
    }

    @Override
    public void claimRoute(String gameId, String username, int routeId, List<TrainCard> selectedCards) {
        String methodName = "claimRoute";
        String[] paramTypes = {String.class.getName(), String.class.getName(), int.class.getName(), List.class.getName()};
        Object[] paramValues = {gameId, username, routeId, selectedCards};
        socketClientCommunicator.send(gson.toJson(new Command(methodName, paramTypes, paramValues)));
    }

    @Override
    public void requestTickets(String gameID, String username) {
        String methodName = "requestTickets";
        String[] paramTypes = {String.class.getName(), String.class.getName()};
        Object[] paramValues = {gameID, username};
        socketClientCommunicator.send(gson.toJson(new Command(methodName, paramTypes, paramValues)));
    }
}
