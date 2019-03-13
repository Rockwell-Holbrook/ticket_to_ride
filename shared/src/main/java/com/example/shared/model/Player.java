package com.example.shared.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String username;
    private boolean isHost;
    private PlayerColor playerColor;
    private ArrayList<TrainCard> trainCards;
    private ArrayList<Ticket> tickets;
    private ArrayList<Route> claimedRoutes;
    private int remainingTrainCars;
    private int pointsEarned;


    public Player(String username, boolean isHost, PlayerColor playerColor) {
        this.username = username;
        this.isHost = isHost;
        this.playerColor = playerColor;
        this.trainCards = new ArrayList<>();
        this.tickets = new ArrayList<>();
        this.claimedRoutes = new ArrayList<>();
        pointsEarned = 0;
        remainingTrainCars = 45;
    }

    public int getRemainingTrainCars() {
        return remainingTrainCars;
    }

    public void setRemainingTrainCars(int remainingTrainCars) {
        this.remainingTrainCars = remainingTrainCars;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isHost() {
        return isHost;
    }

    public void setHost(boolean host) {
        isHost = host;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setColor(PlayerColor color) {
        this.playerColor = color;
    }

    public ArrayList<TrainCard> getTrainCards() {
        return trainCards;
    }

    public void setTrainCards(List<TrainCard> trainCards) {
        trainCards = trainCards;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public ArrayList<Route> getClaimedRoutes() {
        return claimedRoutes;
    }

    public void setClaimedRoutes(ArrayList<Route> claimedRoutes) {
        this.claimedRoutes = claimedRoutes;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public void addTrainCard(TrainCard trainCard) {
        this.trainCards.add(trainCard);
    }

    public void removeTrainCard(int number, Route.RouteColor color) {
        //Todo: MAKE THIS WORK
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void returnedTickets(ArrayList<Ticket> returnedTickets) {
        for (int i = 0; i < returnedTickets.size(); i++) {
            this.tickets.remove(returnedTickets.get(i));
        }
    }

    public enum PlayerColor {
        GREEN, BLUE, YELLOW, RED, BLACK
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass())
            return false;

        Player p = (Player) o;

        return p.getUsername().equals(this.getUsername());
    }

    /**
     * Adds route to claimed route list AND adds points AND removes cars
     * @param addedRoute Route to add
     */
    public void claimRoute(Route addedRoute) {
        this.claimedRoutes.add(addedRoute);
        this.pointsEarned += addedRoute.getPointValue();
        this.remainingTrainCars -= addedRoute.getLength();
    }
}