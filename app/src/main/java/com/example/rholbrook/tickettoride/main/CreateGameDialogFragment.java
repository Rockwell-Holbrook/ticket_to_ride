package com.example.rholbrook.tickettoride.main;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.rholbrook.tickettoride.R;

public class CreateGameDialogFragment extends DialogFragment {

    public interface CreateGameDialogInterface {
        public void onCreatePressed(DialogFragment dialog);
        public void onCancelPressed(DialogFragment dialog);
    }

    CreateGameDialogInterface mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (CreateGameDialogInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement Dialog Interface");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_create_game, null);
        EditText gameName = dialogView.findViewById(R.id.game_name_edit_text);
        CheckBox privateCheckbox = dialogView.findViewById(R.id.private_checkbox);
        Spinner playerNumberSpinner = dialogView.findViewById(R.id.player_number_spinner);
        ArrayAdapter<CharSequence> numberAdapter = ArrayAdapter.createFromResource(getContext(), R.array.player_number_array, android.R.layout.simple_spinner_item);
        numberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerNumberSpinner.setAdapter(numberAdapter);
        Spinner playerColorSpinner = dialogView.findViewById(R.id.player_color_spinner);
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(getContext(), R.array.player_colors_array, android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerColorSpinner.setAdapter(colorAdapter);
        builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.onCreatePressed(CreateGameDialogFragment.this);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.onCancelPressed(CreateGameDialogFragment.this);
            }
        });


        builder.setView(dialogView);
        return builder.create();
    }
}
