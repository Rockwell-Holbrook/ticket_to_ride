package communication;

import com.example.shared.interfaces.IServer;

public class ServerFacade implements IServer {
    private static final ServerFacade ourInstance = new ServerFacade();

    public static ServerFacade getInstance() {
        return ourInstance;
    }

    private ServerFacade() {
    }

    @Override
    public void createGame(String username) {

    }

    @Override
    public void joinGame(String username, String gameId) {

    }

    @Override
    public void startGame(String gameId) {

    }

    @Override
    public void sendChat(String username, String gameId, String message) {

    }
}
