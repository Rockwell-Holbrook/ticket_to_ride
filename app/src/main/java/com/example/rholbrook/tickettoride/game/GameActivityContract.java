package com.example.rholbrook.tickettoride.game;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.example.shared.model.Ticket;
import com.example.shared.model.Player;
import com.example.shared.model.TrainCard;

import java.util.List;

public class GameActivityContract {

    public interface View {
        void setHandCards(List<TrainCard> handCards);
        void setFaceUpDeck(List<TrainCard> testHand);
        void startUserTurn();
        void endUserTurn();
        void addFaceUpCardClickListeners();
        void enableFaceUpCards();
        void disableFaceUpCards();
        void initializeGame();
        void setPlayerTicketDeck(List<Ticket> testDestinations);
        void initializePlayers(List<Player> turnOrder);
        void updateClient(Player updatedPlayer);
        void updatePlayerOne(Player updatedPlayer);
        void updatePlayerTwo(Player updatedPlayer);
        void updatePlayerThree(Player updatedPlayer);
        void updatePlayerFour(Player updatedPlayer);
    }

    public interface Presenter {
        void init();
        void selectFaceUpCard(int index);
        void selectFaceDownCardDeck();
        void clickDrawTickets();
        void initializeGame();
        Player getOpponentOne();
        void setupTurnOrder(List<Player> turnOrder);
        Drawable getAvatar(Context applicationContext, Player.PlayerColor playerColor);
        Drawable getColorBackground(Context applicationContext, Player.PlayerColor playerColor);
        void setFaceUpCards(List<TrainCard> faceUpCards);
    }
}
