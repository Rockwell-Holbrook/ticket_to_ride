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

import java.util.List;

public class LastRoundDialogFragment extends DialogFragment {

    public interface LastRoundDialogFragmentInterface {
        public void onClosePressed(DialogFragment dialogFragment);
    }

    LastRoundDialogFragment.LastRoundDialogFragmentInterface mListener;

    public static LastRoundDialogFragment newInstance() {
        LastRoundDialogFragment fragment = new LastRoundDialogFragment();

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (LastRoundDialogFragmentInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement Dialog Interface");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_last_round, null);

        final Button closeButton = dialogView.findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClosePressed(LastRoundDialogFragment.this);
            }
        });

        builder.setView(dialogView);
        return builder.create();
    }
}
