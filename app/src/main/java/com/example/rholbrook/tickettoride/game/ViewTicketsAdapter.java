package com.example.rholbrook.tickettoride.game;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.Ticket;
import com.example.shared.model.TrainCard;

import java.util.List;

public class ViewTicketsAdapter extends RecyclerView.Adapter<ViewTicketsAdapter.ViewHolder>{
    List<Ticket> tickets;
    Context context;

    public ViewTicketsAdapter(List<Ticket> tickets, Context context) {
        this.tickets = tickets;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card_view, viewGroup, false);
        return new ViewTicketsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.cardImage.setImageResource(GameActivityPresenter.TICKET_IMAGE_MAP.get((tickets.get(viewHolder.getAdapterPosition())).getTicketId()));
        if (tickets.get(viewHolder.getAdapterPosition()).isCompleted()) {
            viewHolder.checkMark.setVisibility(View.VISIBLE);
        } else {
            viewHolder.checkMark.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cardImage;
        ImageView checkMark;

        public ViewHolder(View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.ticket_card_image_view);
            checkMark = itemView.findViewById(R.id.ticket_completed_check_mark);
        }
    }
}
