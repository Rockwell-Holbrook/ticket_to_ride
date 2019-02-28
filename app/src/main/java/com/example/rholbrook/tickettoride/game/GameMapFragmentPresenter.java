package com.example.rholbrook.tickettoride.game;

import com.example.shared.model.Player;

import java.util.Observable;
import java.util.Observer;

public class GameMapFragmentPresenter implements GameMapFragmentContract.Presenter, Observer {
    private GameMapFragmentContract.View viewCallback;
    private GameActivityModel mModel;

    public GameMapFragmentPresenter(GameMapFragmentContract.View viewCallback) {
        this.viewCallback = viewCallback;
        mModel = GameActivityModel.getInstance();
        mModel.setGameMapFragmentPresenter(this);
        mModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void init() {

    }
}
