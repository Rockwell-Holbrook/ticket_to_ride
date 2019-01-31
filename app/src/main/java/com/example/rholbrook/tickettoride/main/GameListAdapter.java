package com.example.rholbrook.tickettoride.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.rholbrook.tickettoride.R;
import model.Game;

import java.util.List;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.ViewHolder> {
    List<Game> games;
    MainActivity mListener;

    public GameListAdapter(List<Game> games, MainActivity mListener) {
        this.games = games;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_game, viewGroup, false);
        return new GameListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Game game = games.get(viewHolder.getAdapterPosition());
        int currentPlayerNumber = game.getCurrentPlayers();
        int maxPlayerNumber = game.getMaxPlayers();
        String gameName = game.getGameName();
        String hostName = game.getHost().getUsername();
        viewHolder.currentPlayerNumber.setText(String.valueOf(currentPlayerNumber));
        viewHolder.maxPlayerNumber.setText(String.valueOf(maxPlayerNumber));
        viewHolder.gameName.setText(gameName);
        viewHolder.hostName.setText(hostName);
    }

    @Override
    public int getItemCount() {
        return games.size();
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
