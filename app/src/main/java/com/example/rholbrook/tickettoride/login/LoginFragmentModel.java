package com.example.rholbrook.tickettoride.login;

import com.example.rholbrook.tickettoride.main.Authentication;
import com.example.rholbrook.tickettoride.serverconnection.AuthenticationServerProxy;
import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Message;
import com.example.shared.model.User;

import java.util.Observable;

class LoginFragmentModel extends Observable {
    public static final int SUCCESSFUL_LOGIN = 1;
    public static final int SENT_TO_REGISTER = 0;
    private static final LoginFragmentModel ourInstance = new LoginFragmentModel();
    private String username;
    private String password;

    public static LoginFragmentModel getInstance() {
        return ourInstance;
    }



    private LoginFragmentModel() {
        username = null;
        password = null;
    }



    public Message login() {
        String message;
        boolean success;

        try {
            User user = new User(username, password);
            message = AuthenticationServerProxy.getInstance().login(user);
            ServerProxy.getInstance().connectToManagementSocket(username);
            Authentication.getInstance().setUsername(username);
            success = true;

        } catch (Exception e) {
            message = e.getMessage();
            success = false;
        } catch (Throwable throwable) {
            message = throwable.getMessage();
            success = false;
        }
        return new Message(success, message);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
