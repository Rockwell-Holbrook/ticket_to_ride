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
        availableRoutes.add(new Route(1, new City("Denver"), new City("Salt Lake City"), Route.RouteColor.BLACK, 7, 4));
        availableRoutes.add(new Route(69, new City("Denver"), new City("Salt Lake City"), Route.RouteColor.BLACK, 7, 4));
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
        boolean valid = false;

        // Until we get a valid face up config
        while(!valid){
            // Fill temp
            for (int i = 0; i < 5; i++) {
                temp.add(this.trainCardDeck.drawFromTop());
            }

            // Check if temp is a valid face up config
            valid = isValidFaceUp(temp);

            // If it's not valid, discard all and make a new temp
            if(!valid){
                for (TrainCard tc: temp) {
                    this.trainCardDeck.discard(tc);
                }
                temp = new ArrayList<>();
            }
        }

        this.trainCardsFaceUp = temp;
    }

    boolean isValidFaceUp(ArrayList<TrainCard> faceUp){
        int pinkCnt = 0;
        int whiteCnt = 0;
        int blueCnt = 0;
        int yellowCnt = 0;
        int orangeCnt = 0;
        int blackCnt = 0;
        int redCnt = 0;
        int greenCnt = 0;
        int wildCnt = 0;

        final int COLOR_MAX = 4;
        final int WILD_MAX = 3;

        for(TrainCard tc : faceUp){
            switch (tc.getColor()){
                case PINK:
                    pinkCnt++;
                    break;
                case WHITE:
                    whiteCnt++;
                    break;
                case BLUE:
                    blueCnt++;
                    break;
                case YELLOW:
                    yellowCnt++;
                    break;
                case ORANGE:
                    orangeCnt++;
                    break;
                case BLACK:
                    blackCnt++;
                    break;
                case RED:
                    redCnt++;
                    break;
                case GREEN:
                    greenCnt++;
                    break;
                case WILD:
                    wildCnt++;
                    break;
            }
        }

        return pinkCnt < COLOR_MAX && whiteCnt < COLOR_MAX && blueCnt < COLOR_MAX && yellowCnt < COLOR_MAX &&
                orangeCnt < COLOR_MAX && blackCnt < COLOR_MAX && redCnt < COLOR_MAX && greenCnt < COLOR_MAX &&
                wildCnt < WILD_MAX;
    }

    public ArrayList<TrainCard> initializeTrainCardsInHand(String username) {
        for (int i = 0; i < 4; i++) {
            getPlayerWithUsername(username).addTrainCard(this.trainCardDeck.drawFromTop());
        }

        return getPlayerWithUsername(username).getTrainCards();
    }

    public ArrayList<Ticket> initializeTickets(String username) {
        ArrayList<Ticket> temp = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Ticket ticket = this.ticketDeck.drawFromTop();
            getPlayerWithUsername(username).addTicket(ticket);
            temp.add(ticket);
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

    public void sendDeckCount(){
        clientProxy.sendDeckCount(ticketDeck.getDeckSize(), trainCardDeck.getDeckSize());
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

    public void cardSelected(String username, int index) {
        if (index != 5) {
            getPlayerWithUsername(username).addTrainCard(trainCardsFaceUp.get(index));
            trainCardsFaceUp.set(index, trainCardDeck.drawFromTop());
            clientProxy.updateFaceUpCards(trainCardsFaceUp);
            clientProxy.sendDeckCount(ticketDeck.getDeckSize(), trainCardDeck.getDeckSize());
        }

        else { //5 is the index for the face-down-deck
            TrainCard newCard = trainCardDeck.drawFromTop();
            getPlayerWithUsername(username).addTrainCard(newCard);
            clientProxy.receiveFaceDownCard(newCard, username, gameId);
            clientProxy.sendDeckCount(ticketDeck.getDeckSize(), trainCardDeck.getDeckSize());
        }
    }

    public void ticketsRequested(String username) {
        clientProxy.ticketsReceived(this.initializeTickets(username), username, gameId);
    }

    public void ticketsReturned(String gameId, String username, ArrayList<Ticket> returned) {
        for (int i = 0; i < returned.size(); i++) {
            this.ticketDeck.putToBottom(returned.get(i));
        }

        getPlayerWithUsername(username).returnedTickets(returned);
//        clientProxy.ticketsReceived(getPlayerWithUsername(username).getTickets(), username, gameId);
        clientProxy.sendDeckCount(ticketDeck.getDeckSize(), trainCardDeck.getDeckSize());
    }

    public void claimRoute(String username, int routeId) {
        Route routeToClaim = Route.ROUTE_GROUP_MAP.get(routeId);

        getPlayerWithUsername(username).claimRoute(routeToClaim);
        getPlayerWithUsername(username).removeTrainCard(routeToClaim.getLength(), routeToClaim.getColor()); //todo: make removeTrainCard Work

        for (int i = 0; i <routeToClaim.getLength() ; i++) {
            //todo: route and trainCard colors are not equivalent.. Need to implement adding these cards to the discard deck after they were used and removed from hand above.
        }

        this.availableRoutes.remove(routeToClaim);
        this.claimedRoutes.add(routeToClaim);

        clientProxy.routeClaimed(getPlayerWithUsername(username), routeToClaim);
    }

    public void endPlayerTurn(String username) {
        clientProxy.turnEnded(getPlayerWithUsername(username));
        startNextTurn(getPlayerWithUsername(username));
    }

    private void startNextTurn(Player player) {
        ArrayList<Player> turnOrder = new ArrayList<>(this.playerList);
        int index = turnOrder.indexOf(player);
        if (index + 1 < maxPlayers) {
            Player newTurn = turnOrder.get(index + 1);
            clientProxy.startTurn(getAvailableRoutes(), newTurn.getUsername(), gameId);
            clientProxy.turnStarted(newTurn, gameId);
        } else {
            Player newTurn = turnOrder.get(0);
            clientProxy.startTurn(getAvailableRoutes(), newTurn.getUsername(), gameId);
            clientProxy.turnStarted(newTurn, gameId);
        }
    }

    public Player getPlayerWithUsername(String username) {
        for (Player player : playerList) {
            if (player.getUsername().equals(username)) {
                return player;
            }
        }
        return null;
    }

    public void calculateClaimableRoutes(String username) { //todo: need to do a check for already owning the same 2-way route.
         Map<TrainCard.Color, Integer> cardGroupings = getTrainCardGroupings(username);
         ArrayList<Route> claimableRoutes = new ArrayList<>();

         for(Route route : this.availableRoutes) {
             if(route.getColor() == Route.RouteColor.GRAY) {
                 for (Map.Entry<TrainCard.Color, Integer> entry : cardGroupings.entrySet()) {
                     if(route.getLength() <= entry.getValue()) {
                         claimableRoutes.add(route);
                         break;
                     }
                 }
             }
             else if (route.getLength() <= cardGroupings.get(getCardColorFromRouteColor(route.getColor()))) {
                 claimableRoutes.add(route);
             }
         }
         clientProxy.getClaimableRoutes(claimableRoutes, username, this.gameId);
    }

    private Map<TrainCard.Color, Integer> getTrainCardGroupings(String username) {
        Map<TrainCard.Color, Integer> cardGroupings = new HashMap<>();

        cardGroupings.put(TrainCard.Color.BLACK, 0);
        cardGroupings.put(TrainCard.Color.PINK, 0);
        cardGroupings.put(TrainCard.Color.BLUE, 0);
        cardGroupings.put(TrainCard.Color.GREEN, 0);
        cardGroupings.put(TrainCard.Color.ORANGE, 0);
        cardGroupings.put(TrainCard.Color.RED, 0);
        cardGroupings.put(TrainCard.Color.WHITE, 0);
        cardGroupings.put(TrainCard.Color.YELLOW, 0);
        cardGroupings.put(TrainCard.Color.WILD, 0);

        for(TrainCard card : getPlayerWithUsername(username).getTrainCards()) {
            if(card.getColor() == TrainCard.Color.WILD) {
                for (Map.Entry<TrainCard.Color, Integer> entry : cardGroupings.entrySet()) {
                    cardGroupings.put(entry.getKey(), entry.getValue() + 1);
                }
            }
            else {
                cardGroupings.put(card.getColor(), cardGroupings.get(card.getColor()) + 1);
            }
        }
        return cardGroupings;
    }

    private TrainCard.Color getCardColorFromRouteColor(Route.RouteColor routeColor) {
        switch(routeColor) {
            case YELLOW:
                return TrainCard.Color.YELLOW;
            case ORANGE:
                return TrainCard.Color.ORANGE;
            case PINK:
                return TrainCard.Color.PINK;
            case BLUE:
                return TrainCard.Color.BLUE;
            case RED:
                return TrainCard.Color.RED;
            case BLACK:
                return TrainCard.Color.BLACK;
            case GREEN:
                return TrainCard.Color.GREEN;
            case WHITE:
                return TrainCard.Color.WHITE;
            default:
                return null;
        }
    }

    /* ******************************************** GETTERS AND SETTERS ******************************************** */

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