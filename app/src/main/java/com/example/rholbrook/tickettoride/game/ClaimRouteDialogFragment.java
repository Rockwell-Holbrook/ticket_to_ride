package com.example.rholbrook.tickettoride.game;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.Route;
import com.example.shared.model.TrainCard;

import java.util.ArrayList;
import java.util.List;

public class ClaimRouteDialogFragment extends DialogFragment implements
        ClaimRouteHandAdapter.ClaimRouteHandAdapterInterface {
    List<TrainCard> playerCards;
    List<TrainCard> selectedCards;
    ClaimRouteDialogFragmentInterface mListener;
    Route route;
    TextView instructionTextView;
    RecyclerView cardRecyclerView;
    Button cancelButton;
    Button submitButton;

    @Override
    public void selectCard(TrainCard card) {
        selectedCards.add(card);
        if (selectedCards.size() == route.getLength()) {
            submitButton.setEnabled(true);
        } else {
            submitButton.setEnabled(false);
        }
    }

    @Override
    public void deselectCard(TrainCard card) {
        selectedCards.remove(card);
        if (selectedCards.size() == route.getLength()) {
            submitButton.setEnabled(true);
        } else {
            submitButton.setEnabled(false);
        }
    }

    public interface ClaimRouteDialogFragmentInterface {
        void onCancelPressed(DialogFragment dialog);
        void onClaimRoutePressed(DialogFragment dialog, List<TrainCard> selectedCards, Route route);
    }

    public static ClaimRouteDialogFragment newInstance(List<TrainCard> possibleCards, Route route) {
        ClaimRouteDialogFragment fragment = new ClaimRouteDialogFragment();
        Bundle params = new Bundle();
        fragment.setArguments(params);

        fragment.selectedCards = new ArrayList<>();
        fragment.playerCards = possibleCards;
        fragment.route = route;

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (ClaimRouteDialogFragmentInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement Dialog Interface");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_claim_route, null);
        instructionTextView = dialogView.findViewById(R.id.create_instructions_text_view);
        cardRecyclerView = dialogView.findViewById(R.id.view_cards_recycler_view);
        cancelButton = dialogView.findViewById(R.id.cancel_button);
        submitButton = dialogView.findViewById(R.id.view_cards_close_button);

        if (route != null) {
            String instructions = "You must choose " + route.getLength() + " " + getColorString(route.getColor()) + " cards";
            instructionTextView.setText(instructions);
        }

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCancelPressed(ClaimRouteDialogFragment.this);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClaimRoutePressed(ClaimRouteDialogFragment.this, selectedCards, route);
            }
        });
        submitButton.setEnabled(false);

        //Set Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        cardRecyclerView.setAdapter(new ClaimRouteHandAdapter(playerCards, getContext(), route, this));
        cardRecyclerView.setLayoutManager(layoutManager);


        builder.setView(dialogView);
        return builder.create();
    }

    private String getColorString(Route.RouteColor color) {
        switch (color) {
            case RED:
                return "Red";
            case BLUE:
                return "Blue";
            case BLACK:
                return "Black";
            case YELLOW:
                return "Yellow";
            case GREEN:
                return "Green";
            case PINK:
                return "Pink";
            case WHITE:
                return "White";
            case ORANGE:
                return "Orange";
            default:
                return "Matching";
        }
    }


}
