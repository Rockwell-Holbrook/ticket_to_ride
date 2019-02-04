package com.example.rholbrook.tickettoride.main;

import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Game;
import com.example.shared.model.Player;
import com.example.shared.model.User;

import java.util.List;
import java.util.UUID;

public class MainActivityModel {
    public final static int CREATE_GAME_BUTTON = 0;
    public final static int JOIN_GAME_BUTTON = 1;
    private static MainActivityContract.Presenter mPresenter;

    private static MainActivityModel instance;


    public MainActivityModel() {

    }

    public MainActivityContract.Presenter getmPresenter() {
        return mPresenter;
    }

    public void setmPresenter(MainActivityContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    public static synchronized MainActivityModel getInstance() {
        if (instance == null) {
            instance = new MainActivityModel();
        }
        return instance;
    }

    public void joinedGame(String gameId){
        try {
            ServerProxy.getInstance().connectToGameSocket(gameId, "username");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        mPresenter.joinedGame();
    }

    public void joinGame(String selectedGameId) {
        //Call the joinGame method in ServerProxy
        try {
            ServerProxy.getInstance().joinGame("username", selectedGameId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void newGameListRetrieved(List<Game> games) {
        mPresenter.newGameList(games);
    }

    public void createGame(String username) {
        try {
            ServerProxy.getInstance().createGame("username");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
