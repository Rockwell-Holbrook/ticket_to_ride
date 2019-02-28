package com.example.rholbrook.tickettoride.game;

import com.example.shared.model.Chat;
import com.example.shared.model.GameHistory;

import java.util.List;

public class DrawerContract {
    interface ChatView {
        void updateChatList(List<Chat> chatMessages);
    }

    interface ChatPresenter {
        void updateChatList(List<Chat> chatMessages);
        void sendChat(String message);
        void getChatHistory();
    }

    interface HistoryView {
        void updateGameHistory(List<GameHistory> gameHistory);
    }

    interface HistoryPresenter {
        void updateGameHistory(List<GameHistory> gameHistory);
        void getGameHistory();
    }
}
