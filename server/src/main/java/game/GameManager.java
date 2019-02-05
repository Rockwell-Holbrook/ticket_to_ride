package game;

import clientProxy.IClientNotInGame;
import com.example.shared.model.Game;
import com.example.shared.model.Player;

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

    private IClientNotInGame clientProxy = new IClientNotInGame() {
        @Override
        public void updateGameList(Game game) {

        }

        @Override
        public void joinGameComplete(String gameName, Player player) {

        }

        @Override
        public void startGameComplete() {

        }
    };

    void createGame(Player host, int maxPlayers, String gameName) {
        Game game = new Game(host, gameName);
        this.gameList.put(gameName, game);
        clientProxy.updateGameList(game);
    }

    void joinGame(String gameName, Player player) {
        Game game = this.gameList.get(gameName);
        game.addPlayer(player);
        clientProxy.joinGameComplete(gameName, player);
    }

    void startGame(String gameName) {
        Game game = this.gameList.get(gameName);
        game.startGame();
        clientProxy.startGameComplete();
    }

    public Map<String, Game> getGameList() {
        return gameList;
    }
}
