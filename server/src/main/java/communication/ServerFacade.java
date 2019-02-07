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

    private ServerFacade() {}

    /**
     * Creates the game and adds it to the gameList and to the socketServer using gameID as the key.
     *
     * @param host The Player hosting the game. This player will be set to host upon creation.
     * @param maxPlayers The max number of players this game needs before it can start. An int between 2-5.
     * @param gameName The name of the game.
     */
    @Override
    public void createGame(Player host, int maxPlayers, String gameName) {
        String gameId = gameManager.createGame(host, maxPlayers, gameName);
        gameManager.joinGame(gameId, host);
    }

    /**
     * The provided player will be added to the game specified by the gameId.
     *
     * @param gameId The ID of the game trying to be joined.
     * @param player The Player that wants to join the game.
     */
    @Override
    public void joinGame(String gameId, Player player) {
        gameManager.joinGame(gameId, player);
    }

    /**
     * Starts the game specified by the gameID.
     *
     * @param gameId The ID of the game that needs to be started.
     */
    @Override
    public void startGame(String gameId) {
        gameManager.startGame(gameId);
    }

    @Override
    public void sendChat(String username, String gameId, String message) {
        // Todo: Make this sucker work baby.
    }
}
