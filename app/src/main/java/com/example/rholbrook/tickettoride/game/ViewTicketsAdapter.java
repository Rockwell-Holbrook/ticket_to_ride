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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_ticket_view, viewGroup, false);
        return new ViewTicketsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Ticket ticket = this.tickets.get(viewHolder.getAdapterPosition());
        viewHolder.cardImage.setImageResource(this.TICKET_IMAGE_MAP.get(ticket.getTicketId()));
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

    public static final Map<Integer, Integer> TICKET_IMAGE_MAP;
    static {
        TICKET_IMAGE_MAP = new HashMap<>();
        TICKET_IMAGE_MAP.put(Integer.valueOf(1), R.mipmap.los_angeles_new_york);
        TICKET_IMAGE_MAP.put(Integer.valueOf(2), R.mipmap.duluth_houston);
        TICKET_IMAGE_MAP.put(Integer.valueOf(3), R.mipmap.sault_st_marie_nashville);
        TICKET_IMAGE_MAP.put(Integer.valueOf(4), R.mipmap.new_york_atlanta);
        TICKET_IMAGE_MAP.put(Integer.valueOf(5), R.mipmap.portland_nashville);
        TICKET_IMAGE_MAP.put(Integer.valueOf(6), R.mipmap.vancouver_montreal);
        TICKET_IMAGE_MAP.put(Integer.valueOf(7), R.mipmap.duluth_el_paso);
        TICKET_IMAGE_MAP.put(Integer.valueOf(8), R.mipmap.toronto_miami);
        TICKET_IMAGE_MAP.put(Integer.valueOf(9), R.mipmap.portland_phoenix);
        TICKET_IMAGE_MAP.put(Integer.valueOf(10), R.mipmap.dallas_new_york);
        TICKET_IMAGE_MAP.put(Integer.valueOf(11), R.mipmap.calgary_salt_lake_city);
        TICKET_IMAGE_MAP.put(Integer.valueOf(12), R.mipmap.calgary_phoenix);
        TICKET_IMAGE_MAP.put(Integer.valueOf(13), R.mipmap.los_angeles_miami);
        TICKET_IMAGE_MAP.put(Integer.valueOf(14), R.mipmap.winnipeg_little_rock);
        TICKET_IMAGE_MAP.put(Integer.valueOf(15), R.mipmap.san_francisco_atlanta);
        TICKET_IMAGE_MAP.put(Integer.valueOf(16), R.mipmap.kansas_city_houston);
        TICKET_IMAGE_MAP.put(Integer.valueOf(17), R.mipmap.los_angeles_chicago);
        TICKET_IMAGE_MAP.put(Integer.valueOf(18), R.mipmap.denver_pittsburgh);
        TICKET_IMAGE_MAP.put(Integer.valueOf(19), R.mipmap.chicago_santa_fe);
        TICKET_IMAGE_MAP.put(Integer.valueOf(20), R.mipmap.vancouver_santa_fe);
        TICKET_IMAGE_MAP.put(Integer.valueOf(21), R.mipmap.boston_miami);
        TICKET_IMAGE_MAP.put(Integer.valueOf(22), R.mipmap.chicago_new_orleans);
        TICKET_IMAGE_MAP.put(Integer.valueOf(23), R.mipmap.montreal_atlanta);
        TICKET_IMAGE_MAP.put(Integer.valueOf(24), R.mipmap.seattle_new_york);
        TICKET_IMAGE_MAP.put(Integer.valueOf(25), R.mipmap.denver_el_paso);
        TICKET_IMAGE_MAP.put(Integer.valueOf(26), R.mipmap.helena_los_angeles);
        TICKET_IMAGE_MAP.put(Integer.valueOf(27), R.mipmap.winnipeg_houston);
        TICKET_IMAGE_MAP.put(Integer.valueOf(28), R.mipmap.montreal_new_orleans);
        TICKET_IMAGE_MAP.put(Integer.valueOf(29), R.mipmap.sault_st_marie_oaklahoma_city);
        TICKET_IMAGE_MAP.put(Integer.valueOf(30), R.mipmap.seattle_los_angeles);
    }
}
