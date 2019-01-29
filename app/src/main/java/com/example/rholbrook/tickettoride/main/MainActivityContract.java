package com.example.rholbrook.tickettoride.main;

public class MainActivityContract {
    public interface View {
        void showToast(String message);
        void updateGameList();

    }

    public interface Presenter {
        void init();
        void createGame();
        void joinGame();
        void onClick(int id);
    }
}
