package com.example.rholbrook.tickettoride.game;

import java.util.Observable;

public class GameActivityModel extends Observable {
    private static GameActivityModel instance;

    public GameActivityModel() {
    }

    public static GameActivityModel getInstance() {
        if (instance == null) {
            instance = new GameActivityModel();
        }
        return instance;
    }
}
