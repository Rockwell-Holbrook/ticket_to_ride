package com.example.flatfileplugin;

import com.example.shared.interfaces.IUserDao;
import com.example.shared.model.User;

public class FFUserDao implements IUserDao {
    @Override
    public void registerUser(User user) {

    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public void clear() {

    }
}