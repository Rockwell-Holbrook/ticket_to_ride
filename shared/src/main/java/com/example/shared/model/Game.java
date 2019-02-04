package com.example.shared.model;


import com.example.shared.interfaces.IClientInGame;
import communication.ClientProxy;

import java.util.*;

public class Game {
    private String gameId;
    private boolean isPlaying;
    private Player host;
    private Set<Player> playerList = new HashSet<>();
    private int maxPlayers;
    private int currentPlayers;
    private String gameName;

    public Game(Player host, String gameName) {
        this.host = host;
        this.gameName = gameName;
        this.isPlaying = false;
        this.maxPlayers = 5;
        this.currentPlayers = 0;
        this.gameId = UUID.randomUUID().toString();

        addPlayer(host);
    }

    IClientInGame clientProxy = new ClientProxy();

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
}