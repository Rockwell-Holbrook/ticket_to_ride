package com.example.rholbrook.tickettoride.main;

import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Game;
import com.example.shared.model.Player;
import com.example.shared.model.User;
import java.net.URISyntaxException;
import java.util.*;

public class MainActivityModel extends Observable {
    public final static int CREATE_GAME_BUTTON = 0;
    public final static int JOIN_GAME_BUTTON = 1;
    private static MainActivityContract.Presenter mPresenter;
    private Game selectedGame;
    private ArrayList<Player.PlayerColor> possibleColors;
    private static MainActivityModel instance;



    public MainActivityModel() {
        newAvailableColorsSet();
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
            connectToGameServer(gameId);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mPresenter.joinedGame(gameId);
    }

    public void joinGame(String selectedGameId, Player.PlayerColor color) {
        //Call the joinGame method in ServerProxy
        try {
            ServerProxy.getInstance().joinGame(selectedGameId, new Player(Authentication.getInstance().getUsername(), false, color));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void newGameListRetrieved(ArrayList<Game> games) {
        mPresenter.newGameList(games);
    }

    public void createGame(Player player, int maxPlayers, String gameName) {
        selectedGame = new Game(player, maxPlayers, gameName);
        try {
            ServerProxy.getInstance().createGame(player, maxPlayers, gameName);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void connectToManagementServer() throws URISyntaxException {
        try {
            ServerProxy.getInstance().connectToManagementSocket(Authentication.getInstance().getUsername());
        } catch (URISyntaxException e) {
            throw e;
        }
    }

    public void connectToGameServer(String gameId) throws URISyntaxException {
        ServerProxy.getInstance().connectToGameSocket(gameId, Authentication.getInstance().getUsername());
    }

    public void getGameList() {
        ServerProxy.getInstance().getGameList(Authentication.getInstance().getUsername());
    }

    public ArrayList<Player.PlayerColor> getAvailableColors() {
        Set<Player> players = selectedGame.getPlayerList();
        ArrayList<Player.PlayerColor> availableColors = possibleColors;
        for (Player player : players) {
            availableColors.remove(player.getPlayerColor());
        }
        return availableColors;
    }

    public void newAvailableColorsSet() {
        possibleColors = new ArrayList<>();
        possibleColors.add(Player.PlayerColor.BLUE);
        possibleColors.add(Player.PlayerColor.BLACK);
        possibleColors.add(Player.PlayerColor.GREEN);
        possibleColors.add(Player.PlayerColor.RED);
        possibleColors.add(Player.PlayerColor.YELLOW);
    }
}
