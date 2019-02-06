package com.example.rholbrook.tickettoride.gamelobby;

import com.example.rholbrook.tickettoride.main.Authentication;
import com.example.shared.model.Player;

import java.util.ArrayList;

public class GameLobbyActivityPresenter implements GameLobbyActivityContract.Presenter {
    private GameLobbyActivityContract.View viewCallback;
    private GameLobbyActivityModel mModel;

    public GameLobbyActivityPresenter(GameLobbyActivityContract.View viewCallback) {
        this.viewCallback = viewCallback;
    }

    @Override
    public void init() {
        mModel = GameLobbyActivityModel.getInstance();
        mModel.setmListener(this);

    }

    @Override
    public void checkHost() {
        if (Authentication.getInstance().getUsername().equals(mModel.getHostUsername())) {
            viewCallback.setHostStartButtonUsername(true);
        } else {
            viewCallback.setHostStartButtonUsername(false);
        }
    }

    @Override
    public void setHost(String hostUsername) {
        mModel.setHostUsername(hostUsername);
        checkHost();
    }

    @Override
    public void setGameId(String gameId) {
        mModel.setGameId(gameId);
    }

    @Override
    public void updatePlayerList(ArrayList<Player> connectedPlayers) {
        viewCallback.updatePlayerList(connectedPlayers);
    }

    @Override
    public void updateChatList(ArrayList<ChatModel> chatMessages) {
        viewCallback.updateChatList(chatMessages);
    }

    @Override
    public void startGame() {
        mModel.startGame();
    }

    @Override
    public void gameStarted() {
        viewCallback.startGameActivity(mModel.getGameId());
    }

    @Override
    public void sendChat(String message) {
        mModel.sendChat(message);
    }
}
