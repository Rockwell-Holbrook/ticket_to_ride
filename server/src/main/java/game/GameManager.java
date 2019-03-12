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
     * @pre
     * @post
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
     * @pre
     * @post
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
     * Starts the game specified by the gameID.
     *
     * @pre
     * @post
     *
     * @param gameId The ID of the game that needs to be started.
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
     * @pre
     * @post
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
     * @pre
     * @post
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
     * @pre
     * @post
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
     * @pre
     * @post
     *
     * @param gameId The ID of the game we need to work with!
     */
    public void getGameHistory(String gameId) {
        // Todo: Make this sucker work baby.
    }

    /**
     * User Telling the server that they have initialized. Need to make sure every one of the users sends this.
     *
     * @pre
     * @post
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
     * The User has told us that they have successfully initialized. If he is the last user to do so then the game is started.
     *
     * @pre
     * @post
     *
     * @param gameId   ID of the game needed!
     * @param username username that is ready to initialize.
     */
    public void initializeComplete(String gameId, String username) {
        Game game = this.playingGameList.get(gameId);
        game.setReadyPlayers(game.getReadyPlayers() + 1);

        if (game.getMaxPlayers() == game.getReadyPlayers()) {
            ArrayList<Player> tempTurnOrder = new ArrayList<>(game.getPlayerList());
            clientProxy.startTurn(game.getAvailableRoutes(), tempTurnOrder.get(0).getUsername(), gameId);
            clientProxy.turnStarted(tempTurnOrder.get(0), gameId);
        }
    }

    /**
     * A user may request a Train Card either from the face up cards or from the deck.
     *
     * @pre
     * @post
     *
     * @param gameId ID of the game needed!
     * @param username The username of the User that needs to get a card.
     * @param index a number from 0-5 where 5 is the deck and 0-4 are face up cards
     */
    public void getCard(String gameId, String username, int index) {
        Game game = this.playingGameList.get(gameId);
        game.cardSelected(username, index);
    }

    /**
     * A user may request Tickets as long as there are Tickets left to give out. The user is given 3 tickets.
     *
     * @pre
     * @post
     *
     * @param gameID ID of the game needed!
     * @param username The username of the User that has requested a ticket.
     */
    public void requestTickets(String gameID, String username) {
        Game game = this.playingGameList.get(gameID);
        game.ticketsRequested(username);
    }

    /**
     * After a user requests tickets they may return 0-2 of their Tickets.
     *
     * @pre
     * @post
     *
     * @param gameId ID of the game needed!
     * @param username The username of the User that is returning tickets.
     * @param returned The Tickets the player wishes to return to the deck.
     */
    public void ticketsReturned(String gameId, String username, ArrayList<Ticket> returned) {
        Game game = this.playingGameList.get(gameId);
        game.ticketsReturned(username, returned);
    }

    /**
     * If a user has the necessary TrainCards they may claim a route.
     *
     * @pre
     * @post
     *
     * @param gameId ID of the game needed!
     * @param username The username of the User that has asked to claim a route.
     * @param routeId The ID of the route that the user is asking to claim.
     */
    public void claimRoute(String gameId, String username, int routeId) {
        Game game = this.playingGameList.get(gameId);
        game.claimRoute(username, routeId);
    }

    /**
     * After a player has exhausted their options their turn is ended and passed onto the next player.
     *
     * @pre
     * @post
     *
     * @param gameID ID of the game needed!
     * @param username The username of the User that has ended his turn.
     */
    public void endPlayerTurn(String gameID, String username) {
        Game game = this.playingGameList.get(gameID);
        game.endPlayerTurn(username);
    }
}
