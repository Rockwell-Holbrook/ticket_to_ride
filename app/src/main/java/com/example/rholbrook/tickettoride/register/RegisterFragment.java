package com.example.rholbrook.tickettoride.register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rholbrook.tickettoride.authentication.AuthenticationActivityModel;
import com.example.shared.model.Message;
import com.example.rholbrook.tickettoride.R;

/**
 * Created by Blaine Johnson on 1/29/19.
 */

public class RegisterFragment extends Fragment implements RegisterContract.View {
    private RegisterContract.Presenter mPresenter = new RegisterPresenter(this);
    private static final int SUCCESSFUL_AUTHENTICATION = 1;
    private static final int SWITCH_TO_LOGIN = 2;
    private Button mRegisterButton;
    private EditText mUsernameField;
    private EditText mPasswordField;
    private EditText mConfPasswordField;
    private AuthenticationActivityModel.CallBack callback;
    private boolean usernameFilled;
    private boolean passwordFilled;
    private boolean confPasswordFilled;
    private Listener mListener;
    private int authenticationStatus;
    private CountingIdlingResource idlingResource;

    public interface Listener {
        void onLogin();
    }

    public static RegisterFragment newInstance(CountingIdlingResource authenticationIdlingResource) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle paramas = new Bundle();

        fragment.setArguments(paramas);

        fragment.idlingResource = authenticationIdlingResource;

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        mUsernameField = v.findViewById(R.id.register_username_field);
        mUsernameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action required
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    usernameFilled = false;
                } else {
                    usernameFilled = true;
                    mPresenter.updateUsername(s.toString());
                }
                onFieldsChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No action required
            }

        });

        mPasswordField = v.findViewById(R.id.register_password_field);
        mPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    passwordFilled = false;
                } else {
                    passwordFilled = true;
                    mPresenter.updatePassword(s.toString());
                }
                onFieldsChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // no action needed
            }
        });

        mConfPasswordField = v.findViewById(R.id.register_conf_password_field);
        mConfPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    confPasswordFilled = false;
                } else {
                    confPasswordFilled = true;
                    mPresenter.updateConfPassword(s.toString());
                }
                onFieldsChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // no action needed
            }
        });

        mRegisterButton = v.findViewById(R.id.register_button);
        mRegisterButton.setEnabled(false);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idlingResource.increment();
                mPresenter.updateUsername(mUsernameField.getText().toString());
                mPresenter.updatePassword(mPasswordField.getText().toString());
                mPresenter.updateConfPassword(mConfPasswordField.getText().toString());
                mPresenter.register();
            }
        });

        authenticationStatus = SWITCH_TO_LOGIN;
        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (callback != null) {
            callback.onCall(authenticationStatus);
        }
    }


    public void onSuccess() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                authenticationStatus = SUCCESSFUL_AUTHENTICATION;
                getActivity().getSupportFragmentManager().beginTransaction().remove(RegisterFragment.this).commit();
                idlingResource.decrement();
            }
        });
    }

    public void onFailure(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showToast(message);
                idlingResource.decrement();
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    private void onFieldsChanged() {
        if (usernameFilled && passwordFilled && confPasswordFilled) {
            mRegisterButton.setEnabled(true);
        }
        else {
            mRegisterButton.setEnabled(false);
        }
    }

    private void register() {
        RegisterTask registerTask = new RegisterTask();
        registerTask.setListener(new ListeningTask.Listener() {
            @Override
            public void onComplete(Object result) {
                Message message = (Message) result;
                checkStatus(message);
            }
        });
        registerTask.execute();
    }

    private void checkStatus(final Message message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (message.isSuccess()) {
                    onSuccess();
                }
                else {
                    onFailure(message.getMessage());
                }
            }
        });

    }

    public AuthenticationActivityModel.CallBack getCallback() {
        return callback;
    }

    public void setCallback(AuthenticationActivityModel.CallBack callback) {
        this.callback = callback;
    }
}

