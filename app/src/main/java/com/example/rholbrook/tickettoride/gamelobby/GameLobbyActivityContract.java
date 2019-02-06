package com.example.rholbrook.tickettoride.gamelobby;

public class GameLobbyActivityContract {
    public interface View {
        void setHostStartButtonUsername(boolean isHost);
    }
    public interface Presenter {
        void init();
        void checkHost();
        void setHost(String hostUsername);
        void setGameId(String gameId);
    }
}
