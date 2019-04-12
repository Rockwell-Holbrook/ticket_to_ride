package com.example.shared.interfaces;

import com.example.shared.model.User;

public interface IUserDao {
    void registerUser(User user) throws Exception;
    User getUser(String username) throws Exception;
    void clear() throws Exception;
}
