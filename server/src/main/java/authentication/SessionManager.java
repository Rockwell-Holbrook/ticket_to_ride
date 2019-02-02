package authentication;

import com.example.shared.model.Message;
import com.example.shared.model.User;

public class SessionManager {

    public Message register(User user) {
        login(user);
        return new Message(true, "User Registered Correctly");
    }

    public Message login(User user) {
        return new Message(true, "User Logged In Correctly");
    }
}
