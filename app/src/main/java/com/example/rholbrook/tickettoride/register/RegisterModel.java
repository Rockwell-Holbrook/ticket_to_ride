package com.example.rholbrook.tickettoride.register;

import com.example.rholbrook.tickettoride.main.Authentication;
import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Message;
import com.example.shared.model.User;

import java.util.Objects;
import java.util.Observable;


/**
 * Created by chocobj on 2/4/19.
 */


public class RegisterModel extends Observable {

    private static final RegisterModel ourInstance = new RegisterModel();

    private String username;
    private String password;
    private String confPassword;

    public static RegisterModel getInstance() {
        return ourInstance;
    }

    private RegisterModel() {
        username = null;
        password = null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public void register() {
        if (passwordsMatch()) {
            startRegisterTask();
        } else {
            notifyObservers(new Message(false, "Passwords do not match!"));
        }
    }

    private boolean passwordsMatch() {
            if (Objects.equals(password, confPassword)) {
                return true;
            }
        return false;
    }

    public void setPresenter(RegisterContract.Presenter presenter) {
       addObserver(presenter);
    }

    private void startRegisterTask() {
        try {
            final User user = new User(username, password);
            RegisterTask registerTask = new RegisterTask();
            registerTask.setListener(new ListeningTask.Listener() {
                @Override
                public void onComplete(Object result) {
                    Message message = (Message) result;
                    if (message.isSuccess()) {
                        ServerProxy.getInstance().connectToManagementSocket(username);
                        Authentication.getInstance().setUser(user);
                    }
                    notifyObservers(message);
                }
            });
            registerTask.execute(user);
        } catch (Throwable e) {
            notifyObservers(new Message(false, e.getMessage()));
        }
    }
}
