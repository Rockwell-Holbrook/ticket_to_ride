package com.example.rholbrook.tickettoride.game;

import com.example.shared.model.TrainCard;

import java.util.List;

public class GameActivityContract {

    public interface View {
        void setHandCards(List<TrainCard> handCards);
        void setFaceUpDeck(TrainCard[] testHand);
    }

    public interface Presenter {
        void init();
    }
}
