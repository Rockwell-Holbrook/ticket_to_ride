package socket_server;

import javax.websocket.*;
import java.io.IOException;

/**
 * Socket server that sends and receives commands
 * TODO Implement methods for command pattern
 */

public abstract class SocketServer extends Endpoint {
    @Override
    public void onOpen(final Session session, EndpointConfig config) {
        System.out.println("Peer " + session.getId() + " connected");
        session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String message) {
                try {
                    session.getBasicRemote().sendText("Got message from " + session.getId() + "\n" + message);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("Peer " + session.getId() + " disconnected due to " + closeReason.getReasonPhrase());
    }

    @Override
    public void onError(Session session, Throwable error) {
        System.out.println("Error communicating with peer " + session.getId() + ". Detail: "+ error.getMessage());
    }
}
