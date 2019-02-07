package communication;

import com.example.shared.commands.Command;
import com.example.shared.model.Player;
import com.google.gson.Gson;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.Test;

import java.net.URI;
import java.util.List;

import static java.lang.Thread.sleep;

public class SocketServerTests {
    @Test
    public void canConnect(){
        try{
            SocketServer ss = SocketServer.getInstance();

            WebSocketClient cc = new WebSocketClient( new URI( "ws://localhost:7777/management?user=taylor" )) {

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

            WebSocketClient cc2 = new WebSocketClient( new URI( "ws://localhost:7777/management?user=adam" )) {

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


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void canCreateGame(){
        Gson gson = new Gson();
        try{
            SocketServer ss = SocketServer.getInstance();

            WebSocketClient cc = new WebSocketClient( new URI( "ws://localhost:7777/management?user=taylor" )) {

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

            WebSocketClient cc2 = new WebSocketClient( new URI( "ws://localhost:7777/management?user=adam" )) {

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

            String methodName = "createGame";
            String[] typeNames = {Player.class.getName(), int.class.getName(), String.class.getName()};
            Object[] inputVals = {new Player("taylor", true, Player.PlayerColor.BLUE), 5, "Taylor's game"};
            cc.send(gson.toJson(new Command(methodName, typeNames, inputVals)));

            sleep(1000 * 5);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
