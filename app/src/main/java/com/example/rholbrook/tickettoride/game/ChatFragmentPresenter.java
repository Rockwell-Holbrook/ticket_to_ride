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
    public void receivedChat(Chat chat) {
        viewCallback.receivedChat(chat);
    }

    @Override
    public void receivedChatHistory(List<Chat> chatHistory) {

    }

    @Override
    public void sendChat(Chat chat) {

    }

    @Override
    public void getChatHistory() {

    }
}
