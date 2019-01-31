package com.example.rholbrook.tickettoride.main;

import model.Game;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.View viewCallback;
    private MainActivityModel mModel;

    public MainActivityPresenter(MainActivityContract.View viewCallback){
        this.viewCallback = viewCallback;
        this.mModel = MainActivityModel.getInstance();
    }

    @Override
    public void init() {
        List<Game> gamesList = new ArrayList<>();
        Player playerExample = new Player("player", true);
        Game gameExample1 = new Game(playerExample, 3, 1, "Test Game");
        Game gameExample2 = new Game(playerExample, 5, 4, "Test Game 2");
        Game gameExample3 = new Game(playerExample, 4, 2, "Test Game 3");
        gamesList.add(gameExample1);
        gamesList.add(gameExample2);
        gamesList.add(gameExample3);
        viewCallback.updateGameList(gamesList);
    }

    @Override
    public void createGame() {

    }

    @Override
    public void joinGame() {

    }

    @Override
    public void onClick(int id) {
        switch (id) {
            case MainActivityModel.CREATE_GAME_BUTTON:
                viewCallback.createGame();
                break;
            case MainActivityModel.JOIN_GAME_BUTTON:
                this.joinGame();
                break;
        }
    }
}
