package com.example.rholbrook.tickettoride.login;

import com.example.shared.model.Message;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginPresenterTest {
    LoginFragmentContract.View view;
    LoginFragmentPresenter presenter = new LoginFragmentPresenter(view);



    @Test
    public void login() {
        // successful login
        String username = "Fernando";
        String password = "Beatles";

        presenter.updateUsername(username);
        presenter.updatePassword(password);

        Message message = presenter.login();

        assertNotNull(message);
        assertTrue(message.isSuccess());
        System.out.println(message.getMessage());

        // failed login - wrong password
        username = "Fernando";
        password = "RollingStones";

        presenter.updateUsername(username);
        presenter.updatePassword(password);

        message = presenter.login();

        assertNotNull(message);
        assertFalse(message.isSuccess());
        System.out.println(message.getMessage());

        // failed login - username invalid
        username = "Leo";
        password = "Unknown";

        presenter.updateUsername(username);
        presenter.updatePassword(password);

        message = presenter.login();

        assertNotNull(message);
        assertFalse(message.isSuccess());
        System.out.println(message.getMessage());

    }
}