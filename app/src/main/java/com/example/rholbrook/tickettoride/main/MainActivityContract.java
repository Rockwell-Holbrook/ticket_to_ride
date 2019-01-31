package com.example.rholbrook.tickettoride.main;

import model.Game;

import java.util.List;

public class MainActivityContract {
    public interface View {
        void showToast(String message);
        void updateGameList(List<Game> games);
        void createGame();
    }

    public interface Presenter {
        void init();
        void createGame();
        void joinGame();
        void onClick(int id);
    }
}
