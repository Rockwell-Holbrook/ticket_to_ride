package com.example.rholbrook.tickettoride.game;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.rholbrook.tickettoride.R;
import com.example.rholbrook.tickettoride.login.LoginFragment;
import com.example.rholbrook.tickettoride.login.LoginFragmentPresenter;

public class GameMapFragment extends Fragment implements GameMapFragmentContract.View {

    public static GameMapFragment newInstance() {
        GameMapFragment fragment = new GameMapFragment();
        Bundle params = new Bundle();

        fragment.setArguments(params);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_game_map, container, false);
        return view;
    }

    @Override
    public void startUserTurn() {
        //enable all click listeners
    }

    @Override
    public void endUserTurn() {
        //disable all click listeners
    }
}
