package com.example.rholbrook.tickettoride.register;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterPresenterTest {
    RegisterContract.View view;
    RegisterPresenter presenter = new RegisterPresenter(view);

    @Test
    public void register() {

        // Good registration
        String username = "Fernando";
        String password = "Beatles";
        String confPassword = "Beatles";

        presenter.updateUsername(username);
        presenter.updatePassword(password);
        presenter.updatePassword(confPassword);

        presenter.register();

        // Bad registration: different password inputs

        username = "Erick";
        password = "PinkFloyd";
        confPassword = "Beatles";

        presenter.updateUsername(username);
        presenter.updatePassword(password);
        presenter.updatePassword(confPassword);

        presenter.register();

        // Good registration

        username = "Genesis";
        password = "Paramore";
        confPassword = "Paramore";

        presenter.updateUsername(username);
        presenter.updatePassword(password);
        presenter.updatePassword(confPassword);

        presenter.register();

        // Bad registration: username already exists

        username = "Genesis";
        password = "Rihanna";
        confPassword = "Rihanna";

        presenter.updateUsername(username);
        presenter.updatePassword(password);
        presenter.updatePassword(confPassword);

        presenter.register();


    }

    @Test
    public void update() {

    }
}