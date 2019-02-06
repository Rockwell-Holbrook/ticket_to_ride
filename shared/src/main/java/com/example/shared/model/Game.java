package com.example.shared.model;


import com.example.shared.interfaces.IClientInGame;
import com.example.shared.interfaces.IClientNotInGame;

import java.util.*;

public class Game {
    private String gameId;
    private boolean isPlaying;
    private Player host;
    private Set<Player> playerList = new HashSet<>();
    private int maxPlayers;
    private int currentPlayers;
    private String gameName;
    private IClientInGame clientProxy;
    private List<Player.PlayerColor> availableColors;

    public Game(Player host, int maxPlayers, String gameName) {
        this.host = host;
        this.gameName = gameName;
        this.isPlaying = false;
        this.maxPlayers = maxPlayers;
        this.currentPlayers = 0;
        this.gameId = UUID.randomUUID().toString();
        newAvailableColorsSet();
        addPlayer(host);
    }

    public void newAvailableColorsSet() {
        availableColors = new ArrayList<>();
        availableColors.add(Player.PlayerColor.BLUE);
        availableColors.add(Player.PlayerColor.BLACK);
        availableColors.add(Player.PlayerColor.GREEN);
        availableColors.add(Player.PlayerColor.RED);
        availableColors.add(Player.PlayerColor.YELLOW);
    }

    public void addPlayer(Player player) {
        playerList.add(player);
        currentPlayers++;
    }

    public void updateAvailableColors(Player player) {
        availableColors.remove(player.getColor());
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

    public List<Player.PlayerColor> getAvailableColors() {
        return availableColors;
    }

    public void setAvailableColors(List<Player.PlayerColor> availableColors) {
        this.availableColors = availableColors;
    }
}