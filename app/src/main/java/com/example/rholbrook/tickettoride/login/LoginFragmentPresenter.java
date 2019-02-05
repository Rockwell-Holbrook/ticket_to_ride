package com.example.rholbrook.tickettoride.login;

public class LoginFragmentPresenter implements LoginFragmentContract.Presenter {

    private LoginFragmentContract.View viewCallback;
    private LoginFragmentModel mModel;

    LoginFragmentPresenter(LoginFragmentContract.View viewCallback){
        this.viewCallback = viewCallback;
        mModel = LoginFragmentModel.getInstance();
    }

    private void initialize() {

    }

    public void login(String username, String password) {

        try {
            mModel.login(username, password);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            viewCallback.showToast(throwable.getMessage());
        }
    }

}
