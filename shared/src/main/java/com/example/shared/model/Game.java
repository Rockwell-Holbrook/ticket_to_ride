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
    private Deck<Ticket> ticketDeck;
    private Deck<TrainCard> trainCardDeck;

    public Game(Player host, int maxPlayers, String gameName) {
        this.host = host;
        this.gameName = gameName;
        this.isPlaying = false;
        this.maxPlayers = maxPlayers;
        this.gameId = UUID.randomUUID().toString();
        this.availableColors = new ArrayList<>();
        this.availableRoutes =  new ArrayList<>();
        this.claimedRoutes =  new ArrayList<>();
        this.chatHistory =  new ArrayList<>();
        this.gameHistory =  new ArrayList<>();
        this.ticketDeck = new Deck<>(this.populateTicketDeck());
        this.trainCardDeck = new Deck<>(this.populateTrainCardDeck());

        this.ticketDeck.shuffle();
        this.trainCardDeck.shuffle();
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

    public void initializeTrainCardsFaceUp() {
        ArrayList<TrainCard> temp = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            temp.add(this.trainCardDeck.drawFromTop());
        }

        this.trainCardsFaceUp = temp;
    }

    public ArrayList<TrainCard> initializeTrainCards() {
        ArrayList<TrainCard> temp = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            temp.add(this.trainCardDeck.drawFromTop());
        }

        return temp;
    }

    public ArrayList<Ticket> initializeTickets() {
        ArrayList<Ticket> temp = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            temp.add(this.ticketDeck.drawFromTop());
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

    private ArrayList<Ticket> populateTicketDeck() {
        ArrayList<Ticket> temp = new ArrayList<>();
        int index = 1;
        temp.add(new Ticket(index, "Los Angeles", "New York City", 21));     index++;
        temp.add(new Ticket(index, "Duluth", "Houston", 8));                 index++;
        temp.add(new Ticket(index, "Sault Ste Marie", "Nashville", 8));      index++;
        temp.add(new Ticket(index, "New York", "Atlanta", 6));               index++;
        temp.add(new Ticket(index, "Portland", "Nashville", 17));            index++;
        temp.add(new Ticket(index, "Vancouver", "Montréal", 20));            index++;
        temp.add(new Ticket(index, "Duluth", "El Paso", 10));                index++;
        temp.add(new Ticket(index, "Toronto", "Miami", 10));                 index++;
        temp.add(new Ticket(index, "Portland", "Phoenix", 11));              index++;
        temp.add(new Ticket(index, "Dallas", "New York City", 11));          index++;
        temp.add(new Ticket(index, "Calgary", "Salt Lake City", 7));         index++;
        temp.add(new Ticket(index, "Calgary", "Phoenix", 13));               index++;
        temp.add(new Ticket(index, "Los Angeles", "Miami", 20));             index++;
        temp.add(new Ticket(index, "Winnipeg", "Little Rock", 11));          index++;
        temp.add(new Ticket(index, "San Francisco", "Atlanta", 17));         index++;
        temp.add(new Ticket(index, "Kansas City", "Houston", 5));            index++;
        temp.add(new Ticket(index, "Los Angeles", "Chicago", 16));           index++;
        temp.add(new Ticket(index, "Denver", "Pittsburgh", 11));             index++;
        temp.add(new Ticket(index, "Chicago", "Santa Fe", 9));               index++;
        temp.add(new Ticket(index, "Vancouver", "Santa Fe", 13));            index++;
        temp.add(new Ticket(index, "Boston", "Miami", 12));                  index++;
        temp.add(new Ticket(index, "Chicago", "New Orleans", 7));            index++;
        temp.add(new Ticket(index, "Montreal", "Atlanta", 9));               index++;
        temp.add(new Ticket(index, "Seattle", "New York City", 22));         index++;
        temp.add(new Ticket(index, "Denver", "El Paso", 4));                 index++;
        temp.add(new Ticket(index, "Helena", "Los Angeles", 8));             index++;
        temp.add(new Ticket(index, "Winnipeg", "Houston", 12));              index++;
        temp.add(new Ticket(index, "Montreal", "New Orleans", 13));          index++;
        temp.add(new Ticket(index, "Sault Ste Marie", "Oklahoma City", 9));  index++;
        temp.add(new Ticket(index, "Seattle", "Los Angeles", 9));
        return temp;
    }

    private ArrayList<TrainCard> populateTrainCardDeck() {
        ArrayList<TrainCard> temp = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            if(i < 12) {
                temp.add(new TrainCard(TrainCard.Color.BLACK));
                temp.add(new TrainCard(TrainCard.Color.BLUE));
                temp.add(new TrainCard(TrainCard.Color.GREEN));
                temp.add(new TrainCard(TrainCard.Color.ORANGE));
                temp.add(new TrainCard(TrainCard.Color.PINK));
                temp.add(new TrainCard(TrainCard.Color.RED));
                temp.add(new TrainCard(TrainCard.Color.WHITE));
                temp.add(new TrainCard(TrainCard.Color.YELLOW));
            }
            temp.add(new TrainCard(TrainCard.Color.WILD));
        }

        return temp;
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