package com.example.rholbrook.tickettoride.game;

import android.util.Log;
import com.example.rholbrook.tickettoride.chat.ChatContract;
import com.example.rholbrook.tickettoride.main.Authentication;
import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.interfaces.IServer;
import com.example.shared.model.Chat;
import com.example.shared.model.Game;
import com.example.shared.model.GameHistory;
import com.example.shared.model.Player;
import com.example.shared.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class GameActivityModel extends Observable implements ChatContract.ChatModel {
    private String TAG = "GameActivityModel";

    private static final int NO_OPPONENTS = 1;
    private static final int ONE_OPPONENT = 2;
    private static final int TWO_OPPONENTS = 3;
    private static final int THREE_OPPONENTS = 4;
    private static final int FOUR_OPPONENTS = 5;
    private static final int SELECT_FACE_DOWN_DECK = 5;
    public static final int INITIALIZE_TICKETS_SELECTION_TYPE = 0;
    public static final int ADDITIONAL_TICKETS_SELECTION_TYPE = 1;

    private static GameActivityModel instance;
    private ChatContract.ChatPresenter chatListener;
    private HistoryContract.HistoryPresenter historyListener;
    private IServer server;
    private String gameId;
    private GameActivityContract.Presenter gameActivityPresenter;
    private GameMapFragmentContract.Presenter gameMapFragmentPresenter;
    private Player opponentOne;
    private Player opponentTwo;
    private Player opponentThree;
    private Player opponentFour;
    private Player client;
    private List<Player> turnOrder;
    private List<TrainCard> faceUpCards;
    private boolean isTurn;
    private Game game;
    private List<Chat> chatMessages;
    private List<GameHistory> gameHistory;
    private List<Route> availableRoutes;

    public GameActivityModel() {
        server = ServerProxy.getInstance();
        chatMessages = new ArrayList<>();

    }

    public void notifyLastTurn() {
        gameActivityPresenter.notifyLastTurn();
    }



    /**
     * Getters and Setters
     */

    public List<Route> getAvailableRoutes() {
        return availableRoutes;
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

    public void setHistoryListener(HistoryContract.HistoryPresenter historyListener) {
        this.historyListener = historyListener;
    }

    @Override
    public void setChatListener(ChatContract.ChatPresenter chatListener) {
        this.chatListener = chatListener;
    }

    public static GameActivityModel getInstance() {
        if (instance == null) {
            instance = new GameActivityModel();
        }
        return instance;
    }

    @Override
    public Player.PlayerColor getPlayerColor(String username) {
        if (opponentOne != null && opponentOne.getUsername().equals(username)) {
            return opponentOne.getPlayerColor();
        }
        if (opponentTwo != null && opponentTwo.getUsername().equals(username)) {
            return opponentTwo.getPlayerColor();
        }
        if (opponentThree != null && opponentThree.getUsername().equals(username)) {
            return opponentThree.getPlayerColor();
        }
        if (opponentFour != null && opponentFour.getUsername().equals(username)) {
            return opponentFour.getPlayerColor();
        }
        return client.getPlayerColor();
    }

    /**
     * Chat Methods
     */

    public void receivedChat(Chat chat) {
        chatMessages.add(chat);
        chatListener.updateChatList(chatMessages);
        gameActivityPresenter.sendNotification(chat);
    }

    public void receivedChatHistory(List<Chat> chatHistory) {
        chatMessages = chatHistory;
        chatListener.updateChatList(chatMessages);
    }

    @Override
    public void sendChat(String message) {
        Log.d(TAG, "sendChat");
        Chat newChat = new Chat(Authentication.getInstance().getUsername(), message);
        server.sendChat(newChat, gameId, true);
    }

    @Override
    public void getChatHistory() {
        if (chatListener != null)
        chatListener.updateChatList(chatMessages);
        //server.getChatHistory(gameId, Authentication.getInstance().getUsername(), true);
    }

    /**
     * History Methods
     */
    public void receivedHistoryObject(GameHistory history) {
        gameHistory.add(history);
        historyListener.updateGameHistory(this.gameHistory);
    }

    public void receivedGameHistory(List<GameHistory> gameHistory) {
        if (historyListener != null)
        this.gameHistory = gameHistory;
        historyListener.updateGameHistory(this.gameHistory);
    }

    public void getGameHistory() {
        server.getGameHistory(gameId, Authentication.getInstance().getUsername());
    }

    /**
     * Game Initialization
     */

    public void readyToInitialize() {
        ServerProxy.getInstance().readyToInitialize(gameId, Authentication.getInstance().getUsername());
    }

    /**
     * Initializes the game
     *
     * @param trainCardsFaceUp list of 5 cards to set as the faceup cards
     * @param trainCards list of 4 cards to set as the player's cards
     * @param tickets list of 3 tickets to select from
     * @param turnOrder order of the game
     */
    public void initializeGame(List<TrainCard> trainCardsFaceUp, List<TrainCard> trainCards, List<Ticket> tickets, List<Player> turnOrder) {
        this.turnOrder = turnOrder;
        setPlayers(turnOrder);
        client.setTrainCards(trainCards);
        setChanged();
        notifyObservers(client);
        clearChanged();
        this.faceUpCards = trainCardsFaceUp;
        gameActivityPresenter.setFaceUpCards(trainCardsFaceUp);
        client.getTickets().clear();
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

    public void initializeComplete() {
        ServerProxy.getInstance().initializeComplete(gameId, Authentication.getInstance().getUsername());
    }


    /**
     * Turn Order
     */

    public void startTurn(List<Route> availableRoutes) {
        this.availableRoutes = availableRoutes;
        gameMapFragmentPresenter.updateAvailableRoutes(availableRoutes);
        isTurn = true;
        setChanged();
        notifyObservers(isTurn);
        clearChanged();
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

    public void endUserTurn() {
        isTurn = false;
        setChanged();
        notifyObservers(isTurn);
        clearChanged();
        ServerProxy.getInstance().turnEnded(gameId, Authentication.getInstance().getUsername());
    }

    public void playerTurnStarted(Player player) {
        gameActivityPresenter.turnStarted(player);
    }

    /**
     * Tickets
     */

    public void ticketDataReceived(List<Ticket> tickets) {
        gameActivityPresenter.selectTickets(tickets);
    }

    public void returnTickets(List<Ticket> returnedCards) {
        ArrayList<Ticket> cards = new ArrayList<>(returnedCards);
        ServerProxy.getInstance().ticketsReturned(gameId, Authentication.getInstance().getUsername(), cards);
    }

    public void drawTickets() {
        ServerProxy.getInstance().requestTickets(gameId, Authentication.getInstance().getUsername());
    }

    /**
     * Routes
     */

    public void selectRoute(int routeId, List<TrainCard> selectedCards) {
        client.removeTrainCards(selectedCards);
        setChanged();
        notifyObservers(client);
        clearChanged();
        ServerProxy.getInstance().claimRoute(gameId, Authentication.getInstance().getUsername(), routeId, selectedCards);
    }

    public void routeClaimed(Player player, Route route) {
        gameMapFragmentPresenter.routeClaimed(player,route);
        if (player.getUsername().equals(client.getUsername())) {
            int remainingTrainCars = client.getRemainingTrainCars();
            int points = client.getPointsEarned();
            remainingTrainCars -= route.getLength();
            points += route.getPointValue();
            setChanged();
            notifyObservers(client);
            clearChanged();
        } else if (player.getUsername().equals(opponentOne.getUsername())) {
            int remainingTrainCars = opponentOne.getRemainingTrainCars();
            int points = opponentOne.getPointsEarned();
            remainingTrainCars -= route.getLength();
            points += route.getPointValue();
            setChanged();
            notifyObservers(opponentOne);
            clearChanged();
        } else if (player.getUsername().equals(opponentTwo.getUsername())) {
            int remainingTrainCars = opponentTwo.getRemainingTrainCars();
            int points = opponentTwo.getPointsEarned();
            remainingTrainCars -= route.getLength();
            points += route.getPointValue();
            setChanged();
            notifyObservers(opponentTwo);
            clearChanged();
        } else if (player.getUsername().equals(opponentThree.getUsername())) {
            int remainingTrainCars = opponentThree.getRemainingTrainCars();
            int points = opponentThree.getPointsEarned();
            remainingTrainCars -= route.getLength();
            points += route.getPointValue();
            setChanged();
            notifyObservers(opponentThree);
            clearChanged();
        } else if (player.getUsername().equals(opponentFour.getUsername())) {
            int remainingTrainCars = opponentFour.getRemainingTrainCars();
            int points = opponentFour.getPointsEarned();
            remainingTrainCars -= route.getLength();
            points += route.getPointValue();
            setChanged();
            notifyObservers(opponentFour);
            clearChanged();
        }
    }

    /**
     * Cards
     */

    public void setFaceUpCards(List<TrainCard> faceUpCards) {
        this.faceUpCards = faceUpCards;
        gameActivityPresenter.setFaceUpCards(faceUpCards);
    }

    public void selectFaceUpCard(int index) {
        TrainCard card = gameActivityPresenter.getFaceUpCard(index);
        List<TrainCard> cards = client.getTrainCards();
        cards.add(card);
        client.setTrainCards(cards);
        ServerProxy.getInstance().getCard(gameId, Authentication.getInstance().getUsername(), index);
        setChanged();
        notifyObservers(client);
        clearChanged();
    }

    public void selectFaceDownCardDeck() {
        ServerProxy.getInstance().getCard(gameId, Authentication.getInstance().getUsername(), SELECT_FACE_DOWN_DECK);
    }

    public void setDeckCount(int ticketDeckCount, int trainDeckCount) {
        gameActivityPresenter.setDeckCount(ticketDeckCount, trainDeckCount);
    }

    public void updateFaceUpCards(List<TrainCard> trainCards) {
        this.faceUpCards = trainCards;
        gameActivityPresenter.setFaceUpCards(trainCards);
    }

    public void drewCard(TrainCard newCard) {
        client.addTrainCard(newCard);
        setChanged();
        notifyObservers(client);
        clearChanged();
    }

    public void updatePlayer(Player player) {
        playerTurnEnded(player);
    }

    public void selectingCards() {
        gameMapFragmentPresenter.selectingCards();
    }

    public void endGame() {
        gameActivityPresenter.endGame();
    }

    public void fatalError(String message) {
        gameActivityPresenter.fatalError();
    }
}
