package com.example.rholbrook.tickettoride.register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shared.model.Message;

/**
 * Created by Blaine Johnson on 1/29/19.
 */

public class RegisterFragment extends Fragment implements RegisterContract.View {
    // TODO: Is there a way to avoid referring to RegisterPresenter at all? //
    private RegisterContract.Presenter mPresenter = new RegisterPresenter(this);

    private Button mRegisterButton;
    private EditText mUsernameField;
    private EditText mPasswordField;
    private EditText mConfPasswordField;

    private boolean usernameFilled;
    private boolean passwordFilled;
    private boolean confPasswordFilled;

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle paramas = new Bundle();

        fragment.setArguments(paramas);

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

        mUsernameField = v.findViewById(R.id.register_username_field, container, false);
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

        mPasswordField = v.findViewById(R.id.register_password_field, container, false);
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

        mConfPasswordField = v.findViewById(R.id.register_conf_password_field, container, false);
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
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.register();
            }
        });
        return v;
    }

    private void onFieldsChanged() {
        if (usernameFilled && passwordFilled && confPasswordFilled) {
            mRegisterButton.setEnabled(true);
        }
        else {
            mRegisterButton.setEnabled(false);
        }
    }

    public void onSuccess() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(RegisterFragment.this).commit();
    }

    public void onFailure(String message) {
        showToast(message);
    }

    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}

