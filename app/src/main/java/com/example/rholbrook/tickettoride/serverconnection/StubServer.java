package com.example.rholbrook.tickettoride.serverconnection;

import android.util.Log;
import com.example.shared.commands.Command;
import com.example.shared.interfaces.IServer;
import com.example.shared.model.*;
import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

public class StubServer implements IServer {
    private String TAG = "StubServer";
    private String[] users = {"bob", "jill", "sam", "bob", "rachel"};
    private String[] words = {"quick", "brown", "fox", "jumped", "over", "the", "big", "blue", "moon"};
    private int numWords = words.length;
    private int numUsers = users.length;
    private Random rand = new Random();

    @Override
    public void sendChat(Chat chat, String gameId, boolean gameStarted) {
        Log.d(TAG, "sendChat");
        client.receivedChat(chat, true, gameId);
    }

    @Override
    public void getChatHistory(String gameId, String username, boolean gameStarted) {
        ArrayList<Chat> chats = new ArrayList<>();
        for (int i = 0; i < rand.nextInt(50); i++) {
            String a = words[rand.nextInt(numWords)];
            String b = words[rand.nextInt(numWords)];
            String c = words[rand.nextInt(numWords)];
            String response = a + " " + b + " " + c;
            String user = users[rand.nextInt(numUsers)];
            chats.add(new Chat(user, response));
        }
        client.receivedChatHistory(chats, gameStarted, username, gameId);
    }

    @Override
    public void getGameHistory(String gameId) {
        String[] users = {"bob", "jill", "sam", "bob", "rachel"};
        String[] actions = {"punched your mom", "ate a planet", "Decimated civilization", "hugged a panda", "pet a unicorn"};
        int numActions = actions.length;
        int numUsers = users.length;
        Random rand = new Random();
        ArrayList<GameHistory> history = new ArrayList<>();
        for (int i = 0; i < rand.nextInt(50); i++) {
            String action = actions[rand.nextInt(numActions)];
            String user = users[rand.nextInt(numUsers)];
            history.add(new GameHistory(user, action));
        }
        client.receivedGameHistory(history);
    }

    private static StubServer instance;
    private SocketClientCommunicator socketClientCommunicator;
    private static Gson gson = new Gson();
    private ClientFacade client = ClientFacade.getInstance();

    private StubServer() {}

    public static synchronized StubServer getInstance() {
        if (instance == null) {
            instance = new StubServer();
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
    public void readyToInitialize(String gameId, String username) {}

    @Override
    public void initializeComplete(String gameId, String username) {}

    @Override
    public void ticketsReturned(String gameId, String username, ArrayList<Ticket> returned) {

    }

    @Override
    public void turnEnded(String gameID, String username) {

    }

    @Override
    public void getCard(String gameId, String username, int index) {

    }

    @Override
    public void claimRoute(String gameId, String username, int routeId) {

    }

    @Override
    public void requestTickets(String gameID, String usernmae) {

    }

    @Override
    public void calculateClaimableRoutes(String gameId, String username) {

    }
}
