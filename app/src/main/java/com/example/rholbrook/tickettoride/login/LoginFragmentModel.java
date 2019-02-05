package com.example.rholbrook.tickettoride.login;

import com.example.rholbrook.tickettoride.serverconnection.AuthenticationServerProxy;
import model.User;

class LoginFragmentModel {
    private static final LoginFragmentModel ourInstance = new LoginFragmentModel();

    public static LoginFragmentModel getInstance() {
        return ourInstance;
    }



    private LoginFragmentModel() {

    }

    public String login(String username, String password) throws Throwable {
        try {
            User user = new User(username, password);
            return AuthenticationServerProxy.getInstance().login(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }
    }
}
