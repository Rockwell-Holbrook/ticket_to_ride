package com.example.rholbrook.tickettoride;

import com.google.gson.Gson;

public class ServerProxy {
    private static ServerProxy instance;

    public ServerProxy() { }

    public static synchronized ServerProxy getInstance() {
        if (instance == null) {
            instance = new ServerProxy();
        }
        return instance;
    }
}
