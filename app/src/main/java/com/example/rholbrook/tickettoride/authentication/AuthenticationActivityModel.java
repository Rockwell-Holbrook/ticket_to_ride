package com.example.rholbrook.tickettoride.authentication;

public class AuthenticationActivityModel {
    private static AuthenticationActivityModel instance;

    public interface CallBack<T> {
        void onCall(int key);
    }

    private AuthenticationActivityModel() {

    }

    public static AuthenticationActivityModel getInstance() {
        if (instance == null) {
            instance = new AuthenticationActivityModel();
        }
        return instance;
    }
}
