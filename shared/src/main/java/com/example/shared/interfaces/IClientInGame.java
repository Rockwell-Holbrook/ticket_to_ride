package com.example.shared.interfaces;

import com.example.shared.model.*;

import java.util.List;
import java.util.Set;

public interface IClientInGame {
    /**
     * Signals the GameLobbyFragmentModel or GameActivityModel to update the chat message
     * list.
     *
     * @param chat Chat object containing username of sender and message
     */
    void receivedChat(Chat chat, boolean gameStarted, String gameId);

    /**
     * Signals the GameLobbyFragmentModel to update the joined players list
     *
     * @param gameId     Id of the game created.
     * @param playerList This is the updated playerList as requested.
     */
    void playerJoinedGame(Set<Player> playerList, String gameId);

    /**
     * Signals the GameLobbyFragmentModel to start the Game Activity
     */
    void gameStarted(String gameId);

    /**
     * Signals the GameActivityModel to update the chat message list with all messages
     *
     * @param chatHistory List of all previous Chat objects
     */
    void receivedChatHistory(List<Chat> chatHistory, boolean gameStarted, String username, String gameId);


    /**
     * Signals the GameActivityModel to update the game history list with all history items
     *
     * @param gameHistory List of all previous GameHistory objects
     */
    void receivedGameHistory(List<GameHistory> gameHistory, String username, String gameId);

    /**
     * Signals the GameActivityModel to initialize the game with the player's hand, tickets
     * to choose from, and the turn order
     *
     * @param trainCards List of 4 TrainCard objects to go into the player's hand
     * @param tickets    List of 3 Ticket objects which the player must choose from
     * @param turnOrder  List of Player objects, in turn order, starting with this player
     */
    void initializeGame(List<TrainCard> trainCardsFaceUp, List<TrainCard> trainCards, List<Ticket> tickets, List<Player> turnOrder, String username, String gameId);

    /**
     * Signals the GameActivityModel to update the player's view
     *
     * @param player Player object with updated data
     */
    void initializeComplete(Player player, String gameId);

    /**
     * Signals the GameActivityModel to show the player a list of tickets to choose from
     *
     * @param tickets List of 3 Ticket objects which which the player must choose from
     * @param username
     * @param gameId
     */
    void ticketsReceived(List<Ticket> tickets, String username, String gameId);

    /**
     * Signals the GameActivityModel to allow the player to take a turn, updating the
     * routes that are available to claim
     *
     * @param availableRoutes List of Route objects representing all unclaimed routes
     */
    void startTurn(List<Route> availableRoutes, String username, String gameId);

    void turnStarted(Player player, String gameId);

    /**
     * Signals the GameActivityModel to update which tickets are completed
     *
     * @param ticket Ticket object for completed ticket
     */
    void ticketCompleted(Ticket ticket);

    /**
     * Signals the GameActivityModel to update the map showing a new route being claimed
     *
     * @param player Player object for the player who claimed the route
     * @param route  Route object for the route that was claimed
     */
    void routeClaimed(Player player, Route route);

    /**
     * Signals the GameActivityModel to update a player's Player Summary Box at the end of
     * another player's turn
     *
     * @param player Player object for the player whose turn finished
     */
    void turnEnded(Player player);

    /**
     * Sends the number of cards in the decks to the clients. Server uses this without a request from client
     *
     * @param ticketDeckCount Number of cards in ticket deck
     * @param trainDeckCount  Number of cards in train card deck
     */
    void sendDeckCount(int ticketDeckCount, int trainDeckCount);


    /**
     * Sends a new set of faceup train cards
     *
     * @param newTrainCards New Train Card objects to update the view with
     */
    void updateFaceUpCards(List<TrainCard> newTrainCards);

    /**
     * Sends the user the card that they drew when they take a top card
     *
     * @param newCard The card that the player drew
     * @param username The username of the player
     * @param gameId GameId to specify which game
     */
    void receiveFaceDownCard(TrainCard newCard, String username, String gameId);

    void getClaimableRoutes(List<Route> claimableRoutes, String username, String gameId);

    void gameEnding(String gameId);

    void gameEnded(String gameId);

    void fatalError(String message);
}
