package com.example.rholbrook.tickettoride.login;

import com.example.rholbrook.tickettoride.register.ListeningTask;
import com.example.shared.model.Message;

/**
 * Created by chocobj on 11/15/18.
 */

public class LoginTask extends ListeningTask<Void, Void, Message> {
    @Override
    protected Message doInBackground(Void... voidParams) {
        LoginFragmentContract.Presenter presenter = new LoginFragmentPresenter();
        return presenter.login();
    }
}
