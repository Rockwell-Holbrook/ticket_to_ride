package com.example.rholbrook.tickettoride.game;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.*;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.*;

import java.util.List;

public class GameActivity extends AppCompatActivity implements
        GameActivityContract.View,
        SelectTicketsDialogFragment.SelectTicketsDialogInterface{
    private GameActivityContract.Presenter mPresenter;



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
    private RecyclerView viewHandRecyclerView;
    private RecyclerView viewTicketsRecyclerView;
    private Button openDrawerButton;
    private DrawerLayout drawerLayout;
    private Button closeDrawerButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setContentView(R.layout.drawer_layout);

        playerHandLayout = findViewById(R.id.player_train_card_hand);
        playerTicketDeck = findViewById(R.id.personal_ticket_deck);
        faceUpCardOne = findViewById(R.id.card_one);
        faceUpCardTwo = findViewById(R.id.card_two);
        faceUpCardThree = findViewById(R.id.card_three);
        faceUpCardFour = findViewById(R.id.card_four);
        faceUpCardFive = findViewById(R.id.card_five);
        viewHandRecyclerView = findViewById(R.id.view_hand_recycler_view);
        viewTicketsRecyclerView = findViewById(R.id.view_tickets_recycler_view);
        faceDownTicketDeck = findViewById(R.id.ticket_deck);
        faceDownTrainCardDeck = findViewById(R.id.facedown_card_deck);
        opponentOneConstraintLayout = findViewById(R.id.opponent_one_constraint_layout);
        opponentOneAvatarImageView = findViewById(R.id.opponent_one_image_view);
        opponentOneUsernameTextView = findViewById(R.id.opponent_one_username_text_view);
        opponentOnePointTextView = findViewById(R.id.opponent_one_point_text_view);
        opponentOneTrainCardTextView = findViewById(R.id.opponent_one_card_text_view);
        opponentOneTicketCountTextView = findViewById(R.id.opponent_one_ticket_count_text_view);
        opponentOneTrainCountTextView = findViewById(R.id.opponent_one_train_text_view);
        opponentTwoConstraintLayout = findViewById(R.id.opponent_two_constraint_layout);
        opponentTwoAvatarImageView = findViewById(R.id.opponent_two_image_view);
        opponentTwoUsernameTextView = findViewById(R.id.opponent_two_username_text_view);
        opponentTwoPointTextView = findViewById(R.id.opponent_two_point_text_view);
        opponentTwoTrainCardTextView = findViewById(R.id.opponent_two_card_text_view);
        opponentTwoTicketCountTextView = findViewById(R.id.opponent_two_ticket_count_text_view);
        opponentTwoTrainCountTextView = findViewById(R.id.opponent_two_train_text_view);
        opponentThreeConstraintLayout = findViewById(R.id.opponent_three_constraint_layout);
        opponentThreeAvatarImageView = findViewById(R.id.opponent_three_image_view);
        opponentThreeUsernameTextView = findViewById(R.id.opponent_three_username_text_view);
        opponentThreePointTextView = findViewById(R.id.opponent_three_point_text_view);
        opponentThreeTrainCardTextView = findViewById(R.id.opponent_three_card_text_view);
        opponentThreeTicketCountTextView = findViewById(R.id.opponent_three_ticket_count_text_view);
        opponentThreeTrainCountTextView = findViewById(R.id.opponent_three_train_text_view);
        opponentFourConstraintLayout = findViewById(R.id.opponent_four_constraint_layout);
        opponentFourAvatarImageView = findViewById(R.id.opponent_four_image_view);
        opponentFourUsernameTextView = findViewById(R.id.opponent_four_username_text_view);
        opponentFourPointTextView = findViewById(R.id.opponent_four_point_text_view);
        opponentFourTrainCardTextView = findViewById(R.id.opponent_four_card_text_view);
        opponentFourTicketCountTextView = findViewById(R.id.opponent_four_ticket_count_text_view);
        opponentFourTrainCountTextView = findViewById(R.id.opponent_four_train_text_view);
        playerConstraintLayout = findViewById(R.id.player_constraint_layout);
        playerAvatarImageView = findViewById(R.id.player_image_view);
        playerPointTextView = findViewById(R.id.player_point_text_view);
        playerTicketCountTextView = findViewById(R.id.player_ticket_card_text_view);
        playerTrainCardTextView = findViewById(R.id.player_card_text_view);
        playerTrainCountTextView = findViewById(R.id.player_train_text_view);
        openDrawerButton = findViewById(R.id.open_drawer_button);
        drawerLayout = findViewById(R.id.drawer_layout);
        closeDrawerButton = drawerLayout.findViewById(R.id.close_drawer_button);

        mPresenter = new GameActivityPresenter(this);

        faceDownTicketDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.clickDrawTickets();
            }
        });

        faceDownTrainCardDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.selectFaceDownCardDeck();
            }
        });

        openDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        closeDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        gameMapFrameLayout = findViewById(R.id.fragment_map_container);
        Fragment gameMapFragment = GameMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_map_container, gameMapFragment).commit();
        addFaceUpCardClickListeners();
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
    public void setFaceUpDeck(List<TrainCard> cards) {
        final List<TrainCard> faceUpDeck = cards;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                faceUpCardOne.setImageDrawable(getColorCardDrawable(faceUpDeck.get(0)));
                faceUpCardTwo.setImageDrawable(getColorCardDrawable(faceUpDeck.get(1)));
                faceUpCardThree.setImageDrawable(getColorCardDrawable(faceUpDeck.get(2)));
                faceUpCardFour.setImageDrawable(getColorCardDrawable(faceUpDeck.get(3)));
                faceUpCardFive.setImageDrawable(getColorCardDrawable(faceUpDeck.get(4)));
            }
        });
    }

    @Override
    public void startUserTurn() {
        faceDownTicketDeck.setActivated(true);
        faceDownTrainCardDeck.setActivated(true);
        enableFaceUpCards();
    }

    @Override
    public void endUserTurn() {
        faceDownTicketDeck.setActivated(false);
        faceDownTrainCardDeck.setActivated(false);
        disableFaceUpCards();
        mPresenter.endTurn();
    }

    @Override
    public void addFaceUpCardClickListeners() {
        faceUpCardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.selectFaceUpCard(0);
                TrainCard selectedCard = mPresenter.getFaceUpCard(0);
                if(selectedCard.getClass().getName().equals(LocomotiveCard.class.getName())) {
                    endUserTurn();
                }
            }
        });
        faceUpCardTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.selectFaceUpCard(1);
                TrainCard selectedCard = mPresenter.getFaceUpCard(1);
                if(selectedCard.getClass().getName().equals(LocomotiveCard.class.getName())) {
                    endUserTurn();
                }
            }
        });
        faceUpCardThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.selectFaceUpCard(2);
                TrainCard selectedCard = mPresenter.getFaceUpCard(2);
                if(selectedCard.getClass().getName().equals(LocomotiveCard.class.getName())) {
                    endUserTurn();
                }
            }
        });
        faceUpCardFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.selectFaceUpCard(3);
                TrainCard selectedCard = mPresenter.getFaceUpCard(3);
                if(selectedCard.getClass().getName().equals(LocomotiveCard.class.getName())) {
                    endUserTurn();
                }
            }
        });
        faceUpCardFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.selectFaceUpCard(4);
                TrainCard selectedCard = mPresenter.getFaceUpCard(4);
                if(selectedCard.getClass().getName().equals(LocomotiveCard.class.getName())) {
                    endUserTurn();
                }
            }
        });
    }

    @Override
    public void enableFaceUpCards() {
        faceUpCardOne.setActivated(true);
        faceUpCardTwo.setActivated(true);
        faceUpCardThree.setActivated(true);
        faceUpCardFour.setActivated(true);
        faceUpCardFive.setActivated(true);
    }

    @Override
    public void disableFaceUpCards() {
        faceUpCardOne.setActivated(false);
        faceUpCardTwo.setActivated(false);
        faceUpCardThree.setActivated(false);
        faceUpCardFour.setActivated(false);
        faceUpCardFive.setActivated(false);
    }

    @Override
    public void initializeGame(List<Ticket> selectableTickets) {
        selectTickets(selectableTickets, GameActivityModel.INITIALIZE_TICKETS_SELECTION_TYPE);
        mPresenter.initializeComplete();
    }

    @Override
    public void setPlayerTicketDeck(List<Ticket> testDestinations) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                playerTicketDeck.setImageDrawable(getResources().getDrawable(R.mipmap.ticket_back));
            }});
    }

    @Override
    public void initializePlayers(List<Player> players) {
        final List<Player> turnOrder = players;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < turnOrder.size(); i++) {
                    Drawable avatarImage = mPresenter.getAvatar(getApplicationContext(), turnOrder.get(i).getPlayerColor());
                    Drawable colorBackground = mPresenter.getColorBackground(getApplicationContext(), turnOrder.get(i).getPlayerColor());
                    switch (i) {
                        case 0:
                            playerConstraintLayout.setBackground(colorBackground);
                            playerAvatarImageView.setImageDrawable(avatarImage);
                            break;
                        case 1:
                            opponentOneConstraintLayout.setBackground(colorBackground);
                            opponentOneAvatarImageView.setImageDrawable(avatarImage);
                            opponentOneUsernameTextView.setText(turnOrder.get(i).getUsername());
                            break;
                        case 2:
                            opponentTwoConstraintLayout.setBackground(colorBackground);
                            opponentTwoAvatarImageView.setImageDrawable(avatarImage);
                            opponentTwoUsernameTextView.setText(turnOrder.get(i).getUsername());
                            break;
                        case 3:
                            opponentThreeConstraintLayout.setBackground(colorBackground);
                            opponentThreeAvatarImageView.setImageDrawable(avatarImage);
                            opponentThreeUsernameTextView.setText(turnOrder.get(i).getUsername());
                            break;
                        case 4:
                            opponentFourConstraintLayout.setBackground(colorBackground);
                            opponentFourAvatarImageView.setImageDrawable(avatarImage);
                            opponentFourUsernameTextView.setText(turnOrder.get(i).getUsername());
                            break;
                    }
                }
                switch (turnOrder.size()) {
                    case 1:
                        opponentOneConstraintLayout.setVisibility(View.INVISIBLE);
                        opponentTwoConstraintLayout.setVisibility(View.INVISIBLE);
                        opponentThreeConstraintLayout.setVisibility(View.INVISIBLE);
                        opponentFourConstraintLayout.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        opponentTwoConstraintLayout.setVisibility(View.INVISIBLE);
                        opponentThreeConstraintLayout.setVisibility(View.INVISIBLE);
                        opponentFourConstraintLayout.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        opponentThreeConstraintLayout.setVisibility(View.INVISIBLE);
                        opponentFourConstraintLayout.setVisibility(View.INVISIBLE);
                        break;
                    case 4:
                        opponentFourConstraintLayout.setVisibility(View.INVISIBLE);
                        break;
                    case 5:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void updateClient(Player updatedPlayer) {
        final Player player = updatedPlayer;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                playerPointTextView.setText(String.valueOf(player.getPointsEarned()));
                playerTicketCountTextView.setText(String.valueOf(player.getTickets().size()));
                playerTrainCardTextView.setText(String.valueOf(player.getTrainCards().size()));
                playerTrainCountTextView.setText(String.valueOf(player.getRemainingTrainCars()));
                setHandCards(player.getTrainCards());
            }
        });
    }

    @Override
    public void updatePlayerOne(Player updatedPlayer) {
        final Player player = updatedPlayer;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                opponentOnePointTextView.setText(player.getPointsEarned());
                opponentOneTicketCountTextView.setText(player.getTickets().size());
                opponentOneTrainCardTextView.setText(player.getTrainCards().size());
                opponentOneTrainCountTextView.setText(player.getRemainingTrainCars());
            }
        });
    }

    @Override
    public void updatePlayerTwo(Player updatedPlayer) {
        final Player player = updatedPlayer;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                opponentTwoPointTextView.setText(player.getPointsEarned());
                opponentTwoTicketCountTextView.setText(player.getTickets().size());
                opponentTwoTrainCardTextView.setText(player.getTrainCards().size());
                opponentTwoTrainCountTextView.setText(player.getRemainingTrainCars());
            }
        });
    }

    @Override
    public void updatePlayerThree(Player updatedPlayer) {
        final Player player = updatedPlayer;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                opponentThreePointTextView.setText(player.getPointsEarned());
                opponentThreeTicketCountTextView.setText(player.getTickets().size());
                opponentThreeTrainCardTextView.setText(player.getTrainCards().size());
                opponentThreeTrainCountTextView.setText(player.getRemainingTrainCars());
            }
        });
    }

    @Override
    public void updatePlayerFour(Player updatedPlayer) {
        final Player player = updatedPlayer;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                opponentFourPointTextView.setText(player.getPointsEarned());
                opponentFourTicketCountTextView.setText(player.getTickets().size());
                opponentFourTrainCardTextView.setText(player.getTrainCards().size());
                opponentFourTrainCountTextView.setText(player.getRemainingTrainCars());
            }
        });
    }

    @Override
    public void selectTickets(final List<Ticket> selectableTickets, final int selectionType) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SelectTicketsDialogFragment dialog = SelectTicketsDialogFragment.newInstance(selectableTickets, selectionType);
                dialog.show(getSupportFragmentManager(), "SelectTicketsDialogFragment");
            }
        });
    }

    @Override
    public void onReturnPressed(DialogFragment dialogFragment, List<Ticket> keptCards, List<Ticket> returnedCards) {
        dialogFragment.dismiss();
        mPresenter.addTicketsToPlayer(keptCards);
        mPresenter.returnTickets(returnedCards);
    }
}
