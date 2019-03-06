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
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.Ticket;
import com.example.shared.model.TrainCard;

import java.util.List;

public class ViewTrainCardsDialogFragment extends DialogFragment{
    private List<TrainCard> playerCards;
    private Context context;
    ViewTrainCardsDialogFragment.ViewTrainCardsDialogInterface mListener;

    public interface ViewTrainCardsDialogInterface {
        public void onClosePressed(DialogFragment dialogFragment);
    }

    public static ViewTrainCardsDialogFragment newInstance(List<TrainCard> trainCards, Context context) {
        ViewTrainCardsDialogFragment fragment = new ViewTrainCardsDialogFragment();
        fragment.setPlayerTrainCards(trainCards);
        fragment.setContext(context);

        return fragment;
    }

    private void setContext(Context context) {
        this.context = context;
    }

    private void setPlayerTrainCards(List<TrainCard> trainCards) {
        this.playerCards = trainCards;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (ViewTrainCardsDialogInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement Dialog Interface");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_view_cards, null);

        final Button closeButton = dialogView.findViewById(R.id.view_cards_close_button);
        RecyclerView trainCardsRecyclerView = dialogView.findViewById(R.id.view_cards_recycler_view);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClosePressed(ViewTrainCardsDialogFragment.this);
            }
        });

        //Set Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        trainCardsRecyclerView.setLayoutManager(layoutManager);
        trainCardsRecyclerView.setAdapter(new TrainCardsAdapter(playerCards, context));

        builder.setView(dialogView);
        return builder.create();
    }
}
