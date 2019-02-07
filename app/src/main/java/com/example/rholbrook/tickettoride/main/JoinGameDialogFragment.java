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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.Player;

import java.util.ArrayList;
import java.util.List;

public class JoinGameDialogFragment extends DialogFragment {

    public interface JoinGameDialogInterface {
        public void onJoinPressed(DialogFragment dialog);
        public void onCancelPressed(DialogFragment dialog);
    }

    JoinGameDialogInterface mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (JoinGameDialogInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement Dialog Interface");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ArrayList<String> availableColors = getColorStrings(MainActivityModel.getInstance().getSelectedGame().getAvailableColors());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_join_game, null);
        TextView gameName = dialogView.findViewById(R.id.join_game_name_text_view);
        gameName.setText(MainActivityModel.getInstance().getSelectedGame().getGameName());
        Spinner playerColorSpinner = dialogView.findViewById(R.id.player_color_spinner);
        ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, availableColors);
        playerColorSpinner.setAdapter(colorAdapter);
        builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.onJoinPressed(JoinGameDialogFragment.this);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.onCancelPressed(JoinGameDialogFragment.this);
            }
        });


        builder.setView(dialogView);
        return builder.create();
    }

    private ArrayList<String> getColorStrings(List<Player.PlayerColor> availableColors) {
        ArrayList<String> colors = new ArrayList<>();
        for (Player.PlayerColor color : availableColors) {
            switch(color) {
                case YELLOW:
                    colors.add("Yellow");
                    break;
                case GREEN:
                    colors.add("Green");
                    break;
                case BLACK:
                    colors.add("Black");
                    break;
                case BLUE:
                    colors.add("Blue");
                    break;
                case RED:
                    colors.add("Red");
                    break;
                default:
                    break;
            }
        }
        return colors;
    }
}
