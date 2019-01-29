package com.example.rholbrook.tickettoride;

public class ServerProxy {
    private static ServerProxy instance;
    private String url;

    public ServerProxy() {

    }

    public static synchronized ServerProxy getInstance() {
        if (instance == null) {
            instance = new ServerProxy();
        }
        return instance;
    }
}
