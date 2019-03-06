package com.example.shared.interfaces;

import com.example.shared.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface IClientInGame {
    /**
     * Signals the GameLobbyFragmentModel or GameActivityModel to update the chat message
     * list.
     * @param chat Chat object containing username of sender and message
     */
    void receivedChat(Chat chat, boolean gameStarted, String gameId);

    /**
     * Signals the GameLobbyFragmentModel to update the joined players list
     * @param gameId Id of the game created.
     * @param playerList This is the updated playerList as requested.
     */
    void playerJoinedGame(Set<Player> playerList, String gameId);

    /**
     * Signals the GameLobbyFragmentModel to start the Game Activity
     */
    void gameStarted(String gameId);

    /**
     * Signals the GameActivityModel to update the chat message list with all messages
     * @param chatHistory List of all previous Chat objects
     */
    void receivedChatHistory(List<Chat> chatHistory, boolean gameStarted, String username, String gamId);

    /**
     * Signals the GameActivityModel to update the game history list
     * @param history GameHistory object containing new history info
     */
    void receivedHistoryObject(GameHistory history);

    /**
     * Signals the GameActivityModel to update the game history list with all history items
     * @param gameHistory List of all previous GameHistory objects
     */
    void receivedGameHistory(List<GameHistory> gameHistory);

    /**
     * Signals the GameActivityModel to initialize the game with the player's hand, tickets
     * to choose from, and the turn order
     * @param trainCards List of 4 TrainCard objects to go into the player's hand
     * @param tickets List of 3 Ticket objects which the player must choose from
     * @param turnOrder List of Player objects, in turn order, starting with this player
     */
    void initializeGame(List<TrainCard> trainCardsFaceUp, List<TrainCard> trainCards, List<Ticket> tickets, List<Player> turnOrder, String username, String gameId);

    /**
     *
     * @param gameId the game ID that finished initializing
     * @param username the username of the player who is finished initializing
     */
    void initializeComplete(String gameId, String username);

    /**
     * Signals the GameActivityModel to show the player a list of tickets to choose from
     * @param tickets List of 3 Ticket objects which which the player must choose from
     */
    void ticketsReceived(List<Ticket> tickets);

    /**
     * Signals the GameActivityModel to allow the player to take a turn, updating the
     * routes that are available to claim
     * @param availableRoutes List of Route objects representing all unclaimed routes
     */
    void startTurn(List<Route> availableRoutes, String username);

    /**
     * Signals the GameActivityModel to update which tickets are completed
     * @param ticket Ticket object for completed ticket
     */
    void ticketCompleted(Ticket ticket);

    /**
     * Signals the GameActivityModel to update the map showing a new route being claimed
     * @param player Player object for the player who claimed the route
     * @param route Route object for the route that was claimed
     */
    void routeClaimed(Player player, Route route);

    /**
     * Signals the GameActivityModel to update the face-up train cards after a player draws one
     * @param faceUpCards List of TrainCard objects for the face-up train cards
     */
    void cardDrawn(List<TrainCard> faceUpCards);

    /**
     * Signals the GameActivityModel to update a player's Player Summary Box at the end of
     * another player's turn
     * @param player Player object for the player whose turn finished
     */
    void turnEnded(Player player);
}
