package com.example.shared.model;


import com.example.shared.interfaces.IClientInGame;
import com.example.shared.interfaces.IClientNotInGame;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Game {
    private String gameId;
    private boolean isPlaying;
    private Player host;
    private Set<Player> playerList = new HashSet<>();
    private int maxPlayers;
    private int currentPlayers;
    private String gameName;
    private IClientInGame clientProxy;

    public Game(Player host, int maxPlayers, String gameName) {
        this.host = host;
        this.gameName = gameName;
        this.isPlaying = false;
        this.maxPlayers = maxPlayers;
        this.currentPlayers = 0;
        this.gameId = UUID.randomUUID().toString();

        addPlayer(host);
    }



    public void addPlayer(Player player) {
        playerList.add(player);
        currentPlayers++;
    }

    public void startGame() {
        isPlaying = true;
    }

    /* *********** GETTERS AND SETTERS *********** */

    public String getGameId() {
        return gameId;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public Player getHost() {
        return host;
    }

    public Set<Player> getPlayerList() {
        return playerList;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getCurrentPlayers() {
        return currentPlayers;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setClientProxy(IClientInGame clientProxy) {this.clientProxy = clientProxy;}
}