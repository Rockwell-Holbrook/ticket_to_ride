package com.example.rholbrook.tickettoride.register;

import com.example.shared.model.Message;

/**
 * Created by chocobj on 2/4/19.
 */

public class RegisterContract {
    interface View {

    }

    interface Presenter {
        void updateUsername(String username);
        void updatePassword(String password);
        void updateConfPassword(String password);
        Message register();
    }

}
