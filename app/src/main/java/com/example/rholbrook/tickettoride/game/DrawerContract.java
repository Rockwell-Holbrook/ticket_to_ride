package com.example.rholbrook.tickettoride.game;

import com.example.shared.model.Chat;
import com.example.shared.model.GameHistory;

import java.util.List;

public class DrawerContract {
    interface ChatView {
        void receivedChat(Chat chat);
        void receivedChatHistory(List<Chat> chatHistory);
    }

    interface ChatPresenter {
        void receivedChat(Chat chat);
        void receivedChatHistory(List<Chat> chatHistory);
        void sendChat(Chat chat);
        void getChatHistory();
    }

    interface GameHistoryView {
        void receivedHistoryObject(GameHistory history);
        void receivedGameHistory(List<GameHistory> gameHistory);
    }

    interface GameHistoryPresenter {
        void receivedHistoryObject(GameHistory history);
        void receivedGameHistory(List<GameHistory> gameHistory);
        void getGameHistory();
    }
}
