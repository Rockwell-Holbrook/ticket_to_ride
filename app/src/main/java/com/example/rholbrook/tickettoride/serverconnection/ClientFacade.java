package com.example.rholbrook.tickettoride.serverconnection;

import com.example.rholbrook.tickettoride.gamelobby.GameLobbyActivityModel;
import com.example.rholbrook.tickettoride.main.MainActivityModel;
import com.example.shared.interfaces.IClientInGame;
import com.example.shared.interfaces.IClientNotInGame;
import com.example.shared.model.Game;
import com.example.shared.model.Player;

import java.util.*;

public class ClientFacade implements IClientInGame, IClientNotInGame {
    private static ClientFacade instance;

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
    public void playerJoinedGame(String username, Player.PlayerColor color, Set<Player> playerList) {
        GameLobbyActivityModel.getInstance().newPlayerJoined(playerList);
    }

    @Override
    public void gameStarted(String gameId) {
        GameLobbyActivityModel.getInstance().gameStarted();
    }

//    MainActivity
    @Override
    public void updateGameList(ArrayList<Game> games) {
        MainActivityModel.getInstance().newGameListRetrieved(games);
    }

    @Override
    public void joinGameComplete(String gameId, String string) {
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
