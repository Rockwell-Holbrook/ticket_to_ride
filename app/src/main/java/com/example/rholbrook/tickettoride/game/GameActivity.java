package com.example.rholbrook.tickettoride.game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rholbrook.tickettoride.R;

public class GameActivity extends AppCompatActivity implements GameActivityContract.View {
    private GameActivityContract.Presenter mPresenter;

    private FrameLayout playerHandLayout;
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
    private FrameLayout faceUpTrainCards;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mPresenter = new GameActivityPresenter(this);

        gameMapFrameLayout = findViewById(R.id.fragment_map_container);
        Fragment gameMapFragment = GameMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_map_container, gameMapFragment).commit();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setHandCards() {

    }

    @Override
    public void setFaceUpDeck() {

    }
}
