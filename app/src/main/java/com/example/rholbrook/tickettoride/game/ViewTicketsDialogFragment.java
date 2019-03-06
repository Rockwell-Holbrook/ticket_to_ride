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

public class ViewTicketsDialogFragment extends DialogFragment {
    private List<Ticket> playerTickets;

    public interface ViewTicketsDialogInterface {
        public void onClosePressed(DialogFragment dialogFragment);
    }

    ViewTicketsDialogFragment.ViewTicketsDialogInterface mListener;

    public static ViewTicketsDialogFragment newInstance(List<Ticket> tickets) {
        ViewTicketsDialogFragment fragment = new ViewTicketsDialogFragment();
        fragment.setPlayerTickets(tickets);

        return fragment;
    }

    private void setPlayerTickets(List<Ticket> tickets) {
        this.playerTickets = tickets;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (ViewTicketsDialogInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement Dialog Interface");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_view_tickets, null);

        final Button closeButton = dialogView.findViewById(R.id.view_tickets_close_button);
        RecyclerView ticketsRecyclerView = dialogView.findViewById(R.id.view_tickets_recycler_view);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClosePressed(ViewTicketsDialogFragment.this);
            }
        });

        //Set Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ticketsRecyclerView.setLayoutManager(layoutManager);
        ticketsRecyclerView.setAdapter(new TicketsAdapter(playerTickets));

        builder.setView(dialogView);
        return builder.create();
    }
}
