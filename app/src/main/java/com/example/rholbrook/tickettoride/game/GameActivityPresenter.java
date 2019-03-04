package com.example.rholbrook.tickettoride.game;

import com.example.shared.model.Ticket;
import com.example.shared.model.Player;
import com.example.shared.model.TrainCard;

import java.util.ArrayList;
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
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void init() {
        List<TrainCard> testHand = new ArrayList<>();
        LocomotiveCard cardOne = new LocomotiveCard();
        ColorCard cardTwo = new ColorCard(ColorCard.Color.BLACK);
        ColorCard cardThree = new ColorCard(ColorCard.Color.BLUE);
        ColorCard cardFour = new ColorCard(ColorCard.Color.RED);
        ColorCard cardFive = new ColorCard(ColorCard.Color.WHITE);
        testHand.add(cardOne);
        testHand.add(cardTwo);
        testHand.add(cardThree);
        testHand.add(cardFour);
        testHand.add(cardFive);
        viewCallback.setHandCards(testHand);
        TrainCard[] testCards = {cardOne, cardTwo, cardThree, cardFour, cardFive};
        viewCallback.setFaceUpDeck(testCards);
        List<Ticket> testDestinations = new ArrayList<>();
        Ticket destinationCard = new Ticket();
        testDestinations.add(destinationCard);
        viewCallback.setPlayerTicketDeck(testDestinations);
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
    public void initializeGame() {

    }

    @Override
    public Player getOpponentOne() {
        return null;
    }
}
