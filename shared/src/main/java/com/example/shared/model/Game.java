package com.example.shared.model;

import clientProxy.IClientIsInGame;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

public class Game {
    private UUID gameId;
    private boolean isPlaying;
    private Player host;
    private Set<Player> playerList = new TreeSet<>();
    private int maxPlayers;
    private int currentPlayers;
    private String gameName;

    public Game(Player host, String gameName) {
        this.host = host;
        this.gameName = gameName;
        this.isPlaying = false;
        this.maxPlayers = 5;
        this.currentPlayers = 0;
        this.gameId = UUID.randomUUID();

        addPlayer(host);
    }

    IClientIsInGame clientProxy = new IClientIsInGame() {

        @Override
        public void chatReceived() {

        }

        @Override
        public void cardsDrawn() {

        }

        @Override
        public void routeCliamed() {

        }

        @Override
        public void ticketsDrawn() {

        }

        @Override
        public void ticketsReturned() {

        }
    };

    public void addPlayer(Player player) {
        playerList.add(player);
        currentPlayers++;
    }

    public void startGame() {
        isPlaying = true;
    }

    /* *********** GETTERS AND SETTERS *********** */

    public UUID getGameId() {
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