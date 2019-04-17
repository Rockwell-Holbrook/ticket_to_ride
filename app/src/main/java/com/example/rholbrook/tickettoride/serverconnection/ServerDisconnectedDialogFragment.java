package com.example.rholbrook.tickettoride.serverconnection;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.rholbrook.tickettoride.R;
import com.example.rholbrook.tickettoride.main.MainActivityModel;
import com.example.shared.model.Player;

import java.util.ArrayList;
import java.util.List;

public class ServerDisconnectedDialogFragment extends DialogFragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_server_disconnected, null);

        builder.setView(dialogView);
        return builder.create();
    }
}
