package com.example.rholbrook.tickettoride.main;

import com.example.shared.model.Game;
import com.example.shared.model.Player;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainActivityPresenter implements MainActivityContract.Presenter, Observer {
    private MainActivityContract.View viewCallback;
    private MainActivityModel mModel;

    public MainActivityPresenter(MainActivityContract.View viewCallback){
        this.viewCallback = viewCallback;
        this.mModel = MainActivityModel.getInstance();
    }



    @Override
    public void init() {
        mModel.setmPresenter(this);
    }

    @Override
    public void createGame(Player player, int maxPlayers, String gameName) {
        try {
            mModel.createGame(player, maxPlayers, gameName);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void joinGame() {
        mModel.joinGame(mModel.getSelectedGame().getGameId());
    }

    @Override
    public void onClick(int id) {
        switch (id) {
            case MainActivityModel.CREATE_GAME_BUTTON:
                viewCallback.createGame();
                break;
            case MainActivityModel.JOIN_GAME_BUTTON:
                viewCallback.joinGame();
                break;
        }
    }

    @Override
    public void setSelectedGame(Game game) {
        mModel.setSelectedGame(game);
    }

    @Override
    public void joinedGame() {
        viewCallback.startGameLobbyFragment();
    }

    @Override
    public void newGameList(List<Game> games) {
        viewCallback.updateGameList(games);
    }

    public Game getSelectedGame() {
        return mModel.getSelectedGame();
    }

    @Override
    public void connectToManagementServer() {
        try {
            mModel.connectToManagementServer();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            viewCallback.showToast(e.getMessage());
        }
    }

    @Override
    public ArrayList<CharSequence> getAvailableColors() {
        ArrayList<CharSequence> availableColors = new ArrayList<>();
        for(Player.PlayerColor color : mModel.getSelectedGame().getAvailableColors()) {
            switch (color) {
                case RED:
                    availableColors.add("Red");
                    break;
                case BLUE:
                    availableColors.add("Blue");
                    break;
                case BLACK:
                    availableColors.add("Black");
                    break;
                case GREEN:
                    availableColors.add("Green");
                    break;
                case YELLOW:
                    availableColors.add("Yellow");
                    break;
                default:
                    break;
            }
        }
        return availableColors;
    }

    @Override
    public void update(Observable observable, Object o) {
    }

    @Override
    public void joinedGame() {
        viewCallback.startGameLobbyFragment();
    }

    @Override
    public void newGameList(List<Game> games) {
        viewCallback.updateGameList(games);
    }
}
