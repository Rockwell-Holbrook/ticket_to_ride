package com.example.shared.interfaces;

import com.example.shared.commands.Command;
import com.example.shared.model.Game;

import java.util.List;

public interface IGameDao {
    void saveGame(Game game) throws Exception;
    void saveDelta(String gameid, Command delta) throws Exception;
    Game getGame(String gameid) throws Exception;
    List<Command> getDeltas(String gameid) throws Exception;
    void clearDeltas(String gameid) throws Exception;
    void clear() throws Exception;
    int getDeltaCount(String gameid) throws Exception;
}
