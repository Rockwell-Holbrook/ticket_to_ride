package com.example.shared.interfaces;

import com.example.shared.model.Player;

import java.util.Set;

public interface IClientInGame {
    /**
     * Signals the GameLobbyFragmentModel to update the chat message list
     * @param username String value of the username of the sender of the message
     * @param message String value of the message sent
     */
    void chatReceived(String username, String message);

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

    void cardDrawn();
    void routeClaimed();
    void ticketsDrawn();
    void ticketsReturned();
}
