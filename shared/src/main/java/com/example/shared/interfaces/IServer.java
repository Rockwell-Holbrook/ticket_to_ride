package com.example.shared.interfaces;

public interface IServer {
    /**
     * Creates a game on a server
     * @param username the name of the host user
     */
      void createGame(String username);

    /**
     * Join un-started game from game list
     * @param username User who is joining the game
     * @param gameId Id of the game to join
     */
    void joinGame(String username, String gameId);

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
