package com.example.rholbrook.tickettoride.login;

public class LoginFragmentContract {

    public interface View {
        void showToast(String message);
        void startRegisterFragment();
        void successfulLogin();
    }

    public interface Presenter {
        public void login(String username, String password);

    }

}
