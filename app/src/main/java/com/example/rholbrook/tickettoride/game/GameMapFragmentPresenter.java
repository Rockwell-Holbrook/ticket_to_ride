package com.example.rholbrook.tickettoride.game;

import java.util.Observable;
import java.util.Observer;

public class GameMapFragmentPresenter implements GameMapFragmentContract.Presenter, Observer {
    private GameMapFragmentContract.View viewCallback;
    private GameActivityModel mModel;

    public GameMapFragmentPresenter(GameMapFragmentContract.View viewCallback) {
        this.viewCallback = viewCallback;
        mModel = GameActivityModel.getInstance();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void init() {

    }
}
