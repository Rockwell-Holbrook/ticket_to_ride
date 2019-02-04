package com.example.rholbrook.tickettoride.main;

import com.example.shared.model.User;

public class Authentication {
    private User user;
    private static Authentication instance;

    public static Authentication getInstance() {
        if (instance == null) {
            instance = new Authentication();
        }
        return instance;
    }

    public Authentication() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
