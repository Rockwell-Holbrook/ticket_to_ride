package com.example.rholbrook.tickettoride.game;

import com.example.shared.model.GameHistory;
import com.example.shared.model.Player;

import java.util.List;

public class HistoryContract {
    interface HistoryView {
        void updateGameHistory();
    }

    interface HistoryPresenter {
        List<GameHistory> getGameHistoryList();

        void updateGameHistory(List<GameHistory> gameHistory);
        void getGameHistory();
        Player.PlayerColor getPlayerColor(String username);
    }
}
