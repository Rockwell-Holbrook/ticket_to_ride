package com.example.rholbrook.tickettoride.game;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.rholbrook.tickettoride.R;
import com.example.rholbrook.tickettoride.login.LoginFragment;
import com.example.rholbrook.tickettoride.login.LoginFragmentPresenter;

import java.util.List;

public class GameMapFragment extends Fragment implements GameMapFragmentContract.View {
    GameMapFragmentContract.Presenter mPresenter;

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
        mPresenter = new GameMapFragmentPresenter(this);
        return view;
    }

    @Override
    public void startUserTurn(List<Button> availableButtons) {
        for (Button button : availableButtons) {
            button.setEnabled(true);
            button.setBackground(getActivity().getDrawable(R.drawable.selectable_boute_border));
        }
    }

    @Override
    public void endUserTurn(List<Button> availableButtons) {
        for (Button button : availableButtons) {
            button.setEnabled(false);
            button.setBackground(null);
        }
    }

    @Override
    public void addClickListeners(Integer integer) {
        final int routeId = integer;
        Group routeGroup = getView().findViewById(integer);
        int[] routeButtons = routeGroup.getReferencedIds();
        for (int i = 0 ; i < routeButtons.length; i++) {
            Button button = getView().findViewById(routeButtons[i]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.selectRoute(routeId);
                }
            });
            mPresenter.addAvailableButton(button);
        }
    }
}
