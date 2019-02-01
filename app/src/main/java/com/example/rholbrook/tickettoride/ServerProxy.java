package com.example.rholbrook.tickettoride;

import com.google.gson.Gson;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class ServerProxy {
    private static ServerProxy instance;

    public ServerProxy() { }

    public static synchronized ServerProxy getInstance() {
        if (instance == null) {
            instance = new ServerProxy();
        }
        return instance;
    }

    SocketClientCommunicator cc = new SocketClientCommunicator( new URI( "ws://localhost:8990/management?user=taylor" )) {
        @Override
        public void onMessage( String message ) {
            System.out.println( "got: " + message + "\n" );
        }

        @Override
        public void onOpen( ServerHandshake handshake ) {
            System.out.println( "You are connected to ChatServer: " + getURI() + "\n" );
        }

        @Override
        public void onClose( int code, String reason, boolean remote ) {
            System.out.println( "You have been disconnected from: " + getURI() + "; Code: " + code + " " + reason + "\n" );
        }

        @Override
        public void onError( Exception ex ) {
            System.out.println( "Exception occured ...\n" + ex + "\n" );
        }

    };
    cc.connect();
}
