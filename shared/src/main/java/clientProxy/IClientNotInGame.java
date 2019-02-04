package clientProxy;

import model.Game;
import model.Player;

public interface IClientNotInGame {
    void updateGameList(Game game);

    void joinGameComplete(String gameName, Player player);

    void startGameComplete();
}
