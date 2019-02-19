package com.example.rholbrook.tickettoride.main;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.Player;

import java.util.ArrayList;
import java.util.List;

public class CreateGameDialogFragment extends DialogFragment {

    public interface CreateGameDialogInterface {
        public void onCreatePressed(DialogFragment dialog, String gameName, int maxPlayers, Player.PlayerColor selectedColor);
        public void onCancelPressed(DialogFragment dialog);
        public void onCreateError(CreateGameDialogFragment createGameDialogFragment, String error);
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
        final EditText gameName = dialogView.findViewById(R.id.game_name_edit_text);
        final Spinner playerNumberSpinner = dialogView.findViewById(R.id.player_number_spinner);
        final ArrayAdapter<CharSequence> numberAdapter = ArrayAdapter.createFromResource(getContext(), R.array.player_number_array, android.R.layout.simple_spinner_item);
        numberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerNumberSpinner.setAdapter(numberAdapter);
        final Spinner playerColorSpinner = dialogView.findViewById(R.id.player_color_spinner);
        final List<Player.PlayerColor> availableColors = new ArrayList<>();
        getAvailableColors(availableColors);
        final ArrayList<String> colors = getColorStrings(availableColors);
        ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, colors);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerColorSpinner.setAdapter(colorAdapter);
        builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (gameName.getText().toString().equals("")) {
                    mListener.onCreateError(CreateGameDialogFragment.this, "Game Name not specified");
                } else {
                    mListener.onCreatePressed(CreateGameDialogFragment.this,
                            gameName.getText().toString(),
                            Integer.valueOf(playerNumberSpinner.getSelectedItem().toString()), availableColors.get(playerColorSpinner.getSelectedItemPosition()));
                }

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

    private void getAvailableColors(List<Player.PlayerColor> availableColors) {
        availableColors.add(Player.PlayerColor.BLUE);
        availableColors.add(Player.PlayerColor.GREEN);
        availableColors.add(Player.PlayerColor.RED);
        availableColors.add(Player.PlayerColor.YELLOW);
        availableColors.add(Player.PlayerColor.BLACK);
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
