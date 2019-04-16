package com.example.server;

import authentication.AuthenticationServer;
import communication.SocketServer;
import database.PluginManager;

public class Server {
    /**
     *
     * Main function that starts both the Auth and the Socket Server.
     */
    public static void main(String[] args) {
        String portNumber = args[0];
        PluginManager pluginManager = PluginManager.getInstance();
        pluginManager.setPluginDirectory(args[1]);
        pluginManager.setPluginJarName(args[2]);
        pluginManager.setPluginClassName(args[3]);
        AuthenticationServer.getInstance().run(portNumber);
        SocketServer.getInstance().start();
        int deltaVal;
        try {
            deltaVal = Integer.parseInt(args[4]);
        }
        catch (Exception e){
            deltaVal = 10;
        }
        SocketServer.getInstance().setDeltaVal(deltaVal);
        SocketServer.getInstance().recover();
    }
}
