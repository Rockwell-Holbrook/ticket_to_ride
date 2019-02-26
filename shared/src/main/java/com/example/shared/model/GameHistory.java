package com.example.shared.model;

public class GameHistory {
    private String username;
    private String history;

    GameHistory(String username, String history) {
        this.username = username;
        this.history = history;
    }

    public String getUsername() {
        return username;
    }

    public String getHistory() {
        return history;
    }
}
