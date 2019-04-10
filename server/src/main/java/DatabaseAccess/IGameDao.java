package DatabaseAccess;

import com.example.shared.commands.Command;
import com.example.shared.model.Game;

import java.util.List;

public interface IGameDao {
    void saveGame(Game game);
    void saveDelta(Command delta);
    Game getGame(String gameid);
    List<Command> getDeltas(String gameid);
    void clear();
}
