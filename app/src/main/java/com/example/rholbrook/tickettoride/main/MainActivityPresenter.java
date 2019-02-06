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
