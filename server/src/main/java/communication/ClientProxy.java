package communication;

import com.example.shared.commands.Command;
import com.example.shared.interfaces.IClientInGame;
import com.example.shared.interfaces.IClientNotInGame;
import com.example.shared.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClientProxy implements IClientInGame, IClientNotInGame {
    private String gameId;
    private SocketServer ss;

    public ClientProxy() {
        gameId = null;
        ss = SocketServer.getInstance();
    }

    public ClientProxy(String gameId) {
        this.gameId = gameId;
        ss = SocketServer.getInstance();
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
     *
     * @param games    List of games
     * @param username User to send cmd to
     */
    public void updateGameList(ArrayList<Game> games, String username) {
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
    public void receivedChat(Chat chat, boolean gameStarted, String gameId) {
        String methodName = "receivedChat";
        String[] typeNames = {Chat.class.getName(), boolean.class.getName(), String.class.getName()};
        Object[] inputVals = {chat, gameStarted, gameId};

        ss.broadcastToGame(new Command(methodName, typeNames, inputVals), gameId);
    }

    @Override
    public void receivedChatHistory(List<Chat> chatHistory, boolean gameStarted, String username, String gameId) {
        String methodName = "receivedChatHistory";
        String[] typeNames = {List.class.getName(), boolean.class.getName(), String.class.getName(), String.class.getName()};
        Object[] inputVals = {chatHistory, gameStarted, username, gameId};

        ss.sendToUser(new Command(methodName, typeNames, inputVals), username, gameId);
    }

    @Override
    public void receivedGameHistory(List<GameHistory> gameHistory, String username, String gameId) {
        String methodName = "receivedGameHistory";
        String[] typeNames = {List.class.getName(), String.class.getName(), String.class.getName()};
        Object[] inputVals = {gameHistory, username, gameId};

        ss.sendToUser(new Command(methodName, typeNames, inputVals), username, gameId);
    }

    public void initializeGame(List<TrainCard> trainCardsFaceUp, List<TrainCard> trainCards, List<Ticket> tickets, List<Player> turnOrder, String username, String gameId) {
        String methodName = "initializeGame";
        String[] typeNames = {List.class.getName(), List.class.getName(), List.class.getName(), List.class.getName(), String.class.getName(), String.class.getName()};
        Object[] inputVals = {trainCardsFaceUp, trainCards, tickets, turnOrder, username, gameId};

        ss.sendToUser(new Command(methodName, typeNames, inputVals), username, gameId);
    }

    @Override
    public void initializeComplete(Player player, String gameId) {
        String methodName = "initializeComplete";
        String[] typeNames = {Player.class.getName(), String.class.getName()};
        Object[] inputVals = {player, gameId};

        ss.broadcastToGame(new Command(methodName, typeNames, inputVals), gameId);

    }

    @Override
    public void ticketsReceived(List<Ticket> tickets, String username, String gameId) {
        String methodName = "ticketsReceived";
        String[] typeNames = {List.class.getName(), String.class.getName(), String.class.getName()};
        Object[] inputVals = {tickets, username, gameId};

        ss.sendToUser(new Command(methodName, typeNames, inputVals), username, gameId);
    }

    @Override
    public void startTurn(List<Route> availableRoutes, String username, String gameId) {
        String methodName = "startTurn";
        String[] typeNames = {List.class.getName(), String.class.getName(), String.class.getName()};
        Object[] inputVals = {availableRoutes, username, gameId};

        ss.sendToUser(new Command(methodName, typeNames, inputVals), username, gameId);
    }
    @Override
    public void turnStarted(Player player, String gameId) {
        String methodName = "turnStarted";
        String[] typeNames = {Player.class.getName(), String.class.getName()};
        Object[] inputVals = {player, gameId};

        ss.broadcastToGame(new Command(methodName, typeNames, inputVals), gameId);
    }


    @Override
    public void ticketCompleted(Ticket ticket) {

    }

    @Override
    public void routeClaimed(Player player, Route route) {
        String methodName = "routeClaimed";
        String[] typeNames = {Player.class.getName(), Route.class.getName()};
        Object[] inputVals = {player, route};

        ss.broadcastToGame(new Command(methodName, typeNames, inputVals), gameId);
    }

    @Override
    public void turnEnded(Player player) {
        String methodName = "turnEnded";
        String[] typeNames = {Player.class.getName()};
        Object[] inputVals = {player};

        ss.broadcastToGame(new Command(methodName, typeNames, inputVals), gameId);
    }

    @Override
    public void sendDeckCount(int ticketDeckCount, int trainDeckCount) {
        String methodName = "sendDeckCount";
        String[] typeNames = {int.class.getName(), int.class.getName()};
        Object[] inputVals = {ticketDeckCount, trainDeckCount};

        ss.broadcastToGame(new Command(methodName, typeNames, inputVals), gameId);
    }

    @Override
    public void updateFaceUpCards(List<TrainCard> newTrainCards) {
        String methodName = "updateFaceUpCards";
        String[] typeNames = {List.class.getName()};
        Object[] inputVals = {newTrainCards};

        ss.broadcastToGame(new Command(methodName, typeNames, inputVals), gameId);
    }

    @Override
    public void receiveFaceDownCard(TrainCard newCard, String username, String gameId) {
        String methodName = "receiveFaceDownCard";
        String[] typeNames = {TrainCard.class.getName(), String.class.getName(), String.class.getName()};
        Object[] inputVals = {newCard, username, gameId};

        ss.sendToUser(new Command(methodName, typeNames, inputVals), username, gameId);
    }

    @Override
    public void getClaimableRoutes(List<Route> claimableRoutes, String username, String gameId) {
        String methodName = "getClaimableRoutes";
        String[] typeNames = {List.class.getName(), String.class.getName(), String.class.getName()};
        Object[] inputVals = {claimableRoutes, username, gameId};

        ss.sendToUser(new Command(methodName, typeNames, inputVals), username, gameId);
    }

    @Override
    public void gameEnding(String gameId) {
        String methodName = "gameEnding";
        String[] typeNames = {String.class.getName()};
        Object[] inputVals = {gameId};

        ss.broadcastToGame(new Command(methodName, typeNames, inputVals), gameId);
    }

    @Override
    public void gameEnded(String gameId) {
        String methodName = "gameEnded";
        String[] typeNames = {String.class.getName()};
        Object[] inputVals = {gameId};

        ss.broadcastToGame(new Command(methodName, typeNames, inputVals), gameId);
    }
}
