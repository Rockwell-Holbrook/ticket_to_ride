package com.example.rholbrook.tickettoride.main;

import com.example.shared.model.Game;
import com.example.shared.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.View viewCallback;
    private MainActivityModel mModel;
    private String selectedGameId;

    public MainActivityPresenter(MainActivityContract.View viewCallback){
        this.viewCallback = viewCallback;
        this.mModel = MainActivityModel.getInstance();
    }

    @Override
    public void init() {
        List<Game> gamesList = new ArrayList<>();
        Player playerExample = new Player("player", true, Player.PlayerColor.BLACK);
        Game gameExample1 = new Game(playerExample, "Test Game");
        Game gameExample2 = new Game(playerExample, "Test Game 2");
        Game gameExample3 = new Game(playerExample,"Test Game 3");
        gamesList.add(gameExample1);
        gamesList.add(gameExample2);
        gamesList.add(gameExample3);
        viewCallback.updateGameList(gamesList);
    }

    @Override
    public void createGame() {
        try {
            mModel.createGame("username");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void joinGame() {
        mModel.joinGame(selectedGameId);
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
    public void setSelectedGameId(String id) {
        this.selectedGameId = id;
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
