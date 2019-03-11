package com.example.shared.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String username;
    private boolean isHost;
    private PlayerColor playerColor;
    private List<TrainCard> trainCards;
    private List<Ticket> tickets;
    private List<Route> claimedRoutes;
    private int remainingTrainCars;
    private int pointsEarned;


    public Player(String username, boolean isHost, PlayerColor playerColor) {
        this.username = username;
        this.isHost = isHost;
        this.playerColor = playerColor;
        pointsEarned = 0;
        remainingTrainCars = 45;
        trainCards = new ArrayList<>();
        tickets = new ArrayList<>();
        claimedRoutes = new ArrayList<>();
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

    public List<TrainCard> getTrainCards() {
        return trainCards;
    }

    public void setTrainCards(List<TrainCard> trainCards) {
        this.trainCards = trainCards;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Route> getClaimedRoutes() {
        return claimedRoutes;
    }

    public void setClaimedRoutes(List<Route> claimedRoutes) {
        this.claimedRoutes = claimedRoutes;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public void addCard(TrainCard trainCard) {
        this.trainCards.add(trainCard);
    }

    public void addPoints(int pointValue) {
        this.pointsEarned += pointValue;
    }

    public void subtractCarNumber(int length) {
        this.remainingTrainCars -= length;
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

    public void addClaimedRoute(Route addedRoute) {
        this.claimedRoutes.add(addedRoute);
    }
}