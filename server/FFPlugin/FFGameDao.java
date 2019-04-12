package FFPlugin;

import com.example.shared.commands.Command;
import com.example.shared.model.Game;
import database.IGameDao;

import java.util.List;

public class FFGameDao implements IGameDao {

    @Override
    public void saveGame(Game game) {
        // is game in filesystem?
        // if yes, replace
        // if no, add
    }

    @Override
    public void saveDelta(String gameid, Command delta) {

    }

    @Override
    public Game getGame(String gameid) {
        return null;
    }

    @Override
    public List<Command> getDeltas(String gameid) {
        return null;
    }

    @Override
    public void clear() {

    }
}
