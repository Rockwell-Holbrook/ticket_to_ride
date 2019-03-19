package game;

import com.example.shared.model.*;
import communication.ClientProxy;
import communication.SocketServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {
    private static final GameManager instance = new GameManager();

    /**
     * An empty constructor because we implemented this class as a singleton
     */
    private GameManager() {
    }

    /**
     *
     * @return returns the singleton instance of GameManager
     */
    public static GameManager getInstance() {
        return instance;
    }

    private Map<String, Game> notPlayingGameList = new HashMap<>();
    private Map<String, Game> playingGameList = new HashMap<>();

    private ClientProxy clientProxy = new ClientProxy();

    /**
     * Creates the game and adds it to the notPlayingGameList and to the socketServer using gameID as the key.
     *
     * @pre host, maxPlayers, and gameName != null
     * @pre  2 < maxPlayers < 5
     * @post Game succesfully created
     *
     * @param host       The Player hosting the game. This player will be set to host upon creation.
     * @param maxPlayers The max number of players this game needs before it can start. An int between 2-5
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
     * @pre gameId && player != null
     * @pre gameId and player exist in our gameList and associated player list
     * @post Player successfully joins game
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

    /**
     * A user may need to get the GameList.
     *
     * @pre username != null
     * @post broadcast the gameList to the username given!
     *
     * @param username username that needs the gameList
     */
    public void sendGameList(String username) {
        clientProxy.updateGameList(new ArrayList<>(notPlayingGameList.values()), username);
    }

    /**
     * Starts the game specified by the gameID.
     *
     * @pre gameId != null
     * @pre gameId corresponds with a game in our gameList
     * @post the game designated by the gameId is started.
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
     * @pre gameId != null
     * @pre gameId corresponds with a game in our gameList
     * @post returns the playerList for the game designated by the gameId.
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
     * @pre chat, gameId, & gameStarted != null
     * @pre gameId corresponds with a game in our gameList
     * @post chat is stored and broadcast to the game
     *
     * @param gameId Id of game lobby
     * @param chat   Message to send to all users and username in one
     * @param gameStarted whether the game is started or not so we know which list to grab the game from
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
     * @pre gameId, username, & gameStarted != null
     * @pre gameId corresponds with a game in our gameList
     * @post sends the game's chat history to the username.
     *
     * @param gameId The ID of the game we need to work with!
     * @param username username that needs the history.
     * @param gameStarted whether the game is started or not so we know which list to grab the game from
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
     * @pre gameId != null
     * @pre gameId corresponds with a game in our gameList
     * @post gameHistory is sent to the user
     *
     * @param gameId The ID of the game we need to work with!
     */
    public void getGameHistory(String gameId, String username) {
        Game game = this.playingGameList.get(gameId);
        clientProxy.receivedGameHistory(game.getGameHistory(), username, gameId);
    }

    /**
     * User Telling the server that they have initialized. Need to make sure every one of the users sends this.
     *
     * @pre gameId & username != null
     * @pre gameId corresponds with a game in our gameList
     * @pre username corresponds with a user in the game
     * @post the user's game is initialized
     *
     * @param gameId The ID of the game we need to work with!
     */
    public void readyToInitialize(String gameId, String username) {
        Game game = this.playingGameList.get(gameId);

        ArrayList<TrainCard> trainCards = game.initializeTrainCardsInHand(username);
        ArrayList<Ticket> tickets = game.initializeTickets(username);
        ArrayList<Player> turnOrder = game.initializeTurnOrder(username);

        clientProxy.initializeGame(game.getTrainCardsFaceUp(), trainCards, tickets, turnOrder, username, game.getGameId());
        game.sendDeckCount();
    }

    /**
     * The User has told us that they have successfully initialized. If he is the last user to do so then the game is started.
     *
     * @pre gameId & username != null
     * @pre gameId corresponds with a game in our gameList
     * @pre username corresponds with a user in the game
     * @post A player will be marked as ready and when all are ready the game will begin!
     *
     * @param gameId   ID of the game needed!
     * @param username username that is ready to initialize.
     */
    public void initializeComplete(String gameId, String username) {
        Game game = this.playingGameList.get(gameId);
        game.setReadyPlayers(game.getReadyPlayers() + 1);

        clientProxy.initializeComplete(game.getPlayerWithUsername(username), gameId);

        if (game.getMaxPlayers() == game.getReadyPlayers()) {
            ArrayList<Player> tempTurnOrder = new ArrayList<>(game.getPlayerList());
            clientProxy.startTurn(game.calculateClaimableRoutes(tempTurnOrder.get(0).getUsername()), tempTurnOrder.get(0).getUsername(), gameId);
            clientProxy.turnStarted(tempTurnOrder.get(0), gameId);
        }
    }

    /**
     * A user may request a Train Card either from the face up cards or from the deck.
     *
     * @pre gameId, index & username != null
     * @pre gameId corresponds with a game in our gameList
     * @pre username corresponds with a user in the game
     * @pre index is between 0-5 5 being the deck
     * @post a user will receive the card of their choice
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
     * @pre gameId & username != null
     * @pre gameId corresponds with a game in our gameList
     * @pre username corresponds with a user in the game
     * @post user is given 3 tickets
     *
     * @param gameId ID of the game needed!
     * @param username The username of the User that has requested a ticket.
     */
    public void requestTickets(String gameId, String username) {
        Game game = this.playingGameList.get(gameId);
        game.ticketsRequested(username);
    }

    /**
     * After a user requests tickets they may return 0-2 of their Tickets.
     *
     * @pre gameId, returned & username != null
     * @pre gameId corresponds with a game in our gameList
     * @pre username corresponds with a user in the game
     * @pre returned has a size of 0-2
     * @post The cards are removed from the user's hand and put back in the deck
     *
     * @param gameId ID of the game needed!
     * @param username The username of the User that is returning tickets.
     * @param returned The Tickets the player wishes to return to the deck.
     */
    public void ticketsReturned(String gameId, String username, ArrayList<Ticket> returned) {
        Game game = this.playingGameList.get(gameId);
        game.ticketsReturned(gameId, username, returned);
    }

    /**
     * If a user has the necessary TrainCards they may claim a route.
     *
     * @pre gameId, routeId & username != null
     * @pre gameId corresponds with a game in our gameList
     * @pre username corresponds with a user in the game
     * @pre user has the TrainCards needed to claim the route
     * @post user claim's a route and TrainCards are removed from their hand
     *
     * @param gameId ID of the game needed!
     * @param username The username of the User that has asked to claim a route.
     * @param routeId The ID of the route that the user is asking to claim.
     */
    public void claimRoute(String gameId, String username, int routeId, List<TrainCard> selectedCards) {
        Game game = this.playingGameList.get(gameId);
        game.claimRoute(username, routeId, selectedCards);
    }

    /**
     * After a player has exhausted their options their turn is ended and passed onto the next player.
     *
     * @pre gameId & username != null
     * @pre gameId corresponds with a game in our gameList
     * @pre username corresponds with a user in the game
     * @post the turn for the user is ended and passed to the next user
     *
     * @param gameId ID of the game needed!
     * @param username The username of the User that has ended his turn.
     */
    public void endPlayerTurn(String gameId, String username) {
        Game game = this.playingGameList.get(gameId);
        game.endPlayerTurn(username);
    }

    public void calculateClaimableRoutes(String gameId, String username) {
        Game game = this.playingGameList.get(gameId);
        game.calculateClaimableRoutes(username);
    }
}
