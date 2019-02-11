package com.example.rholbrook.tickettoride.login;

import com.example.rholbrook.tickettoride.serverconnection.AuthenticationServerProxy;
import com.example.shared.model.Message;
import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginModelTest {

    @Before
    public void setup() {
        AuthenticationServerProxy proxy;
    }

    @Test
    public void login() {

        // successful login
        String username = "Fernando";
        String password = "Beatles";

        LoginFragmentModel.getInstance().setUsername(username);
        LoginFragmentModel.getInstance().setPassword(password);

        Message message = LoginFragmentModel.getInstance().login();

        assertNotNull(message);
        assertTrue(message.isSuccess());
        System.out.println(message.getMessage());

        // failed login - wrong password
        username = "Fernando";
        password = "RollingStones";

        LoginFragmentModel.getInstance().setUsername(username);
        LoginFragmentModel.getInstance().setPassword(password);

        message = LoginFragmentModel.getInstance().login();

        assertNotNull(message);
        assertFalse(message.isSuccess());
        System.out.println(message.getMessage());

        // failed login - username invalid
        username = "Leo";
        password = "Unknown";

        LoginFragmentModel.getInstance().setUsername(username);
        LoginFragmentModel.getInstance().setPassword(password);

        message = LoginFragmentModel.getInstance().login();

        assertNotNull(message);
        assertFalse(message.isSuccess());
        System.out.println(message.getMessage());

    }

}