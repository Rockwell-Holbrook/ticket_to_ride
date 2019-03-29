package com.example.rholbrook.tickettoride.chat;

import com.example.rholbrook.tickettoride.game.GameActivityModel;
import com.example.shared.model.Chat;
import com.example.shared.model.Player;

import java.util.List;

public class ChatPresenter implements ChatContract.ChatPresenter {
    private ChatContract.ChatView viewCallback;
    private ChatContract.ChatModel model;
    public List<Chat> chatMessagesList;

    public ChatPresenter(ChatContract.ChatView viewCallback, ChatContract.ChatModel model) {
        this.viewCallback = viewCallback;
        this.model = model;
        model.setChatListener(this);
    }

    @Override
    public List<Chat> getChatMessagesList() {
        return chatMessagesList;
    }

    @Override
    public void setChatMessagesList(List<Chat> chatMessagesList) {
        this.chatMessagesList = chatMessagesList;
    }

    @Override
    public void updateChatList(List<Chat> chatMessages) {
        chatMessagesList = chatMessages;
        if (viewCallback != null)
        viewCallback.updateChatList(chatMessages);
    }

    @Override
    public void sendChat(String message) {
        model.sendChat(message);
    }

    @Override
    public void getChatHistory() {
        model.getChatHistory();
    }

    @Override
    public Player.PlayerColor getPlayerColor(String username) {
        return model.getPlayerColor(username);
    }
}
