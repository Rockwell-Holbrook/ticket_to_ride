package socket_server;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.Test;

import java.net.URI;

import static java.lang.Thread.sleep;

public class SocketServerTests {
    @Test
    public void canConnect(){
        try{
            SocketServer ss = new SocketServer(8990);

            WebSocketClient cc = new WebSocketClient( new URI( "ws://localhost:8990/management?user=taylor" )) {

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

            WebSocketClient cc2 = new WebSocketClient( new URI( "ws://localhost:8990/management?user=adam" )) {

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

            ss.start();
            sleep(1000 * 5);
            cc.connect();
            sleep(1000 * 5);
            cc2.connect();
            sleep(1000 * 5);
            cc.send("Hello friend");
            sleep(1000* 5);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
