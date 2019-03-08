package com.example.rholbrook.tickettoride.gamelobby;

import com.example.rholbrook.tickettoride.chat.ChatContract;
import com.example.rholbrook.tickettoride.main.Authentication;
import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Chat;
import com.example.shared.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;

public class GameLobbyActivityModel extends Observable implements ChatContract.ChatModel {
    private static GameLobbyActivityModel instance;
    private GameLobbyActivityContract.Presenter mListener;
    private ChatContract.ChatPresenter chatListener;

    private String gameId;
    private List<Player> connectedPlayers;
    private List<Chat> chatMessages;

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

    @Override
    public void setChatListener(ChatContract.ChatPresenter chatListener) {
        this.chatListener = chatListener;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public List<Player> getConnectedPlayers() {
        return connectedPlayers;
    }

    public void setConnectedPlayers(ArrayList<Player> connectedPlayers) {
        this.connectedPlayers = connectedPlayers;
    }

    public void receivedChat(Chat chat) {
        chatMessages.add(chat);
        chatListener.updateChatList(chatMessages);
    }

    public void startGame() {
        ServerProxy.getInstance().startGame(gameId);
    }

    public void gameStarted() {
        mListener.gameStarted();
    }

    public void newPlayerJoined(Set<Player> playerList) {
        this.connectedPlayers = new ArrayList<Player>(playerList);
        mListener.updatePlayerList(new ArrayList<Player>(playerList));
    }

    public void getPlayerList() {
        ServerProxy.getInstance().getPlayerList(gameId);
    }

    @Override
    public void sendChat(String message) {
        Chat newChat = new Chat(Authentication.getInstance().getUsername(), message);
        ServerProxy.getInstance().sendChat(newChat, gameId, false);
    }

    @Override
    public Player.PlayerColor getPlayerColor(String username) {
        for (Player player : connectedPlayers) {
            if (player.getUsername().equals(username)) {
                return player.getPlayerColor();
            }
        }
        return null;
    }

    @Override
    public void getChatHistory() {
        chatListener.updateChatList(chatMessages);
    }
}
