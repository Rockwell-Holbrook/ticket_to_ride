package communication;

import com.example.shared.interfaces.IServer;
import com.example.shared.model.Player;
import game.GameManager;

public class        ServerFacade implements IServer {
    private static final ServerFacade ourInstance = new ServerFacade();
    private GameManager gameManager = GameManager.getInstance();

    public static ServerFacade getInstance() {
        return ourInstance;
    }

    private ServerFacade() {
    }

    @Override
    public void createGame(Player host, int maxPlayers, String gameName) {
        gameManager.createGame(host, maxPlayers, gameName);
    }

    @Override
    public void joinGame(String gameId, Player player) {
        gameManager.joinGame(gameId, player);
    }

    @Override
    public void startGame(String gameId) {
        gameManager.startGame(gameId);
    }

    @Override
    public void sendChat(String username, String gameId, String message) {
        // TODO
    }
}
