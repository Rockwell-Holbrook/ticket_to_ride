package com.example.rholbrook.tickettoride.login;


import com.example.shared.model.Message;

import java.util.Observable;
import java.util.Observer;

public class LoginFragmentPresenter implements LoginFragmentContract.Presenter {

    private LoginFragmentContract.View viewCallback;
    private LoginFragmentModel mModel;


    LoginFragmentPresenter(LoginFragmentContract.View viewCallback){
        this.viewCallback = viewCallback;
        mModel = LoginFragmentModel.getInstance();
    }

    LoginFragmentPresenter() {
        mModel = LoginFragmentModel.getInstance();
    }

    private void initialize() {
        mModel.addObserver((Observer) this);
    }

    public Message login() {
        return mModel.login();
    }

    @Override
    public void updateUsername(String username) {
        mModel.setUsername(username);
    }

    @Override
    public void updatePassword(String password) {
        mModel.setPassword(password);
    }

    public void successfulLogin() {

    }
}
