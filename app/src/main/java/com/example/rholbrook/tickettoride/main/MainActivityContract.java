package com.example.rholbrook.tickettoride.main;

import model.Game;

import java.util.List;
import java.util.UUID;

public class MainActivityContract {
    public interface View {
        void showToast(String message);
        void updateGameList(List<Game> games);
        void createGame();
        void selectGame(UUID gameNumber);
    }

    public interface Presenter {
        void init();
        void createGame();
        void joinGame();
        void onClick(int id);
        void setSelectedGameId(UUID id);
    }
}
