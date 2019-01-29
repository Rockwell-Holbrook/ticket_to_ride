package com.example.rholbrook.tickettoride;

public class AuthenticationServerProxy {
    private static AuthenticationServerProxy instance;
    private String url;

    public AuthenticationServerProxy() {

    }

    public static synchronized AuthenticationServerProxy getInstance() {
        if (instance == null) {
            instance = new AuthenticationServerProxy();
        }
        return instance;
    }
}
