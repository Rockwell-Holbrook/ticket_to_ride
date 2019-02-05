package com.example.rholbrook.tickettoride.register;

import com.example.shared.model.Message;

/**
 * Created by chocobj on 2/4/19.
 */

public class RegisterPresenter implements RegisterContract.Presenter {
    RegisterModel mModel;

    public RegisterPresenter() {
        mModel = RegisterModel.getInstance();
    }

    @Override
    public Message register() {
        return mModel.register();
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
}
