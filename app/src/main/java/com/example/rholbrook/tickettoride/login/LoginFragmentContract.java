package com.example.rholbrook.tickettoride.login;

import model.Message;

public class LoginFragmentContract {

    public interface View {
        void showToast(String message);
        void startRegisterFragment();
        void successfulLogin();
    }

    public interface Presenter {
        public Message login(String username, String password);

    }

}
