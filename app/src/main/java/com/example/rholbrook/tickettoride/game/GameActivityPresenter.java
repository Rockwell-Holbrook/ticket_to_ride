package com.example.rholbrook.tickettoride.game;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.Ticket;
import com.example.shared.model.Player;
import com.example.shared.model.TrainCard;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

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
    public TrainCard getFaceUpCard(int i) {
        return mModel.getFaceUpCards().get(i);
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
    }

    @Override
    public void returnTickets(List<Ticket> returnedCards) {
        mModel.returnTickets(returnedCards);
    }
}
