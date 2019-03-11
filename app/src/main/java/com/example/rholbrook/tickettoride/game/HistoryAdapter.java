package com.example.rholbrook.tickettoride.game;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.GameHistory;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<GameHistory> gameHistory;

    public HistoryAdapter(List<GameHistory> gameHistory) {
        this.gameHistory = gameHistory;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_history, viewGroup, false);
        return new HistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder viewHolder, int i) {
        final GameHistory history = gameHistory.get(viewHolder.getAdapterPosition());
        viewHolder.username.setText(history.getUsername());
        viewHolder.action.setText(history.getHistory());
    }

    @Override
    public int getItemCount() {
        return gameHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView action;

        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.history_username);
            action = itemView.findViewById(R.id.history_action);
        }
    }
}
