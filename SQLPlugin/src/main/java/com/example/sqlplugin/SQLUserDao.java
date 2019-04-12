package com.example.sqlplugin;

import com.example.shared.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserDao {
    public void registerUser(User user) throws SQLException {
        Connection con = SQLManager.getConnected();

        PreparedStatement p = con.prepareStatement("INSERT INTO user " +
                "(username, password) " + "VALUES (?, ?);");

        p.setString(1, user.getUserName());
        p.setString(2, user.getPassword());

        p.executeUpdate();

        con.close();

    }

    public User getUser(String username) throws SQLException {
        Connection con = SQLManager.getConnected();

        PreparedStatement p = con.prepareStatement("SELECT password " +
                "FROM user " + "WHERE username = '"+username+"'");

        ResultSet result = p.executeQuery();
        User user;
        try {
            user = new User(username, result.getString("password"));
        }
        catch(Exception e) {
            e.printStackTrace();
            con.close();
            throw new SQLException("This should never be a problem");
        }

        con.close();


        return user;
    }

    public void clear() throws SQLException {
        Connection con = SQLManager.getConnected();

        PreparedStatement p = con.prepareStatement("DELETE FROM user");

        p.execute();

        con.close();
    }
}
