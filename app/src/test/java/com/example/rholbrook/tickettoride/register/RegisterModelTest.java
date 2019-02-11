package com.example.rholbrook.tickettoride.register;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterModelTest {

    @Test
    public void register() {
        // Good registration
        String username = "Fernando";
        String password = "Beatles";
        String confPassword = "Beatles";

        RegisterModel.getInstance().setUsername(username);
        RegisterModel.getInstance().setPassword(password);
        RegisterModel.getInstance().setConfPassword(confPassword);

        RegisterModel.getInstance().register();

        //(RegisterModel.getInstance().register().);

        // Bad registration: different password inputs

        username = "Erick";
        password = "PinkFloyd";
        confPassword = "Beatles";

        RegisterModel.getInstance().setUsername(username);
        RegisterModel.getInstance().setPassword(password);
        RegisterModel.getInstance().setConfPassword(confPassword);

        RegisterModel.getInstance().register();

        // Good registration

        username = "Genesis";
        password = "Paramore";
        confPassword = "Paramore";

        RegisterModel.getInstance().setUsername(username);
        RegisterModel.getInstance().setPassword(password);
        RegisterModel.getInstance().setConfPassword(confPassword);

        RegisterModel.getInstance().register();

        // Bad registration: username already exists

        username = "Genesis";
        password = "Rihanna";
        confPassword = "Rihanna";

        RegisterModel.getInstance().setUsername(username);
        RegisterModel.getInstance().setPassword(password);
        RegisterModel.getInstance().setConfPassword(confPassword);

        RegisterModel.getInstance().register();




    }

}