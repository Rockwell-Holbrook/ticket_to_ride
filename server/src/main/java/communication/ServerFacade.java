package communication;

import com.example.shared.interfaces.IServer;
import com.example.shared.model.Chat;
import com.example.shared.model.Player;
import com.example.shared.model.Route;
import com.example.shared.model.Ticket;
import game.GameManager;

import java.util.ArrayList;

public class ServerFacade implements IServer {
    private static final ServerFacade ourInstance = new ServerFacade();
    private GameManager gameManager = GameManager.getInstance();


    public static ServerFacade getInstance() {
        return ourInstance;
    }

    private ServerFacade() {
    }

    @Override
    public void getGameList(String username) {
        gameManager.sendGameList(username);
    }

    /**
     * Creates the game and adds it to the gameList and to the socketServer using gameID as the key.
     *
     * @param host       The Player hosting the game. This player will be set to host upon creation.
     * @param maxPlayers The max number of players this game needs before it can start. An int between 2-5.
     * @param gameName   The name of the game.
     */
    @Override
    public void createGame(Player host, int maxPlayers, String gameName) {
        String gameId = gameManager.createGame(host, maxPlayers, gameName);
        gameManager.joinGame(gameId, host);
    }

    /**
     * The provided player will be added to the game specified by the gameId.
     *
     * @param gameId The ID of the game trying to be joined.
     * @param player The Player that wants to join the game.
     */
    @Override
    public void joinGame(String gameId, Player player) {
        gameManager.joinGame(gameId, player);
    }

    /**
     * Starts the game specified by the gameID.
     *
     * @param gameId The ID of the game that needs to be started.
     */
    @Override
    public void startGame(String gameId) {
        gameManager.startGame(gameId);
    }

    /**
     * Returns the player list for a specific game to update when someone new joins the lobby.
     *
     * @param gameId The game for the playerList we need.
     */
    @Override
    public void getPlayerList(String gameId) {
        gameManager.getPlayerList(gameId);
    }

    /**
     * Send a message to the chat lobby in an un-started game
     *
     * @param gameId Id of game lobby
     * @param chat Message to send to all users and username in one
     */
    @Override
    public void sendChat(Chat chat, String gameId, boolean gameStarted) {
        gameManager.sendChat(chat, gameId, gameStarted);
    }

    /**
     * Get the entire chat history.
     *
     * @param gameId The ID of the game we need to work with!
     */
    @Override
    public void getChatHistory(String gameId, String username, boolean gameStarted) {
        gameManager.getChatHistory(gameId, username, gameStarted);
    }

    /**
     * Get the entire game history.
     *
     * @param gameId The ID of the game we need to work with!
     */
    @Override
    public void getGameHistory(String gameId) {
        gameManager.getGameHistory(gameId);
    }

    /**
     *
     * @param gameId ID of the game needed!
     * @param username username that is ready to initialize
     */
    public void readyToInitialize(String gameId, String username) {
        gameManager.readyToInitialize(gameId, username);
    }

    /**
     * User Telling the server that they have initialized. Need to make sure every one of the users sends this.
     *
     * @param gameId The ID of the game we need to work with!
     */
    @Override
    public void initializeComplete(String gameId, String username) {
        gameManager.initializeComplete(gameId, username);
    }

    /**
     * Tickets from the beginning of the game that the User wants to send back.
     *
     * @param returned Tickets that the user is returning to be placed in the deck.
     */
    @Override
    public void ticketsReturned(String gameId, String username, ArrayList<Ticket> returned) {
        // Todo: Make this sucker work baby.
    }

    /**
     * A user may return some of the tickets that they received for their turn.
     *
     * @param gameID The ID of the game we need to work with!
     */
    @Override
    public void turnEnded(String gameID, String username) {
        // Todo: Make this sucker work baby.
    }

    /**
     * A User may spend their turn claiming 1 or two cards from the deck or the face up.
     *
     * @param index The index of the visible card's that the user can grab.
     */
    @Override
    public void getCard(String gameId, String username, int index) {
        gameManager.getCard(gameId, username, index);
    }

    /**
     * A user may spend their turn claiming a route.
     *
     *
     */
    @Override
    public void claimRoute(String gameId, String username, int routeId) {
        // Todo: Make this sucker work baby.
    }

    /**
     * A user may spend their turn getting tickets.
     *
     * @param gameID The ID of the game we need to work with!
     */
    @Override
    public void requestTickets(String gameID, String username) {
        gameManager.requestTickets(gameID, username);
    }
}
