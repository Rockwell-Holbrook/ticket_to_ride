package com.example.rholbrook.tickettoride.main;

import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Game;
import com.example.shared.model.Player;
import com.example.shared.model.User;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Observable;
import java.util.UUID;

public class MainActivityModel extends Observable {
    public final static int CREATE_GAME_BUTTON = 0;
    public final static int JOIN_GAME_BUTTON = 1;
    private static MainActivityContract.Presenter mPresenter;
    private Game selectedGame;

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

    public Game getSelectedGame() {
        return selectedGame;
    }

    public void setSelectedGame(Game selectedGame) {
        this.selectedGame = selectedGame;
    }

    public void joinedGame(String gameId){
        try {
            ServerProxy.getInstance().connectToGameSocket(gameId, Authentication.getInstance().getUsername());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        mPresenter.joinedGame();
    }

    public void joinGame(String selectedGameId) {
        //Call the joinGame method in ServerProxy
        try {
            ServerProxy.getInstance().joinGame(selectedGameId, new Player("username", false, Player.PlayerColor.BLUE));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void newGameListRetrieved(List<Game> games) {
        mPresenter.newGameList(games);
    }

    public void createGame(Player player, int maxPlayers, String gameName) {
        selectedGame = new Game(player, maxPlayers, gameName);
        mPresenter.joinedGame();
//        try {
//            ServerProxy.getInstance().createGame(player, maxPlayers, gameName);
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
    }

    public void connectToManagementServer() throws URISyntaxException {
        try {
            ServerProxy.getInstance().connectToManagementSocket(Authentication.getInstance().getUsername());
        } catch (URISyntaxException e) {
            throw e;
        }
    }
}
