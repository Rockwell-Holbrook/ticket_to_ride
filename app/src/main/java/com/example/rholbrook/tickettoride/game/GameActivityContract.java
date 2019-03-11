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
        void startUserTurn(Player player);
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
        void updateDeckCounts(int ticketDeckCount, int trainDeckCount);
        void setOpponentOneTurn(Player opponentOne);
        void setOpponentTwoTurn(Player opponentTwo);
        void setOpponentThreeTurn(Player opponentThree);
        void setOpponentFourTurn(Player opponentFour);
        void showToast(String message);
        void setOpponentThreeNotTurn(Player client);
        void setOpponentTwoNotTurn(Player client);
        void setOpponentOneNotTurn(Player client);
        void setClientNotTurn(Player client);
    }

    public interface Presenter {
        void selectFaceUpCard(int index);
        void selectFaceDownCardDeck();
        void clickDrawTickets();
        void initializeGame(List<Ticket> tickets);
        void setupTurnOrder(List<Player> turnOrder);
        Drawable getAvatar(Context applicationContext, Player.PlayerColor playerColor);
        Drawable getColorBackground(Context applicationContext, Player.PlayerColor playerColor);
        Drawable getColorTurnBackground(Context applicationContext, Player.PlayerColor playerColor);
        void setFaceUpCards(List<TrainCard> faceUpCards);
        TrainCard getFaceUpCard(int i);
        void startUserTurn();
        void endTurn();
        void initializeComplete();
        void selectTickets(List<Ticket> tickets);
        void addTicketsToPlayer(List<Ticket> keptCards);
        void returnTickets(List<Ticket> returnedCards);
        void setGameId(String gameId);
        void readyToInitialize();
        List<TrainCard> getPlayerHand();
        List<Ticket> getPlayerTickets();
        void setHandCards(List<TrainCard> cards);
        void runDemo1();
        void runDemo2();
        void setDeckCount(int ticketDeckCount, int trainDeckCount);
        void setOpponentOneTurn(Player opponentOne);
        void setOpponentTwoTurn(Player opponentTwo);
        void setOpponentThreeTurn(Player opponentThree);
        void setOpponentFourTurn(Player opponentFour);
        void sendToast(String change_turn);
    }
}
