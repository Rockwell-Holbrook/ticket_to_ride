package communication;

import com.example.shared.commands.Command;
import com.example.shared.interfaces.IClientInGame;
import com.example.shared.interfaces.IClientNotInGame;
import com.example.shared.model.Game;
import com.example.shared.model.Player;

import java.util.List;

public class ClientProxy implements IClientInGame, IClientNotInGame {
    private String gameId;
    private SocketServer ss;

    public ClientProxy(){
        gameId = null;
        ss = SocketServer.getInstance();
    }

    public ClientProxy(String gameId){
        this.gameId = gameId;
        ss = SocketServer.getInstance();
    }

    @Override
    public void chatReceived(String username, String message) {
        String methodName = "chatRecieved";
        String[] typeNames = {String.class.getName(), String.class.getName()};
        Object[] inputVals = {username, message};

        ss.broadcastToGame(new Command(methodName, typeNames, inputVals), gameId);
    }

    @Override
    public void playerJoinedGame(String username, Player.PlayerColor color) {
        String methodName = "playerJoinedGame";
        String[] typeNames = {String.class.getName(), Player.PlayerColor.class.getName()};
        Object[] inputVals = {username, color};

        ss.broadcastToGame(new Command(methodName, typeNames, inputVals), gameId);
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
        String methodName = "updateGameList";
        String[] typeNames = {List.class.getName()};
        Object[] inputVals = {games};

        ss.broadcastToManagement(new Command(methodName, typeNames, inputVals));
    }

    @Override
    public void joinGameComplete(String username, String gameId) {
        String methodName = "joinGameComplete";
        String[] typeNames = {String.class.getName(), String.class.getName()};
        Object[] inputVals = {username, gameId};

        ss.sendToUser(new Command(methodName, typeNames, inputVals), username);
    }
}
