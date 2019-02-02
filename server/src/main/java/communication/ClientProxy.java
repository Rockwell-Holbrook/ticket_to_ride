package communication;

import com.example.shared.interfaces.IClientInGame;
import com.example.shared.interfaces.IClientNotInGame;
import com.example.shared.model.Game;
import com.example.shared.model.Player;

import java.util.List;

public class ClientProxy implements IClientInGame, IClientNotInGame {
    @Override
    public void chatReceived(String username, String message) {

    }

    @Override
    public void playerJoinedGame(String username, Player.PlayerColor color) {

    }

    @Override
    public void cardDrawn() {

    }

    @Override
    public void routeClaimed() {

    }

    @Override
    public void ticketsDrawn() {

    }

    @Override
    public void ticketsReturned() {

    }

    @Override
    public void updateGameList(List<Game> games) {

    }

    @Override
    public void joinGameComplete(String gameId) {

    }
}
