package com.example.shared.model;

public class Player {
    private String username;
    private boolean isHost;
    private PlayerColor color;

    public Player(String username, boolean isHost, PlayerColor color) {
        this.username = username;
        this.isHost = isHost;
        this.color = color;
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

    public PlayerColor getColor() {
        return color;
    }

    public void setColor(PlayerColor color) {
        this.color = color;
    }

    public enum PlayerColor {
        GREEN, BLUE, YELLOW, RED, BLACK;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}