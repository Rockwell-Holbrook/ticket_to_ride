package com.example.rholbrook.tickettoride.game;

import com.example.shared.model.GameHistory;
import com.example.shared.model.Player;

import java.util.List;

public class HistoryContract {
    interface HistoryView {
        void updateGameHistory(List<GameHistory> gameHistory);
    }

    interface HistoryPresenter {
        void updateGameHistory(List<GameHistory> gameHistory);
        void getGameHistory();
        Player.PlayerColor getPlayerColor(String username);
    }
}
