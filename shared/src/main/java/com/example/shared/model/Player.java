package com.example.shared.model;

public class Player {
    private String username;
    private boolean isHost;

    public Player(String username, boolean isHost) {
        this.username = username;
        this.isHost = isHost;
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

    public enum PlayerColor {
        GREEN, BLUE, YELLOW, RED, BLACK;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}