package com.example.rholbrook.tickettoride.register;

import com.example.rholbrook.tickettoride.register.ListeningTask;
import com.example.shared.model.Message;

/**
 * Created by chocobj on 11/15/18.
 */

public class RegisterTask extends ListeningTask<Void, Void, Message> {
    @Override
    protected Message doInBackground(Void... voidParams) {
        RegisterContract.Presenter presenter = new RegisterPresenter();
        return presenter.register();
    }
}
