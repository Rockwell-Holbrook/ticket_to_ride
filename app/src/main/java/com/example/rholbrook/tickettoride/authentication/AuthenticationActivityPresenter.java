package com.example.rholbrook.tickettoride.authentication;

public class AuthenticationActivityPresenter implements AuthenticationActivityContract.Presenter {
    private AuthenticationActivityContract.View viewCallback;
    private AuthenticationActivityModel mModel;

    public AuthenticationActivityPresenter(AuthenticationActivityContract.View authenticationActivity) {
        viewCallback = authenticationActivity;
        mModel = AuthenticationActivityModel.getInstance();
    }

    @Override
    public void init() {

    }
}
