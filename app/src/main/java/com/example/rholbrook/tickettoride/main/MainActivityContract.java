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
        void startGameLobbyFragment();
    }

    public interface Presenter {
        void init();
        void createGame(Player player, int maxPlayers, String gameName);
        void joinGame();
        void onClick(int id);
        void setSelectedGame(Game game);
        void joinedGame();
        void newGameList(ArrayList<Game> games);
        Game getSelectedGame();
        ArrayList<CharSequence> getAvailableColors();
    }
}
