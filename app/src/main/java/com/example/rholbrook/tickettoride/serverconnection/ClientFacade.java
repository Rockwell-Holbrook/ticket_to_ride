package com.example.rholbrook.tickettoride.serverconnection;

import android.util.Log;
import com.example.rholbrook.tickettoride.game.GameActivity;
import com.example.rholbrook.tickettoride.game.GameActivityModel;
import com.example.rholbrook.tickettoride.game.GameActivityPresenter;
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
    private final String TAG = "ticket_to_ride";

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

    @Override
    public void receivedChat(Chat chat, boolean gameStarted, String gameId) {
        Log.d(TAG, "Client Facade: in receivedChat");
        if (gameStarted) {
            GameActivityModel.getInstance().receivedChat(chat);
        } else {
            GameLobbyActivityModel.getInstance().receivedChat(chat);
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
    }

    //History Drawer
    @Override
    public void receivedChatHistory(List<Chat> chatHistory, boolean gameStarted, String username, String gameId) {
        GameActivityModel.getInstance().receivedChatHistory(chatHistory);
    }

    @Override
    public void receivedHistoryObject(GameHistory history) {
        GameActivityModel.getInstance().receivedHistoryObject(history);
    }

    @Override
    public void receivedGameHistory(List<GameHistory> gameHistory) {
        GameActivityModel.getInstance().receivedGameHistory(gameHistory);
    }

    //Game Initialization
    @Override
    public void initializeGame(List<TrainCard> trainCardsFaceUp, List<TrainCard> trainCards, List<Ticket> tickets, List<Player> turnOrder, String username, String gameId) {
        //Fix Face Up Train Card List
        String typeValue = gson.toJson(trainCardsFaceUp);
        Type typeName = new TypeToken<List<TrainCard>>(){}.getType();
        List<TrainCard> trainCardsFaceUpList = gson.fromJson(typeValue, typeName);

        //Fix Train Cards List
        typeValue = gson.toJson(trainCards);
        typeName = new TypeToken<List<TrainCard>>(){}.getType();
        List<TrainCard> trainCardsList = gson.fromJson(typeValue, typeName);

        //Fix ticket List
        typeValue = gson.toJson(tickets);
        typeName = new TypeToken<List<Ticket>>(){}.getType();
        List<Ticket> ticketList = gson.fromJson(typeValue, typeName);

        //Fix Turn Order
        typeValue = gson.toJson(turnOrder);
        typeName = new TypeToken<List<Player>>(){}.getType();
        List<Player> newTurnOrder = gson.fromJson(typeValue, typeName);

        GameActivityModel.getInstance().initializeGame(trainCardsFaceUpList, trainCardsList, ticketList, newTurnOrder);
    }

    @Override
    public void initializeComplete(String gameId, String username) {

    }

    @Override
    public void ticketsReceived(List<Ticket> tickets) {
        GameActivityModel.getInstance().ticketDataReceived(tickets);
    }

    //GamePlay
    @Override
    public void startTurn(List<Route> availableRoutes, String username) {
        GameActivityModel.getInstance().startTurn(availableRoutes);
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
        GameActivityModel.getInstance().playerTurnEnded(player);
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
