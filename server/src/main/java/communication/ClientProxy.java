package communication;

import com.example.shared.commands.Command;
import com.example.shared.interfaces.IClientInGame;
import com.example.shared.interfaces.IClientNotInGame;
import com.example.shared.model.Game;
import com.example.shared.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public void updateGameList(ArrayList<Game> games) {
        String methodName = "updateGameList";
        String[] typeNames = {ArrayList.class.getName()};
        Object[] inputVals = {games};

        ss.broadcastToManagement(new Command(methodName, typeNames, inputVals));
    }

    /**
     * Send the command for just one user to update their game list
     * @param games List of games
     * @param username User to send cmd to
     */
    public void updateGameList(ArrayList<Game> games, String username){
        String methodName = "updateGameList";
        String[] typeNames = {ArrayList.class.getName()};
        Object[] inputVals = {games};

        ss.sendToUser(new Command(methodName, typeNames, inputVals), username);
    }

    @Override
    public void playerJoinedGame(Set<Player> playerList, String gameId) {
        String methodName = "playerJoinedGame";
        String[] typeNames = {Set.class.getName(), String.class.getName()};
        Object[] inputVals = {playerList, gameId};

        ss.broadcastToGame(new Command(methodName, typeNames, inputVals), gameId);
    }

    @Override
    public void joinGameComplete(String username, String gameId) {
        String methodName = "joinGameComplete";
        String[] typeNames = {String.class.getName(), String.class.getName()};
        Object[] inputVals = {username, gameId};

        ss.sendToUser(new Command(methodName, typeNames, inputVals), username);
    }

    @Override
    public void gameStarted(String gameId) {
        String methodName = "gameStarted";
        String[] typeNames = {String.class.getName()};
        Object[] inputVals = {gameId};

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
}
