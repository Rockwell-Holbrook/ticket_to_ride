package com.example.rholbrook.tickettoride.register;

import com.example.rholbrook.tickettoride.serverconnection.AuthenticationServerProxy;
import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Message;
import com.example.shared.model.User;

import java.util.Objects;
import java.util.TreeSet;

/**
 * Created by chocobj on 2/4/19.
 */

public class RegisterModel {
    private static final RegisterModel ourInstance = new RegisterModel();
    private RegisterContract.Presenter mPresenter;

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
            try {
                User user = new User(username, password);
                RegisterTask registerTask = new RegisterTask();
                registerTask.setListener(new ListeningTask.Listener() {
                    @Override
                    public void onComplete(Object result) {
                        Message message = (Message) result;
                        if (message.isSuccess()) {
                            ServerProxy.getInstance().connectToManagementSocket(username);
                            mPresenter.onSuccess();
                        } else {
                            mPresenter.onFailure(message.getMessage());
                        }
                    }
                });
                registerTask.execute(user);
            } catch (Throwable e) {
                mPresenter.onFailure(e.getMessage());
            }
        } else {
            mPresenter.onFailure("Passwords do not match!");
        }
    }

    private boolean passwordsMatch() {
            if (Objects.equals(password, confPassword)) {
                return true;
            }
        return false;
    }

    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
