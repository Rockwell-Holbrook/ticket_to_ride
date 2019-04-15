package com.example.sqlplugin;

import com.example.shared.commands.Command;
import com.example.shared.interfaces.IGameDao;
import com.example.shared.model.Game;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLGameDao implements IGameDao {
    private Gson gson = new Gson();

    public void saveGame(Game game) throws SQLException  {
        Connection con = SQLManager.getConnected();

        PreparedStatement p = con.prepareStatement("INSERT INTO game " +
                "(gameID, game) " + "VALUES (?, ?);");

        p.setString(1, game.getGameId());
        p.setString(2, gson.toJson(game));

        p.executeUpdate();

        con.close();
    }

    public void saveDelta(String gameid, Command delta) throws SQLException {
        List<Command> commands = getDeltas(gameid);
        commands.add(delta);

        Connection con = SQLManager.getConnected();

        PreparedStatement p = con.prepareStatement("INSERT INTO deltas " +
                "(gameID, commands) " + "VALUES (?, ?);");

        p.setString(1, gameid);
        p.setString(2, gson.toJson(delta));

        p.executeUpdate();

        con.close();
    }

    public Game getGame(String gameid) throws SQLException {
        Connection con = SQLManager.getConnected();

        PreparedStatement p = con.prepareStatement("SELECT game " +
                "FROM game " + "WHERE gameID = '" + gameid + "'");

        ResultSet result = p.executeQuery();
        Game game = gson.fromJson(result.getString("game"), Game.class);

        con.close();

        return game;
    }

    public List<Command> getDeltas(String gameid) throws SQLException {
        Connection con = SQLManager.getConnected();

        PreparedStatement p = con.prepareStatement("SELECT commands " +
                "FROM deltas " + "WHERE gameID = '" + gameid + "'");

        ResultSet result = p.executeQuery();
        Type type = new TypeToken<List<Command>>() {}.getType();
        List<Command> commands = gson.fromJson(result.getString("game"), type);

        con.close();

        return commands;
    }

    public void clear() throws SQLException {
        Connection con = SQLManager.getConnected();

        PreparedStatement p = con.prepareStatement("DELETE FROM game");

        p.execute();

        con.close();
    }
}
