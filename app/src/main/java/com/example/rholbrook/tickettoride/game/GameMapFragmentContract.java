package com.example.rholbrook.tickettoride.game;

import com.example.shared.model.Player;

public class GameMapFragmentContract {
    public interface View {
        void startUserTurn();
        void endUserTurn();
    }

    public interface  Presenter {
        void init();
    }
}
