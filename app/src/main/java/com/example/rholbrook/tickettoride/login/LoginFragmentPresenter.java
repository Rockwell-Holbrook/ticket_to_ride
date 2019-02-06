package com.example.rholbrook.tickettoride.login;

import model.Message;

import java.util.Observable;
import java.util.Observer;

public class LoginFragmentPresenter extends Observable implements LoginFragmentContract.Presenter {

    private LoginFragmentContract.View viewCallback;
    private LoginFragmentModel mModel;

    LoginFragmentPresenter(LoginFragmentContract.View viewCallback){
        this.viewCallback = viewCallback;
        mModel = LoginFragmentModel.getInstance();
    }

    private void initialize() {
        mModel.addObserver((Observer) this);
    }

    public Message login(String username, String password) {

        try {
            return mModel.login(username, password);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            viewCallback.showToast(throwable.getMessage());
            return new Message(false, throwable.getMessage());
        }
    }

    public void successfulLogin() {

    }

}
