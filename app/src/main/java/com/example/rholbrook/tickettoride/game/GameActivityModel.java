package com.example.rholbrook.tickettoride.game;

import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Player;

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

    public void selectFaceUpCard(int index) {

    }

    public void selectFaceDownCardDeck() {

    }

    public void drawTickets() {

    }

    public Player getOpponentOne() {
        return null;
    }
}
