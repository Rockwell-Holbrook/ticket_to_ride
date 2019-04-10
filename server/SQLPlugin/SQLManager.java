package SQLPlugin;

import java.sql.*;

public class SQLManager {

    static public Connection getConnected() {
        Connection conn = null;

        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            final String CONNECTION_URL = "jdbc:sqlite:SQLDatabase.db";

            // Open a database connection
            conn = DriverManager.getConnection(CONNECTION_URL);

        }
        catch (SQLException e) {
            System.out.println("Error When Connecting to the Database!");
        }

        return conn;
    }
}