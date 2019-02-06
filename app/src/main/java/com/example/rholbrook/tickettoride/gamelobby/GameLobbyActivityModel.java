package com.example.rholbrook.tickettoride.gamelobby;

import com.example.rholbrook.tickettoride.main.Authentication;
import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Player;

import java.util.ArrayList;

public class GameLobbyActivityModel {
    private static GameLobbyActivityModel instance;
    private GameLobbyActivityContract.Presenter mListener;

    private String hostUsername;
    private String gameId;
    private ArrayList<Player> connectedPlayers;
    private ArrayList<ChatModel> chatMessages;

    public GameLobbyActivityModel() {
        connectedPlayers = new ArrayList<>();
        chatMessages = new ArrayList<>();
    }

    public static GameLobbyActivityModel getInstance() {
        if (instance == null) {
            instance = new GameLobbyActivityModel();
        }
        return instance;
    }

    public GameLobbyActivityContract.Presenter getmListener() {
        return mListener;
    }

    public void setmListener(GameLobbyActivityContract.Presenter mListener) {
        this.mListener = mListener;
    }

    public String getHostUsername() {
        return hostUsername;
    }

    public void setHostUsername(String hostUsername) {
        this.hostUsername = hostUsername;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void newPlayerJoined(String username, Player.PlayerColor color) {
        Player player;
        if (username.equals(hostUsername)) {
            player = new Player(username, true, color);
        } else {
            player = new Player(username, false, color);
        }
        connectedPlayers.add(player);
        mListener.updatePlayerList(connectedPlayers);
    }

    public void newMessageReceived(String username, String message) {
        ChatModel newChat = new ChatModel(username, message);
        chatMessages.add(newChat);
        mListener.updateChatList(chatMessages);
    }

    public void startGame() {
        ServerProxy.getInstance().startGame(gameId);
    }

    public void gameStarted() {
        mListener.gameStarted();
    }

    public void sendChat(String message) {
        ServerProxy.getInstance().sendChat(Authentication.getInstance().getUsername(), gameId, message);
    }
}
