package com.example.rholbrook.tickettoride.finishgame;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.Player;
import com.example.shared.model.Ticket;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class FinishGamePlayersAdapter extends RecyclerView.Adapter<FinishGamePlayersAdapter.ViewHolder>{
    ArrayList<Player> players;

    public FinishGamePlayersAdapter(ArrayList<Player> players) {
        this.players = players;
        for (Player player : players) {
            player.setCompletedTicketCount(player.getCompletedTicketCount());
            player.setIncompletedTicketCount(player.getIncompleteTicketCount());
        }
        for (Player player : players) {
            player.calculateEndGame(players);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_finish_player, viewGroup, false);
        return new FinishGamePlayersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Player player = players.get(viewHolder.getAdapterPosition());
        viewHolder.usernameTextView.setText(player.getUsername());
        viewHolder.routesPointsTextView.setText(String.valueOf(player.getPointsEarned()));
        viewHolder.ticketPointsTextView.setText(String.valueOf(player.getTicketPoints()));
        viewHolder.completedTicketCountTextView.setText(String.valueOf(player.getCompletedCount()));
        viewHolder.incompleteTicketCountTextView.setText(String.valueOf(player.getIncompletedTicketCount()));
        viewHolder.completedTicketsPointsTextView.setText(String.valueOf(player.getCompletedTicketPoints()));
        viewHolder.incompleteTicketsPointsTextView.setText(String.valueOf(player.getIncompleteTicketPoints()));
        viewHolder.bonusPointsTextView.setText(String.valueOf(player.getBonusPoints()));
        viewHolder.totalPointsTextView.setText(String.valueOf(player.getTotalPoints()));
        if (player.isHasGlobeTrotter()) {
            viewHolder.globetrotterImageView.setVisibility(View.VISIBLE);
            viewHolder.noBonusTextView.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.globetrotterImageView.setVisibility(GONE);
        }
        if (player.isHasLongestRoute()) {
            viewHolder.longestRouteImageView.setVisibility(View.VISIBLE);
            viewHolder.noBonusTextView.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.longestRouteImageView.setVisibility(GONE);
        }

        if (!isWinner(player)) {
            viewHolder.winnerTextView.setVisibility(View.INVISIBLE);
        }
        viewHolder.userAvatarImageView.setImageResource(getAvatarResource(player));
    }

    private int getAvatarResource(Player player) {
        switch(player.getPlayerColor()) {
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

    private boolean isWinner(Player player) {
        for (Player secondPlayer : players) {
            if (secondPlayer.getTotalPoints() > player.getTotalPoints()) {
                return false;
            }
        }
        return true;
    }


    @Override
    public int getItemCount() {
        return players.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView winnerTextView;
        TextView usernameTextView;
        TextView totalPointsTextView;
        TextView routesPointsTextView;
        TextView completedTicketCountTextView;
        TextView incompleteTicketCountTextView;
        TextView ticketPointsTextView;
        TextView bonusPointsTextView;
        TextView completedTicketsPointsTextView;
        TextView incompleteTicketsPointsTextView;
        TextView noBonusTextView;
        ImageView longestRouteImageView;
        ImageView globetrotterImageView;
        ImageView userAvatarImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            winnerTextView = itemView.findViewById(R.id.winner_text_view);
            usernameTextView = itemView.findViewById(R.id.finish_game_username);
            totalPointsTextView = itemView.findViewById(R.id.total_points_text_view);
            routesPointsTextView = itemView.findViewById(R.id.routes_points_text_view);
            completedTicketCountTextView = itemView.findViewById(R.id.completed_tickets_count);
            incompleteTicketCountTextView = itemView.findViewById(R.id.incompleted_tickets_count);
            ticketPointsTextView = itemView.findViewById(R.id.ticket_points_text_view);
            bonusPointsTextView = itemView.findViewById(R.id.bonus_points_text_view);
            completedTicketsPointsTextView = itemView.findViewById(R.id.completed_ticket_points_text_view);
            incompleteTicketsPointsTextView = itemView.findViewById(R.id.incomplete_points_text_view);
            noBonusTextView = itemView.findViewById(R.id.no_bonuses_text_view);
            longestRouteImageView = itemView.findViewById(R.id.longest_route_award);
            globetrotterImageView = itemView.findViewById(R.id.globetrotter_award);
            userAvatarImageView = itemView.findViewById(R.id.player_avatar);
        }
    }
}
