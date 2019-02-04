package com.example.rholbrook.tickettoride.serverconnection;

import com.example.rholbrook.tickettoride.main.MainActivityModel;
import com.example.shared.interfaces.IClientInGame;
import com.example.shared.interfaces.IClientNotInGame;
import com.example.shared.model.Game;
import com.example.shared.model.Player;

import java.util.List;
import java.util.UUID;

public class ClientFacade implements IClientInGame, IClientNotInGame {
    private static ClientFacade instance;

    public ClientFacade() {}

    public static synchronized ClientFacade getInstance() {
        if (instance == null) {
            instance = new ClientFacade();
        }
        return instance;
    }

    @Override
    public void chatReceived(String username, String message) {

    }

    @Override
    public void playerJoinedGame(String username, Player.PlayerColor color) {

    }

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

    @Override
    public void updateGameList(List<Game> games) {

    }

    @Override
    public void joinGameComplete(String gameId){
    }
}
