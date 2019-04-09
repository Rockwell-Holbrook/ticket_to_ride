package DatabaseAccess;

import com.example.shared.model.User;

public interface IUserDao {
    public void registerUser(User user);
    public User getUser(String username);
    public void clear();
}
