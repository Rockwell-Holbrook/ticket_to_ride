package com.example.rholbrook.tickettoride.game;

import com.example.rholbrook.tickettoride.main.Authentication;
import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;

public class GameActivityModel extends Observable {
    private static final int NO_OPPONENTS = 1;
    private static final int ONE_OPPONENT = 2;
    private static final int TWO_OPPONENTS = 3;
    private static final int THREE_OPPONENTS = 4;
    private static final int FOUR_OPPONENTS = 5;
    private static final int SELECT_FACE_DOWN_DECK = 5;
    public static final int INITIALIZE_TICKETS_SELECTION_TYPE = 0;
    public static final int ADDITIONAL_TICKETS_SELECTION_TYPE = 1;

    private static GameActivityModel instance;
    private GameActivityContract.Presenter gameActivityPresenter;
    private GameMapFragmentContract.Presenter gameMapFragmentPresenter;
    private String gameId;
    private Player opponentOne;
    private Player opponentTwo;
    private Player opponentThree;
    private Player opponentFour;
    private Player client;
    private List<Player> turnOrder;
    private List<TrainCard> faceUpCards;
    private boolean isTurn;

    public GameActivityModel() {

    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Player getOpponentOne() {
        return opponentOne;
    }

    public List<TrainCard> getFaceUpCards() {
        return faceUpCards;
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
        ServerProxy.getInstance().getCard(gameId, Authentication.getInstance().getUsername(), index);
    }

    public void selectFaceDownCardDeck() {
        ServerProxy.getInstance().getCard(gameId, Authentication.getInstance().getUsername(), SELECT_FACE_DOWN_DECK);
    }

    public void drawTickets() {
        ServerProxy.getInstance().requestTickets(gameId, Authentication.getInstance().getUsername());
    }

    public void newMessageReceived(String username, String message) {
        //Todo: sendMessage
    }

    public void initializeGame(List<TrainCard> trainCardsFaceUp, List<TrainCard> trainCards, List<Ticket> tickets, List<Player> turnOrder) {
        this.turnOrder = turnOrder;
        setPlayers(turnOrder);
        client.setTrainCards(trainCards);
        setChanged();
        notifyObservers(client);
        clearChanged();
        this.faceUpCards = trainCardsFaceUp;
        gameActivityPresenter.setFaceUpCards(trainCardsFaceUp);
        gameActivityPresenter.initializeGame(tickets);
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
        this.faceUpCards = faceUpCards;
        gameActivityPresenter.setFaceUpCards(faceUpCards);
    }

    public void endUserTurn() {
        isTurn = false;
        setChanged();
        notifyObservers(isTurn);
        clearChanged();
        ServerProxy.getInstance().turnEnded(gameId, Authentication.getInstance().getUsername());
    }

    public void initializeComplete() {
        ServerProxy.getInstance().initializeComplete(gameId, Authentication.getInstance().getUsername());
    }

    public void ticketDataReceived(List<Ticket> tickets) {
        gameActivityPresenter.selectTickets(tickets);
    }

    public void clientAddTickets(List<Ticket> keptCards) {
        List<Ticket> newClientTickets = getClient().getTickets();
        if (newClientTickets == null) {
            newClientTickets = new ArrayList<>();
        }
        newClientTickets.addAll(keptCards);
        client.setTickets(newClientTickets);
        setChanged();
        notifyObservers(client);
        clearChanged();
    }

    public void returnTickets(List<Ticket> returnedCards) {
        ArrayList<Ticket> cards = new ArrayList<>(returnedCards);
        ServerProxy.getInstance().ticketsReturned(gameId, Authentication.getInstance().getUsername(), cards);
    }

    public void readyToInitialize() {
        ServerProxy.getInstance().readyToInitialize(gameId, Authentication.getInstance().getUsername());
    }

    public void playerTurnEnded(Player player) {
        if (player.getUsername().equals(client.getUsername())){
            client = player;
            setChanged();
            notifyObservers(client);
            clearChanged();
        } else if (player.getUsername().equals(opponentOne.getUsername())) {
            opponentOne = player;
            setChanged();
            notifyObservers(opponentOne);
            clearChanged();
        } else if (player.getUsername().equals(opponentTwo.getUsername())) {
            opponentTwo = player;
            setChanged();
            notifyObservers(opponentTwo);
            clearChanged();
        } else if (player.getUsername().equals(opponentThree.getUsername())) {
            opponentThree = player;
            setChanged();
            notifyObservers(opponentThree);
            clearChanged();
        } else if (player.getUsername().equals(opponentFour.getUsername())) {
            opponentFour = player;
            setChanged();
            notifyObservers(opponentFour);
            clearChanged();
        }
    }

    public void startTurn(List<Route> availableRoutes) {
        gameMapFragmentPresenter.updateAvailableRoutes(availableRoutes);
        isTurn = true;
        setChanged();
        notifyObservers(isTurn);
        clearChanged();
    }

    public void selectRoute(int routeId) {
        ServerProxy.getInstance().claimRoute(gameId, Authentication.getInstance().getUsername(), routeId);
    }
}
