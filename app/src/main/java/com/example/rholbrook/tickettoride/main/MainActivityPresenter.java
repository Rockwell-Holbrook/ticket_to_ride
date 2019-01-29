package com.example.rholbrook.tickettoride.main;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.View viewCallback;
    private MainActivityModel mModel;

    public MainActivityPresenter(MainActivityContract.View viewCallback){
        this.viewCallback = viewCallback;
    }

    @Override
    public void init() {

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
                this.createGame();
                break;
            case MainActivityModel.JOIN_GAME_BUTTON:
                this.joinGame();
                break;
        }
    }
}
