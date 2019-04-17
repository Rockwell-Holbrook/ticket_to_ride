package com.example.shared.interfaces;

import com.example.shared.commands.Command;
import com.example.shared.commands.Deltas;
import com.example.shared.model.Game;

import java.util.List;

public interface IGameDao {
    void saveGame(Game game) throws Exception;
    void saveDelta(String gameid, String delta) throws Exception;
    Game getGame(String gameid) throws Exception;
    List<Game> getAllGames() throws Exception;
    Deltas getDeltas(String gameid) throws Exception;
    void clearDeltas(String gameid) throws Exception;
    void clear() throws Exception;
    void deleteGame(String gameid) throws Exception;
    int getDeltaCount(String gameid) throws Exception;
}
