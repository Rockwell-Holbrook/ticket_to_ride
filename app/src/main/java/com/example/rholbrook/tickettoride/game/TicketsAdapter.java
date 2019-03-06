package com.example.rholbrook.tickettoride.game;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.ViewHolder>{
    List<Ticket> tickets;

    public TicketsAdapter(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card_view, viewGroup, false);
        return new TicketsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.ticketImage.setImageResource(GameActivityPresenter.TICKET_IMAGE_MAP.get((tickets.get(viewHolder.getAdapterPosition())).getTicketId()));
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ticketImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ticketImage = itemView.findViewById(R.id.player_card_image_view);
        }
    }
}
