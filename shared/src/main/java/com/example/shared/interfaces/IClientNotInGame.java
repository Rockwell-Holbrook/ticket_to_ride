package com.example.shared.interfaces;

import com.example.shared.model.Game;

import java.util.List;
import java.util.UUID;

public interface IClientNotInGame {
    /**
     * Sends the data to the MainActivityContract in order to update the current game list
     * @param games List of Game objects including number of players, game name, host, and gameId
     */
    void updateGameList(List<Game> games);

    /**
     * Sends a response to the MainActivityContract to connect to the game websocket
     * @param gameId Query for the connecting to the game websocket
     */
    void joinGameComplete(String gameId);
}
