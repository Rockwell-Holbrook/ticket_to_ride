package com.example.rholbrook.tickettoride.authentication;

public class AuthenticationActivityModel {
    private static AuthenticationActivityModel instance;


    private AuthenticationActivityModel() {

    }

    public interface CallBack<T> {
        void onCall(int key);
    }

    public static AuthenticationActivityModel getInstance() {
        if (instance == null) {
            instance = new AuthenticationActivityModel();
        }
        return instance;
    }
}
