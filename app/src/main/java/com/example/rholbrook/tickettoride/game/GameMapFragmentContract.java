package com.example.rholbrook.tickettoride.game;

import android.widget.Button;
import com.example.shared.model.Player;
import com.example.shared.model.Route;

import java.util.List;

public class GameMapFragmentContract {
    public interface View {
        void startUserTurn(List<Button> buttons);
        void endUserTurn(List<Button> buttons);
        void addClickListeners(Integer integer);
    }

    public interface  Presenter {
        void init();
        void updateAvailableRoutes(List<Route> availableRoutes);
        void selectRoute(int routeId);
        void addAvailableButton(Button button);
        void startUserTurn();
    }
}
