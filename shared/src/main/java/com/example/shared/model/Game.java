package com.example.shared.model;


import com.example.shared.interfaces.IClientInGame;

import java.util.*;

public class Game {
    private String gameId;
    private boolean isPlaying;
    private Player host;
    private Set<Player> playerList = new HashSet<>();
    private int maxPlayers;
    private int readyPlayers = 0;
    private String gameName;
    private List<Player.PlayerColor> availableColors;
    private transient IClientInGame clientProxy;
    private ArrayList<Route> availableRoutes;
    private ArrayList<Route> claimedRoutes;
    private ArrayList<Chat> chatHistory;
    private ArrayList<GameHistory> gameHistory;
    private ArrayList<TrainCard> trainCardsFaceUp;

    public Game(Player host, int maxPlayers, String gameName) {
        this.host = host;
        this.gameName = gameName;
        this.isPlaying = false;
        this.maxPlayers = maxPlayers;
        this.gameId = UUID.randomUUID().toString();
        addPlayer(host);
    }

    /**
     *
     * @param player The player that will be added.
     *
     * This function will add a player to an existing game.
     */
    public void addPlayer(Player player) {
        playerList.add(player);
    }

    /**
     * Start the full game by setting isPlaying to true.
     */
    public void startGame() {
        isPlaying = true;
    }

    public void addChatToList(Chat chat) {
        this.chatHistory.add(chat);
    }

    public void initializeTrainCardsFaceUp() { // TODO: Deck management not randomness.
        ArrayList<TrainCard> temp = new ArrayList<>();
        temp.add(new ColorCard(ColorCard.Color.BLACK));  temp.add(new ColorCard(ColorCard.Color.BLUE)); temp.add(new ColorCard(ColorCard.Color.GREEN)); temp.add(new ColorCard(ColorCard.Color.ORANGE));
        temp.add(new ColorCard(ColorCard.Color.PINK)); temp.add(new ColorCard(ColorCard.Color.RED)); temp.add(new ColorCard(ColorCard.Color.WHITE)); temp.add(new ColorCard(ColorCard.Color.YELLOW));
        temp.add(new LocomotiveCard());

        ArrayList<TrainCard> random = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int randomIndex = (int) (Math.random()*9);
            random.add(temp.get(randomIndex));
        }

        this.trainCardsFaceUp = random;
    }

    public ArrayList<TrainCard> initializeTrainCards() { // TODO: Deck management not randomness.
        ArrayList<TrainCard> temp = new ArrayList<>();
        temp.add(new ColorCard(ColorCard.Color.BLACK));  temp.add(new ColorCard(ColorCard.Color.BLUE)); temp.add(new ColorCard(ColorCard.Color.GREEN)); temp.add(new ColorCard(ColorCard.Color.ORANGE));
        temp.add(new ColorCard(ColorCard.Color.PINK)); temp.add(new ColorCard(ColorCard.Color.RED)); temp.add(new ColorCard(ColorCard.Color.WHITE)); temp.add(new ColorCard(ColorCard.Color.YELLOW));
        temp.add(new LocomotiveCard());

        ArrayList<TrainCard> random = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int randomIndex = (int) (Math.random()*9);
            random.add(temp.get(randomIndex));
        }

        return random;
    }

    public ArrayList<Ticket> initializeTickets() { // TODO: Deck management not randomness and emptiness
        ArrayList<Ticket> temp = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            temp.add(new Ticket());
        }

        return temp;
    }

    public ArrayList<Player> initializeTurnOrder(String username) {
        ArrayList<Player> tempTurnOrder = new ArrayList<>(this.playerList);
        ArrayList<Player> turnOrder = new ArrayList<>();

        int index = 0;

        for (int i = 0; i < tempTurnOrder.size(); i++) { // Figure out the index
            if(tempTurnOrder.get(i).getUsername().equals(username)) {
                index = i;
            }
        }

        for (int i = index; i < tempTurnOrder.size(); i++) { // Add from the index to the end of the array
            turnOrder.add(tempTurnOrder.get(i));
        }

        for (int i = 0; i < tempTurnOrder.size(); i++) { // Add from 0 to the index
            if(i == index) {
                break;
            }
            turnOrder.add(tempTurnOrder.get(i));
        }

        return turnOrder;
    }

    /* *********** GETTERS AND SETTERS *********** */

    public String getGameId() {
        return gameId;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public Player getHost() {
        return host;
    }

    public Set<Player> getPlayerList() {
        return playerList;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setClientProxy(IClientInGame clientProxy) {this.clientProxy = clientProxy;}

    public List<Player.PlayerColor> getAvailableColors() {
        return availableColors;
    }

    public void setAvailableColors(List<Player.PlayerColor> availableColors) {
        this.availableColors = availableColors;
    }

    public ArrayList<Chat> getChatHistory() {
        return chatHistory;
    }

    public ArrayList<TrainCard> getTrainCardsFaceUp() {
        return trainCardsFaceUp;
    }

    public int getReadyPlayers() {
        return readyPlayers;
    }

    public void setReadyPlayers(int readyPlayers) {
        this.readyPlayers = readyPlayers;
    }

    public ArrayList<Route> getAvailableRoutes() {
        return availableRoutes;
    }

    public ArrayList<Route> getClaimedRoutes() {
        return claimedRoutes;
    }
}