package com.example.shared.interfaces;

import com.example.shared.model.Chat;
import com.example.shared.model.Player;
import com.example.shared.model.Route;
import com.example.shared.model.Ticket;

import java.util.ArrayList;

public interface IServer {

    /**
     * Used by a user who just barely joined the management server so that they can get all open games
     * @param username User asking for game list
     */
    void getGameList(String username);

    /**
     * Creates the game and adds it to the gameList and to the socketServer using gameID as the key.
     *
     * @param host The Player hosting the game. This player will be set to host upon creation.
     * @param maxPlayers The max number of players this game needs before it can start. An int between 2-5.
     * @param gameName The name of the game.
     */
    void createGame(Player host, int maxPlayers, String gameName);

    /**
     * The provided player will be added to the game specified by the gameId.
     *
     * @param gameId The ID of the game trying to be joined.
     * @param player The Player that wants to join the game.
     */
    void joinGame(String gameId, Player player);

    /**
     * Starts the game specified by the gameID.
     *
     * @param gameId The ID of the game that needs to be started.
     */
    void startGame(String gameId);

    /**
     * Returns the player list for a specific game to update when someone new joins the lobby.
     *
     * @param gameId The game for the playerList we need.
     */
    void getPlayerList(String gameId);

    /**
     * Send a message to the chat lobby in an un-started game
     *
     * @param gameId Id of game lobby
     * @param chat Message to send to all users and username in one
     */
    void sendChat(Chat chat, String gameId, boolean gameStarted);

    /**
     * Get the entire chat history.
     *
     * @param gameId The ID of the game we need to work with!
     */
    void getChatHistory(String gameId, String username, boolean gameStarted);

    /**
     * Get the entire game history.
     *
     * @param gameId The ID of the game we need to work with!
     */
    void getGameHistory(String gameId);

    /**
     * User Telling the server that they have initialized. Need to make sure every one of the users sends this.
     *
     * @param gameId The ID of the game we need to work with!
     */
    void initializedGame(String gameId);

    /**
     * Tickets from the beginning of the game that the User wants to send back.
     *
     * @param returned Tickets that the user is returning to be placed in the deck.
     */
    void ticketsReturned(ArrayList<Ticket> returned);

    /**
     * A user may return some of the tickets that they received for their turn.
     *
     * @param gameID The ID of the game we need to work with!
     */
    void turnEnded(String gameID);

    /**
     * A User may spend their turn claiming 1 or two cards from the deck or the face up.
     *
     * @param index The index of the visible card's that the user can grab.
     */
    void getCard(int index);

    /**
     * A user may spend their turn claiming a route.
     *
     * @param route The route the user wants to claim.
     */
    void claimRoute(Route route);

    /**
     * A user may spend their turn getting tickets.
     *
     * @param gameID The ID of the game we need to work with!
     */
    void requestTickets(String gameID);


}
