package com.example.shared.interfaces;

import com.example.shared.model.Player;

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
     * Send a message to the chat lobby in an un-started game
     *
     * @param username User who sent the message
     * @param gameId Id of game lobby
     * @param message Message to send to all users
     */
      void sendChat(String username, String gameId, String message);

    /**
     * Returns the player list for a specific game to update when someone new joins the lobby.
     *
     * @param gameId The game for the playerList we need.
     */
      void getPlayerList(String gameId);
}
