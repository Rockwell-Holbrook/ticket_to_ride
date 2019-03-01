package com.example.rholbrook.tickettoride.serverconnection;

import com.example.shared.commands.Command;
import com.example.shared.interfaces.IServer;
import com.example.shared.model.Chat;
import com.example.shared.model.Player;
import com.example.shared.model.Route;
import com.example.shared.model.Ticket;
import com.google.gson.Gson;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class ServerProxy implements IServer {
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
        // Todo: Make this sucker work baby.
    }

    @Override
    public void getGameHistory(String gameId) {
        // Todo: Make this sucker work baby.
    }

    public void initializedGame(String gameId) {
        // Todo: Make this sucker work baby.
    }

    public void ticketsReturned(ArrayList<Ticket> returned) {
        // Todo: Make this sucker work baby.
    }

    public void turnEnded(String gameID) {
        // Todo: Make this sucker work baby.
    }

    public void getCard(int index) {
        // Todo: Make this sucker work baby.
    }

    public void claimRoute(Route route) {
        // Todo: Make this sucker work baby.
    }

    public void requestTickets(String gameID) {
        // Todo: Make this sucker work baby.
    }
}
