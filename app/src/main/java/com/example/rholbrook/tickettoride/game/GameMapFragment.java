package com.example.rholbrook.tickettoride.game;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.Route;
import com.example.shared.model.TrainCard;

import java.util.List;

public class GameMapFragment extends Fragment implements
        GameMapFragmentContract.View {
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
        final List<Button> buttons = availableButtons;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
            for (Button button : buttons) {
                button.setEnabled(true);
                button.setBackground(getActivity().getDrawable(R.drawable.selectable_route_border));
            }
            }
        });

    }

    @Override
    public void endUserTurn(List<Button> availableButtons) {
        final List<Button> buttons = availableButtons;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
            for (Button button : buttons) {
                button.setEnabled(false);
                button.setBackground(null);
            }
            }
        });
    }

    @Override
    public void addClickListeners(Integer integer, final int groupId) {
        final int routeId = integer;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
            Group routeGroup = getView().findViewById(routeId);
            int[] routeButtons = routeGroup.getReferencedIds();
            for (int i = 0 ; i < routeButtons.length; i++) {
                Button button = getView().findViewById(routeButtons[i]);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClaimRouteDialogFragment dialog = ClaimRouteDialogFragment.newInstance(mPresenter.getPlayerHand(), Route.ROUTE_GROUP_MAP.get(groupId));
                        dialog.setCancelable(false);
                        dialog.show(getActivity().getSupportFragmentManager(), "Claim Route Dialog Fragment");
                    }
                });
                mPresenter.addAvailableButton(button);
            }
            }
        });

    }

    @Override
    public void routeClaimed(int color, int route) {
        final int selecterColor = color;
        final int selectedRoute = route;

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Group routeGroup = getView().findViewById(selectedRoute);
                int[] buttons = routeGroup.getReferencedIds();
                for (int i = 0; i < buttons.length; i++){
                    Button button = getView().findViewById(buttons[i]);
                    button.setBackgroundColor(getResources().getColor(selecterColor));
                }
            }
        });
    }
}
