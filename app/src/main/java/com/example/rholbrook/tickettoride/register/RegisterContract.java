package com.example.rholbrook.tickettoride.register;

import com.example.shared.model.Message;


import java.util.Observable;
import java.util.Observer;

/**
 * Created by chocobj on 2/4/19.
 */

public class RegisterContract {
    interface View {
        void onSuccess();
        void onFailure(String message);
    }

    interface Presenter extends Observer {
        void updateUsername(String username);
        void updatePassword(String password);
        void updateConfPassword(String password);
        void register();
      
        @Override
        void update(Observable o, Object arg){
        }

    }

    interface Presenter {
        void updateUsername(String username);
        void updatePassword(String password);
        void updateConfPassword(String password);
        Message register();
    }

}
