package com.example.rholbrook.tickettoride.chat;

import com.example.shared.model.Chat;
import com.example.shared.model.Player;

import java.util.List;

public class ChatContract {
    public interface ChatView {
        void updateChatList(List<Chat> chatMessages);
    }

    public interface ChatPresenter {
        void updateChatList(List<Chat> chatMessages);
        void sendChat(String message);
        void getChatHistory();
        Player.PlayerColor getPlayerColor(String username);
    }

    public interface ChatModel {
        void setChatListener(ChatContract.ChatPresenter chatListener);
        void sendChat(String message);
        void getChatHistory();
        Player.PlayerColor getPlayerColor(String username);
    }
}
