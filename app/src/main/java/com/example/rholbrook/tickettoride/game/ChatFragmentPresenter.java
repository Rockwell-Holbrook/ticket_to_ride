package com.example.rholbrook.tickettoride.game;

import android.util.Log;
import com.example.shared.model.Chat;
import com.example.shared.model.Player;

import java.util.List;

public class ChatFragmentPresenter implements DrawerContract.ChatPresenter {
    private String TAG = "ChatFragmentPresenter";

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
        Log.d(TAG, "sendChat");
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
