package com.example.rholbrook.tickettoride.gamelobby;

import com.example.rholbrook.tickettoride.chat.ChatContract;
import com.example.rholbrook.tickettoride.main.Authentication;
import com.example.shared.model.Chat;
import com.example.shared.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GameLobbyActivityPresenter implements GameLobbyActivityContract.Presenter, Observer {
    private GameLobbyActivityContract.View viewCallback;
    private ChatContract.ChatView chatView;
    private GameLobbyActivityModel mModel;

    public GameLobbyActivityPresenter(GameLobbyActivityContract.View viewCallback, ChatContract.ChatView chatView) {
        this.viewCallback = viewCallback;
        this.chatView = chatView;
    }

    @Override
    public void init() {
        mModel = GameLobbyActivityModel.getInstance();
        mModel.setmListener(this);
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
    public void startGame() {
        mModel.startGame();
    }

    @Override
    public void gameStarted() {
        viewCallback.startGameActivity(mModel.getGameId());
    }

    @Override
    public void getPlayerList() {
        mModel.getPlayerList();
    }

    @Override
    public void checkHost(String username) {
        viewCallback.setHostStartButtonUsername(username.equals(Authentication.getInstance().getUsername()));
    }

    @Override
    public List<Player> getConnectedPlayers() {
        return mModel.getConnectedPlayers();
    }

    @Override
    public void socketConnectionError(Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    //@Override
    //public void updateCanStart(ArrayList<Player> playerList) {
 //       viewCallback.updateCanStart(playerList);
   // }
}
