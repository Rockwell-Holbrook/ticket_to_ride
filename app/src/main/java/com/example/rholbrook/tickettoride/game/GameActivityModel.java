package com.example.rholbrook.tickettoride.game;

import android.util.Log;
import com.example.rholbrook.tickettoride.main.Authentication;
import com.example.rholbrook.tickettoride.serverconnection.ServerProxy;
import com.example.rholbrook.tickettoride.serverconnection.StubServer;
import com.example.shared.interfaces.IServer;
import com.example.shared.model.Chat;
import com.example.shared.model.Game;
import com.example.shared.model.GameHistory;
import com.example.shared.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;

public class GameActivityModel extends Observable {
    private String TAG = "GameActivityModel";

    private static GameActivityModel instance;
    private DrawerContract.ChatPresenter chatListener;
    private DrawerContract.HistoryPresenter historyListener;
    private IServer server;
    private String gameId;
    private Player opponentOne;
    private Player opponentTwo;
    private Player opponentThree;
    private Player opponentFour;
    private Game game;
    private List<Chat> chatMessages;
    private List<GameHistory> gameHistory;

    public GameActivityModel() {
        // TODO Change back to ServerProxy
        server = StubServer.getInstance();
        chatMessages = new ArrayList<>();
    }

    public static GameActivityModel getInstance() {
        if (instance == null) {
            instance = new GameActivityModel();
        }
        return instance;
    }

    public void receivedChat(Chat chat) {
        chatMessages.add(chat);
        chatListener.updateChatList(chatMessages);
    }

    public void receivedChatHistory(List<Chat> chatHistory) {
        chatMessages = chatHistory;
        chatListener.updateChatList(chatMessages);
    }

    public void sendChat(String message) {
        Log.d(TAG, "sendChat");
        Chat newChat = new Chat(Authentication.getInstance().getUsername(), message);
        server.sendChat(newChat, gameId, true);
    }

    public void getChatHistory() {
        server.getChatHistory(gameId, Authentication.getInstance().getUsername(), true);
    }

    public void receivedHistoryObject(GameHistory history) {
        gameHistory.add(history);
        historyListener.updateGameHistory(this.gameHistory);
    }

    public void receivedGameHistory(List<GameHistory> gameHistory) {
        this.gameHistory = gameHistory;
        historyListener.updateGameHistory(this.gameHistory);
    }

    public void getGameHistory() {
        server.getGameHistory(gameId);
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

    public void setHistoryListener(DrawerContract.HistoryPresenter historyListener) {
        this.historyListener = historyListener;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Player.PlayerColor getPlayerColor(String username) {
        if (opponentOne.getUsername().equals(username)) {
            return opponentOne.getPlayerColor();
        }
        if (opponentTwo.getUsername().equals(username)) {
            return opponentTwo.getPlayerColor();
        }
        if (opponentThree.getUsername().equals(username)) {
            return opponentThree.getPlayerColor();
        }
        if (opponentFour.getUsername().equals(username)) {
            return opponentFour.getPlayerColor();
        }
        return
    }
}
