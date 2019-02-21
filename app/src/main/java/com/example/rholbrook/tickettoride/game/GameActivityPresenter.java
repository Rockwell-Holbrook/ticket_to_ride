package com.example.rholbrook.tickettoride.game;

import java.util.Observable;
import java.util.Observer;

public class GameActivityPresenter implements
        GameActivityContract.Presenter,
        Observer {

    private GameActivityContract.View viewCallback;
    private GameActivityModel mModel;

    public GameActivityPresenter(GameActivityContract.View viewCallback) {
        this.viewCallback = viewCallback;
        this.mModel = GameActivityModel.getInstance();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void init() {

    }
}
