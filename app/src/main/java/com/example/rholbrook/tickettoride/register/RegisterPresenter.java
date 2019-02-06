package com.example.rholbrook.tickettoride.register;

import com.example.shared.model.Message;

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

    public void onSuccess() {
        mView.onSuccess();
    }

    public void onFailure(String message) {
        mView.onFailure(message);
    }
}
