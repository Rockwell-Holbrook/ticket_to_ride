package com.example.rholbrook.tickettoride.main;

import com.example.shared.model.User;

public class Authentication {
    private String username;
    private static Authentication instance;

    public static Authentication getInstance() {
        if (instance == null) {
            instance = new Authentication();
        }
        return instance;
    }

    public Authentication() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
