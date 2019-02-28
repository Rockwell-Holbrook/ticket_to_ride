package com.example.rholbrook.tickettoride.game;

import com.example.shared.model.GameHistory;

import java.util.List;

public class GameHistoryFragmentPresenter implements DrawerContract.GameHistoryPresenter {
    private DrawerContract.GameHistoryView viewCallback;
    private GameActivityModel model;

    public GameHistoryFragmentPresenter(DrawerContract.GameHistoryView viewCallback) {
        this.viewCallback = viewCallback;
        model = GameActivityModel.getInstance();
        model.setHistoryListener(this);
    }

    @Override
    public void receivedHistoryObject(GameHistory history) {

    }

    @Override
    public void receivedGameHistory(List<GameHistory> gameHistory) {

    }

    @Override
    public void getGameHistory() {

    }
}
