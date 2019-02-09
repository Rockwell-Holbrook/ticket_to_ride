package game;

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

    private Map<String, Game> notPlayingGameList = new HashMap<>();
    private Map<String, Game> playingGameList = new HashMap<>();

    private ClientProxy clientProxy = new ClientProxy();

    /**
     * Creates the game and adds it to the notPlayingGameList and to the socketServer using gameID as the key.
     *
     * @param host The Player hosting the game. This player will be set to host upon creation.
     * @param maxPlayers The max number of players this game needs before it can start. An int between 2-5.
     * @param gameName The name of the game.
     */
    public String createGame(Player host, int maxPlayers, String gameName) {
        Game game = new Game(host, maxPlayers, gameName);
        game.setClientProxy(new ClientProxy(game.getGameId()));
        this.notPlayingGameList.put(game.getGameId(), game);
        SocketServer.getInstance().addGame(game.getGameId());
        clientProxy.updateGameList(new ArrayList<>(notPlayingGameList.values()));
//        joinGame(game.getGameId(), host);
        return game.getGameId();
    }

    /**
     * Starts the game specified by the gameID.
     *
     * @param gameId The ID of the game that needs to be started.
     */
    public void joinGame(String gameId, Player player) {
        Game game = this.notPlayingGameList.get(gameId);
        game.addPlayer(player);
        clientProxy.joinGameComplete(player.getUsername(), gameId);
        clientProxy.updateGameList(new ArrayList<>(notPlayingGameList.values()));
    }

    public void sendGameList(String username){
        clientProxy.updateGameList(new ArrayList<>(notPlayingGameList.values()), username);
    }

    /**
     *
     * @param gameId The ID of the game that needs to be started.
     *
     * Starts the game specified by the gameID.
     */
    public void startGame(String gameId) {
        Game game = this.notPlayingGameList.get(gameId);
        game.startGame();
        this.notPlayingGameList.remove(gameId);
        this.playingGameList.put(game.getGameId(), game);
        clientProxy.gameStarted(gameId);
        clientProxy.updateGameList(new ArrayList<>(notPlayingGameList.values()));
    }

    /**
     * Returns the player list for a specific game to update when someone new joins the lobby.
     *
     * @param gameId The game for the playerList we need.
     */
    public void getPlayerList(String gameId) {
        Game game = this.notPlayingGameList.get(gameId);
        clientProxy.playerJoinedGame(game.getPlayerList(), gameId);
    }

    public Map<String, Game> getNotPlayingGameList() {
        return notPlayingGameList;
    }
}
