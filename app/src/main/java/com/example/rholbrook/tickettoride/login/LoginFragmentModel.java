package com.example.rholbrook.tickettoride.login;

import com.example.rholbrook.tickettoride.serverconnection.AuthenticationServerProxy;
import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import model.Message;
import model.User;

import java.util.Observable;

class LoginFragmentModel extends Observable {
    private static final LoginFragmentModel ourInstance = new LoginFragmentModel();

    public static LoginFragmentModel getInstance() {
        return ourInstance;
    }



    private LoginFragmentModel() {

    }



    public Message login(String username, String password) throws Throwable {
        final String[] message = new String[1];
        final boolean[] success = new boolean[1];

        try {
            final User user = new User(username, password);
            LoginTask loginTask = new LoginTask();
            loginTask.setListener(new ListeningTask.Listener() {
                @Override
                public void onComplete(Object result) {
                    try {
                        message[0] = AuthenticationServerProxy.getInstance().login(user);
                        success[0] = true;
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                        success[0] = false;
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
            success[0] = false;
            throw e;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            success[0] = false;
            throw throwable;
        }
        return new Message(success[0], message[0]);

    }

    public void onSuccess() {

    }
}
