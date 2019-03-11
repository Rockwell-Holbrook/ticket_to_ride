package com.example.rholbrook.tickettoride.game;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.*;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.*;

import java.util.List;

public class GameActivity extends AppCompatActivity implements
        GameActivityContract.View,
        SelectTicketsDialogFragment.SelectTicketsDialogInterface,
        ViewTrainCardsDialogFragment.ViewTrainCardsDialogInterface,
        ViewTicketsDialogFragment.ViewTicketsDialogInterface
        {
    private GameActivityContract.Presenter mPresenter;

    private Button demoButton;

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
    private Button openDrawerButton;
    private DrawerLayout drawerLayout;
    private Button closeDrawerButton;
    private FrameLayout drawerFragmentContainer;
    private TabLayout drawerTab;
    private ChatFragment chatFragment;
    private HistoryFragment historyFragment;
    private TextView trainCardDeckCount;
    private TextView ticketDeckCountTextView;
    private int cardsDrawn = 0;

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
        drawerTab = drawerLayout.findViewById(R.id.tab_layout);
        drawerFragmentContainer = drawerLayout.findViewById(R.id.drawer_fragment_container);
        trainCardDeckCount = findViewById(R.id.face_down_card_deck_count);
        ticketDeckCountTextView = findViewById(R.id.ticket_deck_count);


        demoButton = findViewById(R.id.demo_button);
        demoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.runDemo2();
            }
        });

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
                cardsDrawn += 1;
                if (cardsDrawn == 2) {
                    endUserTurn();
                }
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

        chatFragment = new ChatFragment();
        final FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.drawer_fragment_container, chatFragment).commit();
        drawerTab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = fm.findFragmentById(R.id.drawer_fragment_container);
                if (fragment != null) {
                    fm.beginTransaction().remove(fragment).commit();
                }
                if (tab.getText().equals(getString(R.string.chat_tab))) {
                    chatFragment = new ChatFragment();
                    fm.beginTransaction().add(R.id.drawer_fragment_container, chatFragment).commit();
                } else if (tab.getText().equals(getString(R.string.game_history_tab))) {
                    historyFragment = new HistoryFragment();
                    fm.beginTransaction().add(R.id.drawer_fragment_container, historyFragment).commit();
                } else {
                    Toast.makeText(GameActivity.this, tab.getText(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                // Do nothing
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                // Do nothing
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                // Do nothing
            }

            @Override
            public void onDrawerStateChanged(int i) {
                // Do nothing
            }
        });

        playerHandLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewTrainCardsDialogFragment dialog = ViewTrainCardsDialogFragment.newInstance(mPresenter.getPlayerHand(), getApplicationContext());
                dialog.show(getSupportFragmentManager(), "View Train Cards Dialog Fragment");
            }
        });

        playerTicketDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewTicketsDialogFragment dialog = ViewTicketsDialogFragment.newInstance(mPresenter.getPlayerTickets());
                dialog.show(getSupportFragmentManager(), "View Tickets Dialog Fragment");
            }
        });

        gameMapFrameLayout = findViewById(R.id.fragment_map_container);
        Fragment gameMapFragment = GameMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_map_container, gameMapFragment).commit();
        addFaceUpCardClickListeners();
        String gameId = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            gameId = extras.getString("gameId");
        }
        mPresenter.setGameId(gameId);
        mPresenter.readyToInitialize();
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
        if (handCards != null) {
            for (TrainCard trainCard : handCards) {
                ImageView iv = new ImageView(this);
                iv.setImageDrawable(getColorCardDrawable(trainCard));
                iv.setTranslationX(xOffset);
                xOffset += 2 * width / handCards.size();
                playerHandLayout.addView(iv);
            }
        }
    }

    private Drawable getColorCardDrawable(TrainCard trainCard) {
        switch (trainCard.getColor()) {
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
            case WILD:
                return getDrawable(R.mipmap.wild_card);
            default:
                return getDrawable(R.mipmap.wild_card);
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
    public void startUserTurn(Player player) {
        cardsDrawn = 0;
        faceDownTicketDeck.setActivated(true);
        faceDownTrainCardDeck.setActivated(true);
        enableFaceUpCards();
        playerConstraintLayout.setBackground(mPresenter.getColorTurnBackground(getApplicationContext(), player.getPlayerColor()));
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
                TrainCard selectedCard = mPresenter.getFaceUpCard(0);
                mPresenter.selectFaceUpCard(0);
                if(selectedCard.getColor() == TrainCard.Color.WILD) {
                    endUserTurn();
                }
                cardsDrawn += 1;
                if (cardsDrawn == 2) {
                    endUserTurn();
                }
            }
        });
        faceUpCardTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrainCard selectedCard = mPresenter.getFaceUpCard(1);
                mPresenter.selectFaceUpCard(1);
                if(selectedCard.getColor() == TrainCard.Color.WILD) {
                    endUserTurn();
                }
                cardsDrawn += 1;
                if (cardsDrawn == 2) {
                    endUserTurn();
                }
            }
        });
        faceUpCardThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrainCard selectedCard = mPresenter.getFaceUpCard(2);
                mPresenter.selectFaceUpCard(2);
                if(selectedCard.getColor() == TrainCard.Color.WILD) {
                    endUserTurn();
                }
                cardsDrawn += 1;
                if (cardsDrawn == 2) {
                    endUserTurn();
                }
            }
        });
        faceUpCardFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrainCard selectedCard = mPresenter.getFaceUpCard(3);
                mPresenter.selectFaceUpCard(3);
                if(selectedCard.getColor() == TrainCard.Color.WILD) {
                    endUserTurn();
                }
                cardsDrawn += 1;
                if (cardsDrawn == 2) {
                    endUserTurn();
                }
            }
        });
        faceUpCardFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrainCard selectedCard = mPresenter.getFaceUpCard(4);
                mPresenter.selectFaceUpCard(4);
                if(selectedCard.getColor() == TrainCard.Color.WILD) {
                    endUserTurn();
                }
                cardsDrawn += 1;
                if (cardsDrawn == 2) {
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
    }

    @Override
    public void setPlayerTicketDeck(final List<Ticket> destinations) {
        final List<Ticket> tickets = destinations;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int id = destinations.get(destinations.size() - 1).getTicketId();
                playerTicketDeck.setImageDrawable(getResources().getDrawable(GameActivityPresenter.TICKET_IMAGE_MAP.get(id)));
            }
        });
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
                if (player.getTickets() != null) {
                    playerTicketCountTextView.setText(String.valueOf(player.getTickets().size()));
                }
                if (player.getTrainCards() != null) {
                    playerTrainCardTextView.setText(String.valueOf(player.getTrainCards().size()));
                }
                playerTrainCountTextView.setText(String.valueOf(player.getRemainingTrainCars()));
                setHandCards(player.getTrainCards());
                playerConstraintLayout.setBackground(mPresenter.getColorBackground(getApplicationContext(), player.getPlayerColor()));
            }
        });
    }

    @Override
    public void updatePlayerOne(Player updatedPlayer) {
        final Player player = updatedPlayer;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                opponentOnePointTextView.setText(String.valueOf(player.getPointsEarned()));
                if (player.getTickets() != null) {
                    opponentOneTicketCountTextView.setText(String.valueOf(player.getTickets().size()));
                }
                if (player.getTrainCards() != null) {
                    opponentOneTrainCardTextView.setText(String.valueOf(player.getTrainCards().size()));
                }
                opponentOneTrainCountTextView.setText(player.getRemainingTrainCars());
                opponentOneConstraintLayout.setBackground(mPresenter.getColorBackground(getApplicationContext(), player.getPlayerColor()));
            }
        });
    }

    @Override
    public void updatePlayerTwo(Player updatedPlayer) {
        final Player player = updatedPlayer;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                opponentTwoPointTextView.setText(String.valueOf(player.getPointsEarned()));
                if (player.getTickets() != null) {
                    opponentTwoTicketCountTextView.setText(String.valueOf(player.getTickets().size()));
                }
                if (player.getTrainCards() != null) {
                    opponentTwoTrainCardTextView.setText(String.valueOf(player.getTrainCards().size()));
                }
                opponentTwoTrainCountTextView.setText(player.getRemainingTrainCars());
                opponentTwoConstraintLayout.setBackground(mPresenter.getColorBackground(getApplicationContext(), player.getPlayerColor()));

            }
        });
    }

    @Override
    public void updatePlayerThree(Player updatedPlayer) {
        final Player player = updatedPlayer;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                opponentThreePointTextView.setText(String.valueOf(player.getPointsEarned()));
                if (player.getTickets() != null) {
                    opponentThreeTicketCountTextView.setText(String.valueOf(player.getTickets().size()));
                }
                if (player.getTrainCards() != null) {
                    opponentThreeTrainCardTextView.setText(String.valueOf(player.getTrainCards().size()));
                }
                opponentThreeTrainCountTextView.setText(player.getRemainingTrainCars());
                opponentThreeConstraintLayout.setBackground(mPresenter.getColorBackground(getApplicationContext(), player.getPlayerColor()));
            }
        });
    }

    @Override
    public void updatePlayerFour(Player updatedPlayer) {
        final Player player = updatedPlayer;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                opponentFourPointTextView.setText(String.valueOf(player.getPointsEarned()));
                if (player.getTickets() != null) {
                    opponentFourTicketCountTextView.setText(String.valueOf(player.getTickets().size()));
                }
                if (player.getTrainCards() != null) {
                    opponentFourTrainCardTextView.setText(String.valueOf(player.getTrainCards().size()));
                }
                opponentFourTrainCountTextView.setText(player.getRemainingTrainCars());
                opponentFourConstraintLayout.setBackground(mPresenter.getColorBackground(getApplicationContext(), player.getPlayerColor()));
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
    public void updateDeckCounts(final int ticketDeckCount, final int trainDeckCount) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                trainCardDeckCount.setText(String.valueOf(trainDeckCount));
                ticketDeckCountTextView.setText(String.valueOf(ticketDeckCount));
            }
        });
    }

    @Override
    public void setOpponentOneTurn(final Player opponentOne) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                opponentOneConstraintLayout.setBackground(mPresenter.getColorTurnBackground(getApplicationContext(), opponentOne.getPlayerColor()));
            }
        });
    }

    @Override
    public void setOpponentTwoTurn(final Player opponentTwo) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                opponentTwoConstraintLayout.setBackground(mPresenter.getColorTurnBackground(getApplicationContext(), opponentTwo.getPlayerColor()));
            }
        });
    }

    @Override
    public void setOpponentThreeTurn(final Player opponentThree) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                opponentThreeConstraintLayout.setBackground(mPresenter.getColorTurnBackground(getApplicationContext(), opponentThree.getPlayerColor()));
            }
        });
    }

    @Override
    public void setOpponentFourTurn(final Player opponentFour) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                opponentFourConstraintLayout.setBackground(mPresenter.getColorTurnBackground(getApplicationContext(), opponentFour.getPlayerColor()));
            }
        });
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setOpponentThreeNotTurn(Player client) {
        opponentThreeConstraintLayout.setBackground(mPresenter.getColorBackground(getApplicationContext(), client.getPlayerColor()));
    }

    @Override
    public void setOpponentTwoNotTurn(Player client) {
        opponentTwoConstraintLayout.setBackground(mPresenter.getColorBackground(getApplicationContext(), client.getPlayerColor()));
    }

    @Override
    public void setOpponentOneNotTurn(Player client) {
        opponentOneConstraintLayout.setBackground(mPresenter.getColorBackground(getApplicationContext(), client.getPlayerColor()));
    }

    @Override
    public void setClientNotTurn(Player client) {
        playerConstraintLayout.setBackground(mPresenter.getColorBackground(getApplicationContext(), client.getPlayerColor()));
    }

    @Override
    public void onReturnPressed(DialogFragment dialogFragment, List<Ticket> keptCards, List<Ticket> returnedCards, int indicator) {
        dialogFragment.dismiss();
        mPresenter.addTicketsToPlayer(keptCards);
        mPresenter.returnTickets(returnedCards);
        if (indicator == GameActivityModel.INITIALIZE_TICKETS_SELECTION_TYPE) {
            mPresenter.initializeComplete();
//            mPresenter.runDemo1();
        }
    }

    @Override
    public void onClosePressed(DialogFragment dialogFragment) {
        dialogFragment.dismiss();
    }
}
