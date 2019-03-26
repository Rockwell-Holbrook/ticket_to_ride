package com.example.rholbrook.tickettoride.main;

import com.example.shared.model.Game;
import com.example.shared.model.Player;

import java.util.ArrayList;
import java.util.List;

public class MainActivityContract {
    public interface View {
        void showToast(String message);
        void updateGameList(ArrayList<Game> games);
        void createGame();
        void joinGame();
        void selectGame(Game gameNumber);
        void startGameLobbyFragment(String gameId);
    }

    public interface Presenter {
        void init();
        void createGame(Player player, int maxPlayers, String gameName);
        void joinGame(Player.PlayerColor color);
        void onClick(int id);
        void setSelectedGame(Game game);
        void joinedGame(String gameId);
        void newGameList(ArrayList<Game> games);
        Game getSelectedGame();
        void socketConnectionError(Exception ex);
    }
}
