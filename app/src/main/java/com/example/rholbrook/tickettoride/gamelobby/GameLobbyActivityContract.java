package com.example.rholbrook.tickettoride.gamelobby;

import com.example.shared.model.Chat;
import com.example.shared.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GameLobbyActivityContract {
    public interface View {
        void setHostStartButtonUsername(boolean isHost);
        void updatePlayerList(ArrayList<Player> connectedPlayers);
        void startGameActivity(String gameId);
    }
    public interface Presenter {
        void init();
        void setGameId(String gameId);
        void updatePlayerList(ArrayList<Player> connectedPlayers);
        void startGame();
        void gameStarted();
        void getPlayerList();
        void checkHost(String username);
        List<Player> getConnectedPlayers();
    }
}
