package communication;

import com.example.shared.model.Game;
import com.example.shared.model.Player;
import org.junit.Test;

public class ModelTests {
    @Test
    public void playerDuplicatesNotAllowedInPlayerList() {
        Player one = new Player("duplicate", false, Player.PlayerColor.BLUE);
        Player two = new Player("duplicate", false, Player.PlayerColor.BLUE);
        Player three = new Player("notDuplicate", false, Player.PlayerColor.BLUE);

        Game game = new Game(one, 5, "GameName");
        game.addPlayer(two);
        assert(game.getPlayerList().size() == 1);

        game.addPlayer(three);
        assert(game.getPlayerList().size() == 2);
    }
}
