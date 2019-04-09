package authentication;

import DatabaseAccess.DatabaseAccess;
import com.example.shared.model.Message;
import com.example.shared.model.User;

public class SessionManager {
    private DatabaseAccess databaseAccess = new DatabaseAccess();

    /**
     * Add the specified user to the database and log them in. If there is a duplicate return an error message back to the client.
     *
     * @param user The user to be logged in.
     * @return Returns a success or failure message.
     */
    public Message register(User user) {
        try {
            databaseAccess.store(user);
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Message(false, "This Username Already Exists"); // The only reason this would fail is if there is a duplicate.
        }

        return new Message(true, "Some future endpoint for the Socket Server");
    }

    /**
     * The user to login is verified to be within the database. If an error occurs it is sent back to the client to be handled.
     *
     * @param user The user to be logged in.
     * @return Returns a success or failure message.
     */
    public Message login(User user) {
        User databaseUser;
        try {
           databaseUser = databaseAccess.retrieve(user.getUserName());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Message(false, "Invalid Username or Password");
        }

        if(!databaseUser.getPassword().equals(user.getPassword())) {
            return new Message(false, "Invalid Username or Password");
        }

        return new Message(true, "Some future endpoint for the Socket Server");
    }
}
