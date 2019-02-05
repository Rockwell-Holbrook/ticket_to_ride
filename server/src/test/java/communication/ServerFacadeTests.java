package communication;

import com.example.shared.model.Player;
import game.GameManager;
import org.junit.Test;

public class ServerFacadeTests {
    @Test
    public void canCreateGame(){
        ServerFacade sf = ServerFacade.getInstance();
        GameManager gm = GameManager.getInstance();

        sf.createGame(new Player("Taylor", true), 5, "My game");

        System.out.println(gm.getGameList());


    }
}
