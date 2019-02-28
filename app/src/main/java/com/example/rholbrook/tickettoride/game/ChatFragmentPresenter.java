package com.example.rholbrook.tickettoride.game;

import com.example.shared.model.Chat;

import java.util.List;

public class ChatFragmentPresenter implements DrawerContract.ChatPresenter {
    private DrawerContract.ChatView viewCallback;
    private GameActivityModel model;

    public ChatFragmentPresenter(DrawerContract.ChatView viewCallback) {
        this.viewCallback = viewCallback;
        model = GameActivityModel.getInstance();
        model.setChatListener(this);
    }

    @Override
    public void updateChatList(List<Chat> chatMessages) {
        viewCallback.updateChatList(chatMessages);
    }

    @Override
    public void sendChat(String message) {
        model.sendChat(String message);
    }

    @Override
    public void getChatHistory() {

    }
}
