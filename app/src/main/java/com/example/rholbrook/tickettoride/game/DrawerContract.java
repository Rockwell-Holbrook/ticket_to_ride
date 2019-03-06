package com.example.rholbrook.tickettoride.game;

import com.example.shared.model.Chat;
import com.example.shared.model.GameHistory;
import com.example.shared.model.Player;

import java.util.List;

public class DrawerContract {
    interface ChatView {
        void updateChatList(List<Chat> chatMessages);
    }

    public interface ChatPresenter {
        void updateChatList(List<Chat> chatMessages);
        void sendChat(String message);
        void getChatHistory();
        Player.PlayerColor getPlayerColor(String username);
    }

    interface HistoryView {
        void updateGameHistory(List<GameHistory> gameHistory);
    }

    interface HistoryPresenter {
        void updateGameHistory(List<GameHistory> gameHistory);
        void getGameHistory();
        Player.PlayerColor getPlayerColor(String username);
    }
}
