package com.example.rholbrook.tickettoride.game;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.Ticket;
import com.example.shared.model.Player;
import com.example.shared.model.TrainCard;

import java.util.*;

public class GameActivityPresenter implements
        GameActivityContract.Presenter,
        Observer {

    private GameActivityContract.View viewCallback;
    private GameActivityModel mModel;

    public GameActivityPresenter(GameActivityContract.View viewCallback) {
        this.viewCallback = viewCallback;
        this.mModel = GameActivityModel.getInstance();
        mModel.setGameActivityPresenter(this);
        mModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass().getName().equals(Player.class.getName())) {
            Player updatedPlayer = (Player) arg;
            if (updatedPlayer.getUsername().equals(mModel.getClient().getUsername())) {
                viewCallback.updateClient(updatedPlayer);
            } else if (updatedPlayer.getUsername().equals(mModel.getOpponentOne().getUsername())) {
                viewCallback.updatePlayerOne(updatedPlayer);
            } else if (updatedPlayer.getUsername().equals(mModel.getOpponentTwo().getUsername())) {
                viewCallback.updatePlayerTwo(updatedPlayer);
            } else if (updatedPlayer.getUsername().equals(mModel.getOpponentThree().getUsername())) {
                viewCallback.updatePlayerThree(updatedPlayer);
            } else if (updatedPlayer.getUsername().equals(mModel.getOpponentFour().getUsername())) {
                viewCallback.updatePlayerFour(updatedPlayer);
            }
        } else if (arg.getClass().getName().equals(Boolean.class.getName())) {
            boolean isTurn = (boolean)arg;
            if (isTurn) {
                viewCallback.startUserTurn();
            } else {
                viewCallback.endUserTurn();
            }
        }
    }

    @Override
    public void selectFaceUpCard(int index) {
        mModel.selectFaceUpCard(index);
    }

    @Override
    public void selectFaceDownCardDeck() {
        mModel.selectFaceDownCardDeck();
    }

    @Override
    public void clickDrawTickets() {
        mModel.drawTickets();
    }

    @Override
    public void initializeGame(List<Ticket> tickets) {
        viewCallback.initializeGame(tickets);
    }

    @Override
    public void setupTurnOrder(List<Player> turnOrder) {
        viewCallback.initializePlayers(turnOrder);
    }

    @Override
    public Drawable getAvatar(Context applicationContext, Player.PlayerColor playerColor) {
        switch (playerColor) {
            case GREEN:
                return applicationContext.getDrawable(R.mipmap.green_player);
            case RED:
                return applicationContext.getDrawable(R.mipmap.red_player);
            case BLUE:
                return applicationContext.getDrawable(R.mipmap.blue_player);
            case BLACK:
                return applicationContext.getDrawable(R.mipmap.black_player);
            case YELLOW:
                return applicationContext.getDrawable(R.mipmap.yellow_player);
            default:
                return null;
        }
    }

    @Override
    public Drawable getColorBackground(Context applicationContext, Player.PlayerColor playerColor) {
        switch (playerColor) {
            case GREEN:
                return applicationContext.getDrawable(R.drawable.section_green_player);
            case RED:
                return applicationContext.getDrawable(R.drawable.section_red_player);
            case BLUE:
                return applicationContext.getDrawable(R.drawable.section_blue_player);
            case BLACK:
                return applicationContext.getDrawable(R.drawable.section_black_player);
            case YELLOW:
                return applicationContext.getDrawable(R.drawable.section_yellow_player);
            default:
                return null;
        }
    }

    @Override
    public void setFaceUpCards(List<TrainCard> faceUpCards) {
        viewCallback.setFaceUpDeck(faceUpCards);
    }

    @Override
    public void setHandCards(List<TrainCard> cards) {
        viewCallback.setHandCards(cards);
    }

    @Override
    public TrainCard getFaceUpCard(int i) {
        return mModel.getFaceUpCards().get(i);
    }

    @Override
    public void startUserTurn() {
        viewCallback.startUserTurn();
    }

    @Override
    public void endTurn() {
        mModel.endUserTurn();
    }

    @Override
    public void initializeComplete() {
        mModel.initializeComplete();
    }

    @Override
    public void selectTickets(List<Ticket> tickets) {
        viewCallback.selectTickets(tickets, GameActivityModel.ADDITIONAL_TICKETS_SELECTION_TYPE);
    }

    @Override
    public void addTicketsToPlayer(List<Ticket> keptCards) {
        mModel.clientAddTickets(keptCards);
        viewCallback.setPlayerTicketDeck(mModel.getClient().getTickets());
    }

    @Override
    public void returnTickets(List<Ticket> returnedCards) {
        mModel.returnTickets(returnedCards);
    }

    @Override
    public void setGameId(String gameId) {
        mModel.setGameId(gameId);
    }

    @Override
    public void readyToInitialize() {
        mModel.readyToInitialize();
    }

    @Override
    public List<TrainCard> getPlayerHand() {
        return mModel.getClient().getTrainCards();
    }

    @Override
    public List<Ticket> getPlayerTickets() {
        return mModel.getClient().getTickets();
    }

    public static final Map<Integer, Integer> TICKET_IMAGE_MAP;
    static {
        TICKET_IMAGE_MAP = new HashMap<Integer, Integer>();
        TICKET_IMAGE_MAP.put(Integer.valueOf(1), R.mipmap.boston_miami);
        TICKET_IMAGE_MAP.put(Integer.valueOf(2), R.mipmap.calgary_phoenix);
        TICKET_IMAGE_MAP.put(Integer.valueOf(3), R.mipmap.calgary_salt_lake_city);
        TICKET_IMAGE_MAP.put(Integer.valueOf(4), R.mipmap.chicago_new_orleans);
        TICKET_IMAGE_MAP.put(Integer.valueOf(5), R.mipmap.chicago_santa_fe);
        TICKET_IMAGE_MAP.put(Integer.valueOf(6), R.mipmap.dallas_new_york);
        TICKET_IMAGE_MAP.put(Integer.valueOf(7), R.mipmap.denver_el_paso);
        TICKET_IMAGE_MAP.put(Integer.valueOf(8), R.mipmap.denver_pittsburgh);
        TICKET_IMAGE_MAP.put(Integer.valueOf(9), R.mipmap.duluth_el_paso);
        TICKET_IMAGE_MAP.put(Integer.valueOf(10), R.mipmap.duluth_houston);
        TICKET_IMAGE_MAP.put(Integer.valueOf(11), R.mipmap.helena_los_angeles);
        TICKET_IMAGE_MAP.put(Integer.valueOf(12), R.mipmap.kansas_city_houston);
        TICKET_IMAGE_MAP.put(Integer.valueOf(13), R.mipmap.los_angeles_chicago);
        TICKET_IMAGE_MAP.put(Integer.valueOf(14), R.mipmap.los_angeles_miami);
        TICKET_IMAGE_MAP.put(Integer.valueOf(15), R.mipmap.los_angeles_new_york);
        TICKET_IMAGE_MAP.put(Integer.valueOf(16), R.mipmap.montreal_atlanta);
        TICKET_IMAGE_MAP.put(Integer.valueOf(17), R.mipmap.montreal_new_orleans);
        TICKET_IMAGE_MAP.put(Integer.valueOf(18), R.mipmap.new_york_atlanta);
        TICKET_IMAGE_MAP.put(Integer.valueOf(19), R.mipmap.portland_nashville);
        TICKET_IMAGE_MAP.put(Integer.valueOf(20), R.mipmap.portland_phoenix);
        TICKET_IMAGE_MAP.put(Integer.valueOf(21), R.mipmap.san_francisco_atlanta);
        TICKET_IMAGE_MAP.put(Integer.valueOf(22), R.mipmap.sault_st_marie_nashville);
        TICKET_IMAGE_MAP.put(Integer.valueOf(23), R.mipmap.sault_st_marie_oaklahoma_city);
        TICKET_IMAGE_MAP.put(Integer.valueOf(24), R.mipmap.seattle_los_angeles);
        TICKET_IMAGE_MAP.put(Integer.valueOf(25), R.mipmap.seattle_new_york);
        TICKET_IMAGE_MAP.put(Integer.valueOf(26), R.mipmap.toronto_miami);
        TICKET_IMAGE_MAP.put(Integer.valueOf(27), R.mipmap.vancouver_montreal);
        TICKET_IMAGE_MAP.put(Integer.valueOf(28), R.mipmap.vancouver_santa_fe);
        TICKET_IMAGE_MAP.put(Integer.valueOf(29), R.mipmap.winnipeg_houston);
        TICKET_IMAGE_MAP.put(Integer.valueOf(30), R.mipmap.winnipeg_little_rock);
    }

    public void runDemo1() {
        mModel.runDemo1();
    }

    public void runDemo2() {mModel.runDemo2();}

}
