package game;

import com.example.shared.interfaces.IClientNotInGame;
import com.example.shared.model.Game;
import com.example.shared.model.Player;
import communication.ClientProxy;
import communication.SocketServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameManager {
    private static final GameManager instance = new GameManager();

    private GameManager(){}

    public static GameManager getInstance(){
        return instance;
    }

    private Map<String, Game> gameList = new HashMap<>();

    private IClientNotInGame clientProxy = new ClientProxy();

    /**
     * Creates the game and adds it to the gameList and to the socketServer using gameID as the key.
     *
     * @param host The Player hosting the game. This player will be set to host upon creation.
     * @param maxPlayers The max number of players this game needs before it can start. An int between 2-5.
     * @param gameName The name of the game.
     */
    public void createGame(Player host, int maxPlayers, String gameName) {
        Game game = new Game(host, maxPlayers, gameName);
        game.setClientProxy(new ClientProxy(game.getGameId()));
        this.gameList.put(gameName, game);
        SocketServer.getInstance().addGame(game.getGameId());
        clientProxy.updateGameList(new ArrayList<>(gameList.values()));
    }

    /**
     * Starts the game specified by the gameID.
     *
     * @param gameId The ID of the game that needs to be started.
     */
    public void joinGame(String gameId, Player player) {
        Game game = this.gameList.get(gameId);
        game.addPlayer(player);
        clientProxy.joinGameComplete(player.getUsername(), gameId);
    }

    /**
     *
     * @param gameId The ID of the game that needs to be started.
     *
     * Starts the game specified by the gameID.
     */
    public void startGame(String gameId) {
        Game game = this.gameList.get(gameId);
        game.startGame();
    }

    public Map<String, Game> getGameList() {
        return gameList;
    }
}
