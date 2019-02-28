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

    @Override
    public void receivedChat(Chat chat, boolean gameStarted) {
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

    @Override
    public void receivedChatHistory(List<Chat> chatHistory) {
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

    @Override
    public void initializeGame(List<TrainCard> trainCards, List<Ticket> tickets, List<Player> turnOrder) {

    }

    @Override
    public void ticketsReceived(List<Ticket> tickets) {

    }

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
