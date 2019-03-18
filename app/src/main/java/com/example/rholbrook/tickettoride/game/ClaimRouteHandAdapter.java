package com.example.rholbrook.tickettoride.game;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.Route;
import com.example.shared.model.TrainCard;

import java.util.ArrayList;
import java.util.List;

public class ClaimRouteHandAdapter extends RecyclerView.Adapter<ClaimRouteHandAdapter.ViewHolder>{
    List<TrainCard> handCards;
    List<TrainCard> selectedCards;
    Context context;
    Route selectedRoute;
    ClaimRouteHandAdapterInterface mListener;
    TrainCard.Color selectedColor;

    public interface ClaimRouteHandAdapterInterface {
        void selectCard(TrainCard card);
        void deselectCard(TrainCard card);
    }

    public ClaimRouteHandAdapter(List<TrainCard> handCards, Context context, Route selectedRoute, ClaimRouteHandAdapterInterface listener) {
        this.handCards = handCards;
        selectedCards = new ArrayList<>();
        this.context = context;
        this.selectedRoute = selectedRoute;
        mListener = listener;
    }

    @NonNull
    @Override
    public ClaimRouteHandAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_select_route_view, viewGroup, false);
        return new ClaimRouteHandAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ClaimRouteHandAdapter.ViewHolder viewHolder, int i) {
        final TrainCard card = handCards.get(viewHolder.getAdapterPosition());
        viewHolder.cardImage.setImageDrawable(getColorCardDrawable(card));
        viewHolder.selectCardImage.setVisibility(View.INVISIBLE);
        viewHolder.cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.selectCardImage.getVisibility() == View.VISIBLE) {
                    selectedCards.remove(card);
                    mListener.deselectCard(card);
                    viewHolder.selectCardImage.setVisibility(View.INVISIBLE);
                } else {
                    if (isValidChoice(card)) {
                        selectedCards.add(card);
                        mListener.selectCard(card);
                        viewHolder.selectCardImage.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(context, R.string.cannot_use_this_card, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean isValidChoice(TrainCard card) {
        switch (selectedRoute.getColor()) {
            case GRAY:
                if (selectedCards.size() > 0) {
                    for (TrainCard selectedCard : selectedCards) {
                        if (selectedCard.getColor() != TrainCard.Color.WILD) {
                            if (selectedCard.getColor() == card.getColor() || card.getColor() == TrainCard.Color.WILD) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                    return true;
                } else {
                   return true;
                }
            default:
                if (card.getColor() == getRouteEquivalentColor(selectedRoute.getColor()) || card.getColor() == TrainCard.Color.WILD) {
                    return true;
                } else {
                    return false;
                }
        }
    }

    private TrainCard.Color getRouteEquivalentColor(Route.RouteColor color) {
        switch (color) {
            case ORANGE:
                return TrainCard.Color.ORANGE;
            case WHITE:
                return TrainCard.Color.WHITE;
            case PINK:
                return TrainCard.Color.PINK;
            case GREEN:
                return TrainCard.Color.GREEN;
            case BLACK:
                return TrainCard.Color.BLACK;
            case BLUE:
                return TrainCard.Color.BLUE;
            case RED:
                return TrainCard.Color.RED;
            case YELLOW:
                return TrainCard.Color.YELLOW;
            default:
                return TrainCard.Color.WILD;
        }
    }

    @Override
    public int getItemCount() {
        return handCards.size();
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
        ImageView selectCardImage;

        public ViewHolder(View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.player_card_image_view);
            selectCardImage = itemView.findViewById(R.id.select_card_check);
        }
    }
}
