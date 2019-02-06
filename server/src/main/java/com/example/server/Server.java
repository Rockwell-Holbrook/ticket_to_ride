package com.example.server;

import authentication.AuthenticationServer;
import communication.SocketServer;

public class Server {
    /**
     *
     * Main function that starts both the Auth and the Socket Server.
     */
    public static void main(String[] args) {
        String portNumber = args[0];
        AuthenticationServer.getInstance().run(portNumber);
        SocketServer.getInstance().start();
    }
}
