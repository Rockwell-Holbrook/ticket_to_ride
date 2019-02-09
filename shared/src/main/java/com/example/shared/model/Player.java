package com.example.shared.model;

public class Player {
    private String username;
    private boolean isHost;
    private PlayerColor playerColor;

    public Player(String username, boolean isHost, PlayerColor playerColor) {
        this.username = username;
        this.isHost = isHost;
        this.playerColor = playerColor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isHost() {
        return isHost;
    }

    public void setHost(boolean host) {
        isHost = host;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setColor(PlayerColor color) {
        this.playerColor = color;
    }

    public enum PlayerColor {
        GREEN, BLUE, YELLOW, RED, BLACK;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass())
            return false;

        try {
            Player p = (Player) o;
            if(!p.getUsername().equals(this.username)) {
                return false;
            }
            if(!p.getPlayerColor().equals(this.playerColor)) {
                return false;
            }
        }
        catch(Exception e){
            return false;
        }

        return true;
    }
}