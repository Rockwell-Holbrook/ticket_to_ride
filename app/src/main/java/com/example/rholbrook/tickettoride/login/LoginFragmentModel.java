package com.example.rholbrook.tickettoride.login;

import com.example.rholbrook.tickettoride.serverconnection.AuthenticationServerProxy;
import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Message;
import com.example.shared.model.User;

import java.util.Observable;

class LoginFragmentModel extends Observable {
    private static final LoginFragmentModel ourInstance = new LoginFragmentModel();

    public static LoginFragmentModel getInstance() {
        return ourInstance;
    }



    private LoginFragmentModel() {

    }



    public Message login(String username, String password) throws Throwable {
        String message;
        boolean success;

        try {
            User user = new User(username, password);
            message = AuthenticationServerProxy.getInstance().login(user);
            success = true;

        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            throw e;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            success = false;
            throw throwable;
        }
        return new Message(success, message);

    }

    public void onSuccess() {

    }
}
