package com.example.shared.interfaces;

import com.example.shared.model.Player;

public interface IServer {
    /**
     * Creates a game on a server
     * @param host Player who created the game
     * @param maxPlayers max players
     * @param gameName Name of the game
     */
    void createGame(Player host, int maxPlayers, String gameName);


    /**
     * Join un-started game from game list
     * @param username User who is joining the game
     * @param gameId Id of the game to join
     */
    void joinGame(String gameId, Player player);

    /**
     * Start a game
     * @param gameId Id of game to start
     */
      void startGame(String gameId);

    /**
     * Send a message to the chat lobby in an un-started game
     * @param username User who sent the message
     * @param gameId Id of game lobby
     * @param message Message to send to all users
     */
      void sendChat(String username, String gameId, String message);
}
