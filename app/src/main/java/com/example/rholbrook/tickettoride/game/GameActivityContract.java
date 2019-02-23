package com.example.rholbrook.tickettoride.game;

import com.example.shared.model.DestinationCard;
import com.example.shared.model.Player;
import com.example.shared.model.TrainCard;

import java.util.List;
import java.util.Set;

public class GameActivityContract {

    public interface View {
        void setHandCards(List<TrainCard> handCards);
        void setFaceUpDeck(TrainCard[] testHand);
        void startUserTurn();
        void endUserTurn();
        void addFaceUpCardClickListeners();
        void enableFaceUpCards();
        void disableFaceUpCards();
        void updatePlayerData(Player[] players);
        void initializeGame();

        void setPlayerTicketDeck(List<DestinationCard> testDestinations);
    }

    public interface Presenter {
        void init();
        void selectFaceUpCard(int index);
        void selectFaceDownCardDeck();
        void clickDrawTickets();
        void initializeGame();

        Player getOpponentOne();
    }
}
