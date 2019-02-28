package com.example.rholbrook.tickettoride.game;

import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Game;
import com.example.shared.model.Player;
import com.example.shared.model.Ticket;
import com.example.shared.model.TrainCard;

import java.util.List;
import java.util.Observable;
import java.util.Set;

public class GameActivityModel extends Observable {
    private static final int NO_OPPONENTS = 1;
    private static final int ONE_OPPONENT = 2;
    private static final int TWO_OPPONENTS = 3;
    private static final int THREE_OPPONENTS = 4;
    private static final int FOUR_OPPONENTS = 5;

    private static GameActivityModel instance;
    private GameActivityContract.Presenter gameActivityPresenter;
    private GameMapFragmentContract.Presenter gameMapFragmentPresenter;
    private Player opponentOne;
    private Player opponentTwo;
    private Player opponentThree;
    private Player opponentFour;
    private Player client;
    private List<Player> turnOrder;

    public GameActivityModel() {

    }

    public Player getOpponentOne() {
        return opponentOne;
    }

    public void setOpponentOne(Player opponentOne) {
        this.opponentOne = opponentOne;
    }

    public Player getOpponentTwo() {
        return opponentTwo;
    }

    public void setOpponentTwo(Player opponentTwo) {
        this.opponentTwo = opponentTwo;
    }

    public Player getOpponentThree() {
        return opponentThree;
    }

    public void setOpponentThree(Player opponentThree) {
        this.opponentThree = opponentThree;
    }

    public Player getOpponentFour() {
        return opponentFour;
    }

    public void setOpponentFour(Player opponentFour) {
        this.opponentFour = opponentFour;
    }

    public Player getClient() {
        return client;
    }

    public void setClient(Player client) {
        this.client = client;
    }

    public void setGameActivityPresenter(GameActivityContract.Presenter gameActivityPresenter) {
        this.gameActivityPresenter = gameActivityPresenter;
    }

    public void setGameMapFragmentPresenter(GameMapFragmentContract.Presenter gameMapFragmentPresenter) {
        this.gameMapFragmentPresenter = gameMapFragmentPresenter;
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

    public void newMessageReceived(String username, String message) {
    }

    public void initializeGame(List<TrainCard> trainCards, List<Ticket> tickets, List<Player> turnOrder) {
        this.turnOrder = turnOrder;
        setPlayers(turnOrder);
        client.setTrainCards(trainCards);
        client.setTickets(tickets);
        setChanged();
        notifyObservers(client);
        clearChanged();
    }

    private void setPlayers(List<Player> turnOrder) {
        switch (turnOrder.size()) {
            case NO_OPPONENTS:
                client = turnOrder.get(0);
                break;
            case ONE_OPPONENT:
                client = turnOrder.get(0);
                opponentOne = turnOrder.get(1);
                break;
            case TWO_OPPONENTS:
                client = turnOrder.get(0);
                opponentOne = turnOrder.get(1);
                opponentTwo = turnOrder.get(2);
                break;
            case THREE_OPPONENTS:
                client = turnOrder.get(0);
                opponentOne = turnOrder.get(1);
                opponentTwo = turnOrder.get(2);
                opponentThree = turnOrder.get(3);
                break;
            case FOUR_OPPONENTS:
                client = turnOrder.get(0);
                opponentOne = turnOrder.get(1);
                opponentTwo = turnOrder.get(2);
                opponentThree = turnOrder.get(3);
                opponentFour = turnOrder.get(4);
                break;
        }
        gameActivityPresenter.setupTurnOrder(turnOrder);
    }

    public void setFaceUpCards(List<TrainCard> faceUpCards) {
        gameActivityPresenter.setFaceUpCards(faceUpCards);
    }
}
