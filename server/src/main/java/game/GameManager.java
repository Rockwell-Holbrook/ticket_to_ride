package game;

import com.example.shared.interfaces.IClientNotInGame;
import com.example.shared.model.Game;
import com.example.shared.model.Player;
import communication.ClientProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameManager {
    private static final GameManager instance = new GameManager();

    //private constructor to avoid client applications to use constructor
    private GameManager(){}

    public static GameManager getInstance(){
        return instance;
    }

    private Map<String, Game> gameList = new HashMap<>();

    private IClientNotInGame clientProxy = new ClientProxy();

    void createGame(Player host, int maxPlayers, String gameName) {
        Game game = new Game(host, gameName);
        this.gameList.put(gameName, game);
        clientProxy.updateGameList(new ArrayList<>(gameList.values()));
    }

    void joinGame(String gameName, Player player) {
        Game game = this.gameList.get(gameName);
        game.addPlayer(player);
        clientProxy.joinGameComplete(gameName);
    }

    void startGame(String gameName) {
        Game game = this.gameList.get(gameName);
        game.startGame();
        // TODO ClientProxy.startGame() of sorts
    }

    public Map<String, Game> getGameList() {
        return gameList;
    }
}
