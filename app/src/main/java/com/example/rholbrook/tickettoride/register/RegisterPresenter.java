package com.example.rholbrook.tickettoride.register;

import com.example.shared.model.Message;
import java.util.Observable;

/**
 * Created by chocobj on 2/4/19.
 */

public class RegisterPresenter implements RegisterContract.Presenter {
    RegisterModel mModel;
    RegisterContract.View mView;

    public RegisterPresenter(RegisterContract.View view) {
        mModel = RegisterModel.getInstance();
        mModel.setPresenter(this);
        mView = view;
    }

    @Override
    public void register() {
        mModel.register();
    }

    @Override
    public void updateConfPassword(String password) {
        mModel.setConfPassword(password);
    }

    @Override
    public void updatePassword(String password) {
        mModel.setPassword(password);
    }

    @Override
    public void updateUsername(String username) {
        mModel.setUsername(username);
    }

    @Override
    public void update(Observable o, Object arg) {
        Message message = (Message) arg;
        if (message.isSuccess()) {
            mView.onSuccess();
        } else {
            mView.onFailure(message.getMessage());
        }
    }
}
