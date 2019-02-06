package com.example.server;

import authentication.AuthenticationServer;
import communication.SocketServer;

public class Server {
    public static void main(String[] args) {
        AuthenticationServer.getInstance().run("8080");
        SocketServer.getInstance().start();
    }
}
