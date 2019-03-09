package com.example.rholbrook.tickettoride.game;

import com.example.shared.model.GameHistory;
import com.example.shared.model.Player;

import java.util.List;

public class HistoryFragmentPresenter implements HistoryContract.HistoryPresenter {
    private HistoryContract.HistoryView viewCallback;
    private GameActivityModel model;

    public HistoryFragmentPresenter(HistoryContract.HistoryView viewCallback) {
        this.viewCallback = viewCallback;
        model = GameActivityModel.getInstance();
        model.setHistoryListener(this);
    }

    @Override
    public void updateGameHistory(List<GameHistory> gameHistory) {
        viewCallback.updateGameHistory(gameHistory);
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
