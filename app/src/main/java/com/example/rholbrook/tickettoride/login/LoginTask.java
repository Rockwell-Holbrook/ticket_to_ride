package com.example.rholbrook.tickettoride.login;

import com.example.rholbrook.tickettoride.serverconnection.AuthenticationServerProxy;
import model.Message;
import model.User;

/**
 * Created by chocobj on 11/15/18.
 */

public class LoginTask extends ListeningTask<User, Void, Message> {
    @Override
    protected Message doInBackground(User... user) {
        String message;
        boolean success;
        try {
            AuthenticationServerProxy.getInstance().register(user[0]);
            message = "Authentication successful";
            success = true;
        } catch (Throwable e) {
            message = e.getMessage();
            success = false;
        }
        return new Message(success, message);
    }
}