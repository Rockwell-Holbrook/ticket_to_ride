package com.example.rholbrook.tickettoride.game;

import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.shared.model.Chat;
import com.example.shared.model.Game;
import com.example.shared.model.Player;

import java.util.Observable;
import java.util.Set;

public class GameActivityModel extends Observable {
    private static GameActivityModel instance;
    private DrawerContract.ChatPresenter chatListener;
    private DrawerContract.GameHistoryPresenter historyListener;
    private Player opponentOne;
    private Player opponentTwo;
    private Player opponentThree;
    private Player opponentFour;
    private Game game;

    public GameActivityModel() {
    }

    public static GameActivityModel getInstance() {
        if (instance == null) {
            instance = new GameActivityModel();
        }
        return instance;
    }

    public void receivedChat(Chat chat) {
        chatListener.receivedChat(chat);
    }

    public void selectFaceUpCard(int index) {

    }

    public void selectFaceDownCardDeck() {

    }

    public void drawTickets() {

    }

    public void sortPlayers() {
        Set<Player> players = game.getPlayerList();
    }

    public void setChatListener(DrawerContract.ChatPresenter chatListener) {
        this.chatListener = chatListener;
    }

    public void setHistoryListener(DrawerContract.GameHistoryPresenter historyListener) {
        this.historyListener = historyListener;
    }
}
