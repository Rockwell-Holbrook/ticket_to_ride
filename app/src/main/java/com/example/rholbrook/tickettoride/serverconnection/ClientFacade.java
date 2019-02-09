package com.example.rholbrook.tickettoride.serverconnection;

import com.example.rholbrook.tickettoride.gamelobby.GameLobbyActivityModel;
import com.example.rholbrook.tickettoride.main.MainActivityModel;
import com.example.shared.interfaces.IClientInGame;
import com.example.shared.interfaces.IClientNotInGame;
import com.example.shared.model.Game;
import com.example.shared.model.Player;
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
    public void chatReceived(String username, String message) {
        GameLobbyActivityModel.getInstance().newMessageReceived(username, message);
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



//    Phase 2 Connections
    @Override
    public void cardDrawn() {

    }

    @Override
    public void routeClaimed() {

    }

    @Override
    public void ticketsDrawn() {

    }

    @Override
    public void ticketsReturned() {

    }
}
