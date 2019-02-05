package com.example.rholbrook.tickettoride.register;

import com.example.rholbrook.tickettoride.serverconnection.AuthenticationServerProxy;
import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Message;
import com.example.shared.model.User;

import java.util.TreeSet;

/**
 * Created by chocobj on 2/4/19.
 */

public class RegisterModel {
    private static final RegisterModel ourInstance = new RegisterModel();

    private String username;
    private String password;
    private String confPassword;

    public static RegisterModel getInstance() {
        return ourInstance;
    }

    private RegisterModel() {
        username = null;
        password = null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public Message register() {
        String message;
        boolean success;
        if (passwordsMatch()) {
            try {
                User user = new User(username, password);
                message = AuthenticationServerProxy.getInstance().register(user);
                ServerProxy.getInstance().connectToManagementSocket(username);
                success = true;
            } catch (Throwable e) {
                message = e.getMessage();
                success = false;
            }
        } else {
            message = "Passwords do not match";
            success = false;
        }
        return new Message(success, message);
    }

    private boolean passwordsMatch() {
        if (password.equals(confPassword)) return true;
        return false;
    }
}
