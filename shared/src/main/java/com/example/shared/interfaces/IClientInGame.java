package com.example.shared.interfaces;

import com.example.shared.model.Player;

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
     */
    void playerJoinedGame(String username, Player.PlayerColor color);

    /**
     * Signals the GameLobbyFragmentModel to start the Game Activity
     */
    void gameStarted();

    void cardDrawn();
    void routeClaimed();
    void ticketsDrawn();
    void ticketsReturned();
}
