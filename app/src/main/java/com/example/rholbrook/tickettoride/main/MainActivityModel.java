package com.example.rholbrook.tickettoride.main;

public class MainActivityModel {
    public final static int CREATE_GAME_BUTTON = 0;
    public final static int JOIN_GAME_BUTTON = 1;

    private static MainActivityModel instance;


    public MainActivityModel() {

    }

    public static synchronized MainActivityModel getInstance() {
        if (instance == null) {
            instance = new MainActivityModel();
        }
        return instance;
    }
}
