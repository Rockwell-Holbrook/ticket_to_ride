package com.example.rholbrook.tickettoride.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.rholbrook.tickettoride.R;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.ViewHolder> {

    public GameListAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView currentPlayerNumber;
        TextView maxPlayerNumber;
        TextView gameName;
        TextView hostName;

        public ViewHolder(View itemView) {
            super(itemView);
            currentPlayerNumber = itemView.findViewById(R.id.current_player_count_text_view);
            maxPlayerNumber = itemView.findViewById(R.id.max_player_count_text_view);
            gameName = itemView.findViewById(R.id.game_name_text_view);
            hostName = itemView.findViewById(R.id.host_name_text_view);
        }
    }
}
