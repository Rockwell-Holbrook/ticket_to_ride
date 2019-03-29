package com.example.rholbrook.tickettoride.game;

import com.example.shared.model.GameHistory;
import com.example.shared.model.Player;

import java.util.List;

public class HistoryFragmentPresenter implements HistoryContract.HistoryPresenter {
    private HistoryContract.HistoryView viewCallback;
    private GameActivityModel model;
    public List<GameHistory> gameHistoryList;

    public HistoryFragmentPresenter(HistoryContract.HistoryView viewCallback) {
        this.viewCallback = viewCallback;
        model = GameActivityModel.getInstance();
        model.setHistoryListener(this);
    }

    @Override
    public List<GameHistory> getGameHistoryList() {
        return gameHistoryList;
    }

    public void setGameHistoryList(List<GameHistory> gameHistoryList) {
        this.gameHistoryList = gameHistoryList;
    }

    @Override
    public void updateGameHistory(List<GameHistory> gameHistory) {
        gameHistoryList = gameHistory;
        viewCallback.updateGameHistory();
    }

    @Override
    public void getGameHistory() {
        model.getGameHistory();
    }

    @Override
    public Player.PlayerColor getPlayerColor(String username) {
        return model.getPlayerColor(username);
    }
}
