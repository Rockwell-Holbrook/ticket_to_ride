package com.example.rholbrook.tickettoride.serverconnection;

import com.example.rholbrook.tickettoride.game.GameActivity;
import com.example.rholbrook.tickettoride.game.GameActivityModel;
import com.example.rholbrook.tickettoride.gamelobby.GameLobbyActivityModel;
import com.example.rholbrook.tickettoride.main.MainActivityModel;
import com.example.shared.interfaces.IClientInGame;
import com.example.shared.interfaces.IClientNotInGame;
import com.example.shared.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.*;

public class ClientFacade implements IClientInGame, IClientNotInGame {
    private static ClientFacade instance;
    private static Gson gson = new Gson();

    public ClientFacade() {}

    public static synchronized ClientFacade getInstance() {
        if (instance == null) {
            instance = new ClientFacade();
        }
        return instance;
    }

//    Phase 1 Connections
//    GameLobby
    public void chatReceived(String username, String message) {
        GameLobbyActivityModel.getInstance().newMessageReceived(username, message);
    }

    @Override
    public void receivedChat(Chat chat, boolean gameStarted, String gameId) {
        if (gameStarted) {
            GameLobbyActivityModel.getInstance().newMessageReceived(chat.getUsername(), chat.getMessage());
        } else {
            GameActivityModel.getInstance().newMessageReceived(chat.getUsername(), chat.getMessage());
        }
    }

    @Override
    public void playerJoinedGame(Set<Player> playerList, String gameId) {
        String jsonValue = gson.toJson(playerList);
        Type typeName = new TypeToken<Set<Player>>(){}.getType();
        Set<Player> players = gson.fromJson(jsonValue, typeName);
        GameLobbyActivityModel.getInstance().newPlayerJoined(players);
    }

    @Override
    public void gameStarted(String gameId) {
        GameLobbyActivityModel.getInstance().gameStarted();
        LocomotiveCard cardOne = new LocomotiveCard();
        ColorCard cardTwo = new ColorCard(ColorCard.Color.BLACK);
        ColorCard cardThree = new ColorCard(ColorCard.Color.BLUE);
        ColorCard cardFour = new ColorCard(ColorCard.Color.RED);
        List<TrainCard> trainCards = new ArrayList<>();
        trainCards.add(cardOne);
        trainCards.add(cardTwo);
        trainCards.add(cardThree);
        trainCards.add(cardFour);
        Ticket ticketOne = new Ticket();
        Ticket ticketTwo = new Ticket();
        Ticket ticketThree = new Ticket();
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticketOne);
        tickets.add(ticketTwo);
        tickets.add(ticketThree);
        Player playerOne = new Player("Player", false, Player.PlayerColor.BLUE);
        Player playerTwo = new Player("Hello", false, Player.PlayerColor.GREEN);
        Player playerThree = new Player("World", true, Player.PlayerColor.BLACK);
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        players.add(playerThree);
        initializeGame(trainCards, tickets, players);
        List<TrainCard> faceUpCards = new ArrayList<>();
        faceUpCards.add(cardOne);
        faceUpCards.add(cardTwo);
        faceUpCards.add(cardThree);
        faceUpCards.add(cardFour);
        faceUpCards.add(cardThree);
        cardDrawn(faceUpCards);
    }

    //History Drawer
    @Override
    public void receivedChatHistory(List<Chat> chatHistory) {

    }

    @Override
    public void receivedHistoryObject(GameHistory history) {

    }

    @Override
    public void receivedGameHistory(List<GameHistory> gameHistory) {

    }

    //Game Initialization
    @Override
    public void initializeGame(List<TrainCard> trainCards, List<Ticket> tickets, List<Player> turnOrder) {
        GameActivityModel.getInstance().initializeGame(trainCards, tickets, turnOrder);
    }

    @Override
    public void ticketsReceived(List<Ticket> tickets) {
        GameActivityModel.getInstance().ticketDataReceived(tickets);
    }

    //GamePlay
    @Override
    public void startTurn(List<Route> availableRoutes) {

    }

    @Override
    public void ticketCompleted(Ticket ticket) {

    }

    @Override
    public void routeClaimed(Player player, Route route) {

    }

    @Override
    public void cardDrawn(List<TrainCard> faceUpCards) {
        GameActivityModel.getInstance().setFaceUpCards(faceUpCards);
    }

    @Override
    public void turnEnded(Player player) {

    }

    //    MainActivity
    @Override
    public void updateGameList(ArrayList<Game> games) {
        String jsonValue = gson.toJson(games);
        Type typeName = new TypeToken<ArrayList<Game>>(){}.getType();
        ArrayList<Game> gameList = gson.fromJson(jsonValue, typeName);
        MainActivityModel.getInstance().newGameListRetrieved(gameList);
    }

    @Override
    public void joinGameComplete(String username, String gameId) {
        MainActivityModel.getInstance().joinedGame(gameId);
    }

}
