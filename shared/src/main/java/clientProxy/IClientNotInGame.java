package clientProxy;


import com.example.shared.model.Game;
import com.example.shared.model.Player;

public interface IClientNotInGame {
    void updateGameList(Game game);

    void joinGameComplete(String gameName, Player player);

    void startGameComplete();
}
