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
import com.example.shared.model.TrainCard;

import java.util.List;

public class TrainCardsAdapter extends RecyclerView.Adapter<TrainCardsAdapter.ViewHolder>{
    List<TrainCard> trainCards;
    Context context;

    public TrainCardsAdapter(List<TrainCard> trainCards, Context context) {
        this.trainCards = trainCards;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card_view, viewGroup, false);
        return new TrainCardsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.cardImage.setImageDrawable(getColorCardDrawable(trainCards.get(viewHolder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return trainCards.size();
    }

    private Drawable getColorCardDrawable(TrainCard trainCard) {
        switch (trainCard.getColor()) {
            case BLACK:
                return context.getDrawable(R.mipmap.black_card);
            case BLUE:
                return context.getDrawable(R.mipmap.blue_card);
            case RED:
                return context.getDrawable(R.mipmap.red_card);
            case YELLOW:
                return context.getDrawable(R.mipmap.yellow_card);
            case GREEN:
                return context.getDrawable(R.mipmap.green_card);
            case PINK:
                return context.getDrawable(R.mipmap.purple_card);
            case WHITE:
                return context.getDrawable(R.mipmap.white_card);
            case ORANGE:
                return context.getDrawable(R.mipmap.orange_card);
            case WILD:
                return context.getDrawable(R.mipmap.wild_card);
            default:
                return context.getDrawable(R.mipmap.wild_card);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cardImage;

        public ViewHolder(View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.player_card_image_view);
        }
    }
}
