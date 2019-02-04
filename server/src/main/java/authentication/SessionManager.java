package authentication;

import model.Message;
import model.User;

public class SessionManager {
    private DatabaseAccess databaseAccess = new DatabaseAccess();

    public Message register(User user) {
        try {
            databaseAccess.store(user);
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Message(false, "duplicate"); // The only reason this would fail is if there is a duplicate.
        }

        return new Message(true, "Some future endpoint for the Socket Server");
    }

    public Message login(User user) {
        User databaseUser;
        try {
           databaseUser = databaseAccess.retrieve(user.getUserName());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Message(false, "incorrect username");
        }

        if(!databaseUser.getPassword().equals(user.getPassword())) {
            return new Message(false, "incorrect password");
        }

        return new Message(true, "Some future endpoint for the Socket Server");
    }
}
