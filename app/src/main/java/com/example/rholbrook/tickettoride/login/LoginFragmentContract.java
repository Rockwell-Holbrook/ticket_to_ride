package com.example.rholbrook.tickettoride.login;

import com.example.shared.model.Message;

public class LoginFragmentContract {

    public interface View {
        void showToast(String message);
        void startRegisterFragment();
        void successfulLogin();
    }

    public interface Presenter {
        Message login();
        void updateUsername(String username);
        void updatePassword(String password);
    }

}
