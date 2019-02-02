package com.example.shared.model;

import java.util.UUID;

public class Game {
    private UUID gameId;
    private boolean isPlaying;
    private Player host;
    private Player[] playerList;
    private int maxPlayers;
    private int currentPlayers;
    private String gameName;

    public Game(Player host, int maxPlayers, int currentPlayers, String gameName) {
        this.host = host;
        this.maxPlayers = maxPlayers;
        this.currentPlayers = currentPlayers;
        this.gameName = gameName;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public Player getHost() {
        return host;
    }

    public void setHost(Player host) {
        this.host = host;
    }

    public Player[] getPlayerList() {
        return playerList;
    }

    public void setPlayerList(Player[] playerList) {
        this.playerList = playerList;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getCurrentPlayers() {
        return currentPlayers;
    }

    public void setCurrentPlayers(int currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}