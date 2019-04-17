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
import java.util.ArrayList;
import java.util.List;

public class SQLGameDao implements IGameDao {
    private Gson gson = new Gson();

    public void saveGame(Game game) throws SQLException  {
        String gameid = game.getGameId();

        Connection con = SQLManager.getConnected();

        PreparedStatement p = con.prepareStatement("SELECT count(*) " +
                "FROM game " + "WHERE gameID = '" + gameid + "'");

        ResultSet result = p.executeQuery();

        int count = result.getInt("count(*)");

        if (count == 0) {
            p = con.prepareStatement("INSERT INTO game " +
                    "(gameID, game) " + "VALUES (?, ?);");

            p.setString(1, gameid);
            p.setString(2, gson.toJson(game));

            p.executeUpdate();
        } else {
            p = con.prepareStatement("UPDATE game " +
                    "SET gameID = ?, game = ? " +
                    "WHERE gameID = '" + gameid + "'");

            p.setString(1, gameid);
            p.setString(2, gson.toJson(game));
        }

        con.close();
    }

    public void saveDelta(String gameid, Command delta) throws SQLException {
        Connection con = SQLManager.getConnected();

        PreparedStatement p = con.prepareStatement("SELECT count(*) " +
                "FROM deltas " + "WHERE gameID = '" + gameid + "'");

        ResultSet result = p.executeQuery();

        int count = result.getInt("count(*)");

        if (count == 0) {
            List<Command> deltas = new ArrayList<Command>();
            deltas.add(delta);
            p = con.prepareStatement("INSERT INTO deltas " +
                    "(gameID, commands) " + "VALUES (?, ?);");

            p.setString(1, gameid);
            p.setString(2, gson.toJson(deltas));

            p.executeUpdate();
        } else {
            List<Command> deltas = getDeltas(gameid);
            deltas.add(delta);
            p = con.prepareStatement("UPDATE deltas " +
                    "SET gameID = ?, commands = ? " +
                    "WHERE gameID = '" + gameid + "'");

            p.setString(1, gameid);
            p.setString(2, gson.toJson(deltas));
        }

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

    @Override
    public List<Game> getAllGames() throws SQLException {
        Connection con = SQLManager.getConnected();

        PreparedStatement p = con.prepareStatement("SELECT game " +
                "FROM game");

        List<Game> games = new ArrayList<>();
        ResultSet result = p.executeQuery();
        while (result.next()) {
            games.add(gson.fromJson(result.getString("game"), Game.class));
        }
        return games;
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

    @Override
    public void clearDeltas(String gameid) throws SQLException {
        Connection con = SQLManager.getConnected();

        List<Command> deltas = new ArrayList<>();
        PreparedStatement p = con.prepareStatement("UPDATE deltas " +
                "SET gameID = ?, commands = ? " +
                "WHERE gameID = '" + gameid + "'");

        p.setString(1, gameid);
        p.setString(2, gson.toJson(deltas));

        p.executeUpdate();

        con.close();
    }

    public void clear() throws SQLException {
        Connection con = SQLManager.getConnected();

        PreparedStatement p = con.prepareStatement("DELETE FROM game");

        p.execute();

        p = con.prepareStatement("DELETE FROM deltas");

        p.execute();

        con.close();
    }

    @Override
    public void deleteGame(String gameid) throws SQLException {
        Connection con = SQLManager.getConnected();

        PreparedStatement p = con.prepareStatement("DELETE FROM game " +
                "WHERE gameID = '" + gameid + "'");

        p.executeUpdate();

    }

    @Override
    public int getDeltaCount(String gameid) throws SQLException {
        List<Command> deltas = getDeltas(gameid);
        return deltas.size();
    }
}
