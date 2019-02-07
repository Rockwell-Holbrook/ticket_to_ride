package authentication;

import com.example.shared.model.User;

import java.sql.*;

public class DatabaseAccess {

    void store(User user) throws SQLException {
        Connection con = getConnected();

        PreparedStatement p = con.prepareStatement("INSERT INTO user " +
                "(username, password) " + "VALUES (?, ?);");

        p.setString(1, user.getUserName());
        p.setString(2, user.getPassword());

        p.executeUpdate();

        con.close();
    }

    User retrieve(String username) throws SQLException {
        Connection con = getConnected();

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
        Connection con = getConnected();

        PreparedStatement p = con.prepareStatement("DELETE FROM user");

        p.execute();

        con.close();
    }

    private Connection getConnected() {
        Connection conn = null;

        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            final String CONNECTION_URL = "jdbc:sqlite:server/userDatabase.db";

            // Open a database connection
            conn = DriverManager.getConnection(CONNECTION_URL);

        }
        catch (SQLException e) {
            System.out.println("Error When Connecting to the Database!");
        }

        return conn;
    }
}