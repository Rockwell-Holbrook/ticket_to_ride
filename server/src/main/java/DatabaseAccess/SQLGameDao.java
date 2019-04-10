package DatabaseAccess;

import com.example.shared.commands.Command;
import com.example.shared.model.Game;

import java.util.List;

public class SQLGameDao implements IGameDao {
    @Override
    public void saveGame(Game game) {

    }

    @Override
    public void saveDelta(Command delta) {

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
