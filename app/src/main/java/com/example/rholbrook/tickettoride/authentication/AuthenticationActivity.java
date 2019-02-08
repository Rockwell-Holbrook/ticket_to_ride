package com.example.rholbrook.tickettoride.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import com.example.rholbrook.tickettoride.R;
import com.example.rholbrook.tickettoride.login.LoginFragment;
import com.example.rholbrook.tickettoride.main.MainActivity;
import com.example.rholbrook.tickettoride.register.RegisterFragment;

public class AuthenticationActivity extends AppCompatActivity implements
        AuthenticationActivityContract.View,
        AuthenticationActivityModel.CallBack {
    private FrameLayout fragmentContainer;
    private AuthenticationActivityContract.Presenter mPresenter;
    private static final int AUTHENTICATION_SUCCESS = 1;
    private static final int SWITCH_FRAGMENT = 0;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_authentication);
        fragmentContainer = findViewById(R.id.authentication_fragment_container);

        mPresenter = new AuthenticationActivityPresenter(this);
        mPresenter.init();

        Fragment loginFragment = LoginFragment.newInstance();
        ((LoginFragment) loginFragment).setCallback(this);
        getSupportFragmentManager().beginTransaction().add(R.id.authentication_fragment_container, loginFragment).commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onCall(int key) {
        switch (key) {
            case AUTHENTICATION_SUCCESS:
                //Switch to Main
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case SWITCH_FRAGMENT:
                Fragment registerFragment = RegisterFragment.newInstance();
                ((RegisterFragment) registerFragment).setCallback(this);
                getSupportFragmentManager().beginTransaction().add(R.id.authentication_fragment_container, registerFragment).commit();
                break;
        }
    }
}
