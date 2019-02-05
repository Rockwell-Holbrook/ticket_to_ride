package com.example.rholbrook.tickettoride.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rholbrook.tickettoride.R;

public class LoginFragment extends Fragment implements LoginFragmentContract.View {
    private LoginFragmentContract.Presenter mPresenter;

    private EditText mUsernameField;
    private EditText mPasswordField;
    private Button mLoginButton;
    private TextView mRegister;


    // onCreate: when the app begins, the code below is run
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new LoginFragmentPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mUsernameField = view.findViewById(R.id.usernameInput);
        mPasswordField = view.findViewById(R.id.passwordInput);
        mLoginButton = view.findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.login(mUsernameField.getText().toString(), mPasswordField.getText().toString());

            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // bring up Blaine's Fragment
                // Fragment fragment = RegisterFragment.newInstance();
                // getFragmentManager().beginTransaction().replace(R.id.authentication_fragment_container, RegisterFragment.class).commit();
            }

        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startRegisterFragment() {

    }

    @Override
    public void successfulLogin() {

    }
}
