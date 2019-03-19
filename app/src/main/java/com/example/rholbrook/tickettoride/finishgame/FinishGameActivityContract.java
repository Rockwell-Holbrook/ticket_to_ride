package com.example.rholbrook.tickettoride.finishgame;

import com.example.shared.model.Player;

import java.util.List;

public class FinishGameActivityContract {
    public interface View {
        void updateData(List<Player> players);
    }
    public interface Presenter {
        void init();
    }
}
