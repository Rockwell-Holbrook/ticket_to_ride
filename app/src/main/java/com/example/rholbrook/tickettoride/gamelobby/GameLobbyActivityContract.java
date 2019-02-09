package com.example.rholbrook.tickettoride.gamelobby;

import com.example.shared.model.Player;

import java.util.ArrayList;

public class GameLobbyActivityContract {
    public interface View {
        void setHostStartButtonUsername(boolean isHost);
        void updatePlayerList(ArrayList<Player> connectedPlayers);
        void updateChatList(ArrayList<ChatModel> chatMessages);
        void startGameActivity(String gameId);
    }
    public interface Presenter {
        void init();
        void setGameId(String gameId);
        void updatePlayerList(ArrayList<Player> connectedPlayers);
        void updateChatList(ArrayList<ChatModel> chatMessages);
        void startGame();
        void gameStarted();
        void sendChat(String message);
        void getPlayerList();
        void checkHost(String username);
    }
}
