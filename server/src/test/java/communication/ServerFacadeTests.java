package communication;

import com.example.shared.commands.Command;
import com.example.shared.model.Game;
import com.example.shared.model.Player;
import com.google.gson.Gson;
import game.GameManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ServerFacadeTests {
    @Test
    public void canCreateGame(){
        ServerFacade sf = ServerFacade.getInstance();
        GameManager gm = GameManager.getInstance();

        sf.createGame(new Player("Taylor", true, Player.PlayerColor.BLUE), 5, "My game");

        System.out.println(gm.getNotPlayingGameList());


    }

    @Test
    public void gsonTest(){
        Gson gson = new Gson();
        String methodName = "updateGameList";

        String[] typeNames = {List.class.getName()};

        List<Game> games = new ArrayList<>();
        games.add(new Game(new Player("Taylor", true, Player.PlayerColor.BLUE), 5, "my game"));
        Object[] inputVals = {games};

        String json = gson.toJson(new Command(methodName, typeNames, inputVals));
        Command cmd = gson.fromJson(json, Command.class);
        System.out.println(cmd.toString());
    }
}
