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
     * @param username String value of the username of the joined player
     * @param color PlayerColor enum value of the color of the joined player
     * @param playerList This is the updated playerList as requested.
     */
    void playerJoinedGame(String username, Player.PlayerColor color, Set<Player> playerList);

    /**
     * Signals the GameLobbyFragmentModel to start the Game Activity
     */
    void gameStarted(String gameId);

    void cardDrawn();
    void routeClaimed();
    void ticketsDrawn();
    void ticketsReturned();
}
