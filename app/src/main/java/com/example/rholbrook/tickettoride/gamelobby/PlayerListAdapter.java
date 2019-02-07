package com.example.rholbrook.tickettoride.gamelobby;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.ViewHolder> {
    ArrayList<Player> players;
    GameLobbyActivity mListener;

    public PlayerListAdapter(ArrayList<Player> players, GameLobbyActivity mListener) {
        this.players = players;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_game, viewGroup, false);
        return new PlayerListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Player player = players.get(viewHolder.getAdapterPosition());
        if ((viewHolder.getAdapterPosition() % 2) == 0) {
            viewHolder.constraintLayout.setBackgroundResource(R.color.gameListEntryBackgroundEven);
        } else {
            viewHolder.constraintLayout.setBackgroundResource(R.color.gameListEntryBackgroundOdd);
        }
        viewHolder.playerAvatar.setImageResource(getAvatar(player.getColor()));
        viewHolder.playerName.setText(player.getUsername());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView playerAvatar;
        TextView playerName;
        ConstraintLayout constraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.player_list_item_view);
            playerAvatar = itemView.findViewById(R.id.player_list_image_view);
            playerName = itemView.findViewById(R.id.player_list_username_text_view);
        }
    }

    public int getAvatar(Player.PlayerColor color) {
        switch(color) {
            case RED:
                return R.mipmap.red_player;
            case BLUE:
                return R.mipmap.blue_player;
            case BLACK:
                return R.mipmap.black_player;
            case GREEN:
                return R.mipmap.green_player;
            case YELLOW:
                return R.mipmap.yellow_player;
            default:
                return 0;
        }
    }
}
