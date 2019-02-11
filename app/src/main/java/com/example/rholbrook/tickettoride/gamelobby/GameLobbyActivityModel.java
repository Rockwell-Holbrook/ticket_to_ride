package com.example.rholbrook.tickettoride.gamelobby;

import com.example.rholbrook.tickettoride.main.Authentication;
import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Player;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Set;

public class GameLobbyActivityModel extends Observable {
    private static GameLobbyActivityModel instance;
    private GameLobbyActivityContract.Presenter mListener;

    private String gameId;
    private ArrayList<ChatModel> chatMessages;

    public GameLobbyActivityModel() {
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

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
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

    public void newPlayerJoined(Set<Player> playerList) {
        ArrayList<Player> players = new ArrayList<>(playerList);
        mListener.updatePlayerList(players);
        mListener.updateCanStart(players);
    }

    public void getPlayerList() {
        ServerProxy.getInstance().getPlayerList(gameId);
    }
}
