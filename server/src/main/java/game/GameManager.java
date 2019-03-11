package game;

import com.example.shared.model.*;
import communication.ClientProxy;
import communication.SocketServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameManager {
    private static final GameManager instance = new GameManager();

    private GameManager() {
    }

    public static GameManager getInstance() {
        return instance;
    }

    private Map<String, Game> notPlayingGameList = new HashMap<>();
    private Map<String, Game> playingGameList = new HashMap<>();

    private ClientProxy clientProxy = new ClientProxy();

    /**
     * Creates the game and adds it to the notPlayingGameList and to the socketServer using gameID as the key.
     *
     * @param host       The Player hosting the game. This player will be set to host upon creation.
     * @param maxPlayers The max number of players this game needs before it can start. An int between 2-5.
     * @param gameName   The name of the game.
     */
    public String createGame(Player host, int maxPlayers, String gameName) {
        Game game = new Game(host, maxPlayers, gameName);
        game.setClientProxy(new ClientProxy(game.getGameId()));
        this.notPlayingGameList.put(game.getGameId(), game);
        SocketServer.getInstance().addGame(game.getGameId());
        clientProxy.updateGameList(new ArrayList<>(notPlayingGameList.values()));
//        joinGame(game.getGameId(), host);
        return game.getGameId();
    }

    /**
     * Starts the game specified by the gameID.
     *
     * @param gameId The ID of the game that needs to be started.
     */
    public void joinGame(String gameId, Player player) {
        Game game = this.notPlayingGameList.get(gameId);

        if (game.getPlayerList().size() != game.getMaxPlayers()) {
            game.addPlayer(player);
            clientProxy.joinGameComplete(player.getUsername(), gameId);
            clientProxy.updateGameList(new ArrayList<>(notPlayingGameList.values()));
        }
    }

    public void sendGameList(String username) {
        clientProxy.updateGameList(new ArrayList<>(notPlayingGameList.values()), username);
    }

    /**
     * @param gameId The ID of the game that needs to be started.
     *               <p>
     *               Starts the game specified by the gameID.
     */
    public void startGame(String gameId) {
        Game game = this.notPlayingGameList.get(gameId);
        game.startGame();
        game.initializeTrainCardsFaceUp();
        this.notPlayingGameList.remove(gameId);
        this.playingGameList.put(game.getGameId(), game);
        clientProxy.gameStarted(gameId);
        clientProxy.updateGameList(new ArrayList<>(notPlayingGameList.values()));
        // todo: initialize game
    }

    /**
     * Returns the player list for a specific game to update when someone new joins the lobby.
     *
     * @param gameId The game for the playerList we need.
     */
    public void getPlayerList(String gameId) {
        Game game = this.notPlayingGameList.get(gameId);
        clientProxy.playerJoinedGame(game.getPlayerList(), gameId);
    }

    public Map<String, Game> getNotPlayingGameList() {
        return notPlayingGameList;
    }

    /**
     * Send a message to the chat lobby in an un-started game
     *
     * @param gameId Id of game lobby
     * @param chat   Message to send to all users and username in one
     */
    public void sendChat(Chat chat, String gameId, boolean gameStarted) {
        Game game;
        if (gameStarted) {
            game = this.playingGameList.get(gameId);
        } else {
            game = this.notPlayingGameList.get(gameId);
        }
        game.addChatToList(chat);
        clientProxy.receivedChat(chat, game.isPlaying(), gameId);
    }

    /**
     * Get the entire chat history.
     *
     * @param gameId The ID of the game we need to work with!
     */
    public void getChatHistory(String gameId, String username, boolean gameStarted) {
        Game game;
        if (gameStarted) {
            game = this.playingGameList.get(gameId);
        } else {
            game = this.notPlayingGameList.get(gameId);
        }
        clientProxy.receivedChatHistory(game.getChatHistory(), gameStarted, username, game.getGameId());
    }

    /**
     * Get the entire game history.
     *
     * @param gameId The ID of the game we need to work with!
     */
    public void getGameHistory(String gameId) {
        // Todo: Make this sucker work baby.
    }

    /**
     * User Telling the server that they have initialized. Need to make sure every one of the users sends this.
     *
     * @param gameId The ID of the game we need to work with!
     */
    public void readyToInitialize(String gameId, String username) {
        Game game = this.playingGameList.get(gameId);

        ArrayList<TrainCard> trainCards = game.initializeTrainCardsInHand();
        ArrayList<Ticket> tickets = game.initializeTickets();
        ArrayList<Player> turnOrder = game.initializeTurnOrder(username);

        clientProxy.initializeGame(game.getTrainCardsFaceUp(), trainCards, tickets, turnOrder, username, game.getGameId());
        game.sendDeckCount();
    }

    /**
     * @param gameId   ID of the game needed!
     * @param username username that is ready to initialize
     */
    public void initializeComplete(String gameId, String username) {
        Game game = this.playingGameList.get(gameId);
        game.setReadyPlayers(game.getReadyPlayers() + 1);

        if (game.getMaxPlayers() == game.getReadyPlayers()) {
            ArrayList<Player> tempTurnOrder = new ArrayList<>(game.getPlayerList());
            clientProxy.startTurn(game.getAvailableRoutes(), tempTurnOrder.get(0).getUsername(), gameId);
        }
    }
}
