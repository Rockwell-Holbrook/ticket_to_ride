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
        void initializeGame(List<Ticket> selectableTickets);
        void setPlayerTicketDeck(List<Ticket> testDestinations);
        void initializePlayers(List<Player> turnOrder);
        void updateClient(Player updatedPlayer);
        void updatePlayerOne(Player updatedPlayer);
        void updatePlayerTwo(Player updatedPlayer);
        void updatePlayerThree(Player updatedPlayer);
        void updatePlayerFour(Player updatedPlayer);
        void selectTickets(List<Ticket> selectableTickets, int selectionTypeIndicator);
    }

    public interface Presenter {
        void selectFaceUpCard(int index);
        void selectFaceDownCardDeck();
        void clickDrawTickets();
        void initializeGame(List<Ticket> tickets);
        void setupTurnOrder(List<Player> turnOrder);
        Drawable getAvatar(Context applicationContext, Player.PlayerColor playerColor);
        Drawable getColorBackground(Context applicationContext, Player.PlayerColor playerColor);
        void setFaceUpCards(List<TrainCard> faceUpCards);
        TrainCard getFaceUpCard(int i);
        void endTurn();
        void initializeComplete();
        void selectTickets(List<Ticket> tickets);
        void addTicketsToPlayer(List<Ticket> keptCards);
        void returnTickets(List<Ticket> returnedCards);
        void setGameId(String gameId);
        void readyToInitialize();
        List<TrainCard> getPlayerHand();
        List<Ticket> getPlayerTickets();
    }
}
