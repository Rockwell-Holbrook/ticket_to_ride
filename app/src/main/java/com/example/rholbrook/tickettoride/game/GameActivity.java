package com.example.rholbrook.tickettoride.game;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.ColorCard;
import com.example.shared.model.LocomotiveCard;
import com.example.shared.model.TrainCard;

import java.util.List;

public class GameActivity extends AppCompatActivity implements GameActivityContract.View {
    private GameActivityContract.Presenter mPresenter;

    static final float NAME = 25.0f;

    private RelativeLayout playerHandLayout;
    private FrameLayout gameMapFrameLayout;
    private ConstraintLayout opponentOneConstraintLayout;
    private ImageView opponentOneAvatarImageView;
    private TextView opponentOnePointTextView;
    private TextView opponentOneUsernameTextView;
    private TextView opponentOneTrainCountTextView;
    private TextView opponentOneTicketCountTextView;
    private TextView opponentOneTrainCardTextView;
    private ConstraintLayout opponentTwoConstraintLayout;
    private ImageView opponentTwoAvatarImageView;
    private TextView opponentTwoPointTextView;
    private TextView opponentTwoUsernameTextView;
    private TextView opponentTwoTrainCountTextView;
    private TextView opponentTwoTicketCountTextView;
    private TextView opponentTwoTrainCardTextView;
    private ConstraintLayout opponentThreeConstraintLayout;
    private ImageView opponentThreeAvatarImageView;
    private TextView opponentThreePointTextView;
    private TextView opponentThreeUsernameTextView;
    private TextView opponentThreeTrainCountTextView;
    private TextView opponentThreeTicketCountTextView;
    private TextView opponentThreeTrainCardTextView;
    private ConstraintLayout opponentFourConstraintLayout;
    private ImageView opponentFourAvatarImageView;
    private TextView opponentFourPointTextView;
    private TextView opponentFourUsernameTextView;
    private TextView opponentFourTrainCountTextView;
    private TextView opponentFourTicketCountTextView;
    private TextView opponentFourTrainCardTextView;
    private ConstraintLayout playerConstraintLayout;
    private ImageView playerAvatarImageView;
    private TextView playerPointTextView;
    private TextView playerTrainCountTextView;
    private TextView playerTicketCountTextView;
    private TextView playerTrainCardTextView;
    private ImageView playerTicketDeck;
    private ImageView faceDownTicketDeck;
    private ImageView faceDownTrainCardDeck;
    private ImageView faceUpCardOne;
    private ImageView faceUpCardTwo;
    private ImageView faceUpCardThree;
    private ImageView faceUpCardFour;
    private ImageView faceUpCardFive;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerHandLayout = findViewById(R.id.player_train_card_hand);
        faceUpCardOne = findViewById(R.id.card_one);
        faceUpCardTwo = findViewById(R.id.card_two);
        faceUpCardThree = findViewById(R.id.card_three);
        faceUpCardFour = findViewById(R.id.card_four);
        faceUpCardFive = findViewById(R.id.card_five);

        mPresenter = new GameActivityPresenter(this);

        gameMapFrameLayout = findViewById(R.id.fragment_map_container);
        Fragment gameMapFragment = GameMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_map_container, gameMapFragment).commit();
        mPresenter.init();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setHandCards(List<TrainCard> handCards) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels / 7;
        float xOffset = 0f;
        playerHandLayout.removeAllViews();
        for (TrainCard trainCard : handCards) {
            ImageView iv = new ImageView(this);
            iv.setImageDrawable(getColorCardDrawable(trainCard));
            iv.setTranslationX(xOffset);
            xOffset += 2 * width / handCards.size();
            playerHandLayout.addView(iv);
        }
    }

    private Drawable getColorCardDrawable(TrainCard trainCard) {
        if (trainCard.getClass().equals(LocomotiveCard.class)) {
            return getDrawable(R.mipmap.wild_card);
        } else {
            ColorCard card = (ColorCard) trainCard;
            switch (card.getColor()) {
                case BLACK:
                    return getDrawable(R.mipmap.black_card);
                case BLUE:
                    return getDrawable(R.mipmap.blue_card);
                case RED:
                    return getDrawable(R.mipmap.red_card);
                case YELLOW:
                    return getDrawable(R.mipmap.yellow_card);
                case GREEN:
                    return getDrawable(R.mipmap.green_card);
                case PINK:
                    return getDrawable(R.mipmap.purple_card);
                case WHITE:
                    return getDrawable(R.mipmap.white_card);
                case ORANGE:
                    return getDrawable(R.mipmap.orange_card);
                default:
                    return getDrawable(R.mipmap.card_back);
            }
        }
    }

    @Override
    public void setFaceUpDeck(TrainCard[] faceUpDeck) {
        faceUpCardOne.setImageDrawable(getColorCardDrawable(faceUpDeck[0]));
        faceUpCardTwo.setImageDrawable(getColorCardDrawable(faceUpDeck[1]));
        faceUpCardThree.setImageDrawable(getColorCardDrawable(faceUpDeck[2]));
        faceUpCardFour.setImageDrawable(getColorCardDrawable(faceUpDeck[3]));
        faceUpCardFive.setImageDrawable(getColorCardDrawable(faceUpDeck[4]));
    }
}
