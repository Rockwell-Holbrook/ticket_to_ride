package com.example.rholbrook.tickettoride.game;

public class GameActivityContract {

    public interface View {
        void setHandCards();
        void setFaceUpDeck();
    }

    public interface Presenter {
        void init();
    }
}
