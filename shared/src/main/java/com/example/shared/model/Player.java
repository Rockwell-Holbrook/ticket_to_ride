package com.example.shared.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    final int LONGEST_ROUTE_POINT_VALUE = 10;
    final int GLOBETROTTER_POINT_VALUE = 15;
    private String username;
    private boolean isHost;
    private PlayerColor playerColor;
    private ArrayList<TrainCard> trainCards;
    private ArrayList<Ticket> tickets;
    private ArrayList<Route> claimedRoutes;
    private int remainingTrainCars;
    private int pointsEarned;
    private int ticketPoints = 0;
    private int completedTicketCount = 0;
    private int incompletedTicketCount = 0;
    private int completedTicketPoints = 0;
    private int incompleteTicketPoints = 0;
    private int longestRouteCount = 0;
    private int bonusPoints = 0;
    private int totalPoints = 0;
    private boolean hasGlobeTrotter = false;
    private boolean hasLongestRoute = false;
    private boolean isWinner = false;

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

    public boolean isHasGlobeTrotter() {
        return hasGlobeTrotter;
    }

    public void setHasGlobeTrotter(boolean hasGlobeTrotter) {
        this.hasGlobeTrotter = hasGlobeTrotter;
    }

    public boolean isHasLongestRoute() {
        return hasLongestRoute;
    }

    public void setHasLongestRoute(boolean hasLongestRoute) {
        this.hasLongestRoute = hasLongestRoute;
    }

    public int getTicketPoints() {
        return ticketPoints;
    }

    public void setTicketPoints(int ticketPoints) {
        this.ticketPoints = ticketPoints;
    }

    public void setCompletedTicketCount(int completedTicketCount) {
        this.completedTicketCount = completedTicketCount;
    }

    public int getCompletedCount() {
        return completedTicketCount;
    }

    public int getIncompletedTicketCount() {
        return incompletedTicketCount;
    }

    public void setIncompletedTicketCount(int incompletedTicketCount) {
        this.incompletedTicketCount = incompletedTicketCount;
    }

    public int getCompletedTicketPoints() {
        return completedTicketPoints;
    }

    public void setCompletedTicketPoints(int completedTicketPoints) {
        this.completedTicketPoints = completedTicketPoints;
    }

    public int getIncompleteTicketPoints() {
        return incompleteTicketPoints;
    }

    public void setIncompleteTicketPoints(int incompleteTicketPoints) {
        this.incompleteTicketPoints = incompleteTicketPoints;
    }

    public int getLongestRouteCount() {
        return longestRouteCount;
    }

    public void setLongestRouteCount(int longestRouteCount) {
        this.longestRouteCount = longestRouteCount;
    }

    public int getRemainingTrainCars() {
        return remainingTrainCars;
    }

    public int getBonusPoints() {
        return bonusPoints;
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

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public void addTrainCard(TrainCard trainCard) {
        this.trainCards.add(trainCard);
    }

    public void removeTrainCards(List<TrainCard> selectedCards) {
        for (TrainCard trainCard : selectedCards) {
            for (TrainCard card : trainCards) {
                if (card.getColor() == trainCard.getColor()) {
                    trainCards.remove(card);
                    break;
                }
            }
        }
    }

    public void calculateEndGame(ArrayList<Player> players) {
        incompletedTicketCount = getIncompleteTicketCount();
        completedTicketCount = getCompletedTicketCount();
        completedTicketPoints = calculateCompletedTicketPoints();
        incompleteTicketPoints = calculateIncompleteTicketPoints();
        ticketPoints = completedTicketPoints - incompleteTicketPoints;

        if (hasGlobetrotter(players)) {
            hasGlobeTrotter = true;
            bonusPoints += GLOBETROTTER_POINT_VALUE;
        }
        if (hasLongestRoute(players)) {
            hasLongestRoute = true;
            bonusPoints += LONGEST_ROUTE_POINT_VALUE;
        }
        totalPoints += ticketPoints + pointsEarned + bonusPoints;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void returnedTickets(ArrayList<Ticket> returnedTickets) {
        for (Ticket ticket : returnedTickets) {
            removeTicket(ticket);
        }
    }

    private void removeTicket(Ticket ticket) {
        for (Ticket ownedTicket : tickets) {
            if (ownedTicket.getTicketId() == ticket.getTicketId()) {
                tickets.remove(ownedTicket);
                break;
            }
        }
    }

    private int getCompletedTicketCount() {
        int completedTicketCount = 0;
        for (Ticket ticket : tickets) {
            if (ticket.isCompleted()) {
                completedTicketCount++;
            }
        }
        return completedTicketCount;
    }

    private int getIncompleteTicketCount() {
        int incompleteTicketCount = 0;
        for (Ticket ticket : tickets) {
            if (!ticket.isCompleted()) {
                incompleteTicketCount++;
            }
        }
        return incompleteTicketCount;
    }

    private int calculateCompletedTicketPoints() {
        int completedTicketPoints = 0;
        for (Ticket ticket : tickets) {
            if (ticket.isCompleted()) {
                completedTicketPoints += ticket.getPoint();
            }
        }
        return completedTicketPoints;
    }

    private int calculateIncompleteTicketPoints() {
        int incompleteTicketPoints = 0;
        for (Ticket ticket : tickets) {
            if (!ticket.isCompleted()) {
                incompleteTicketPoints += ticket.getPoint();
            }
        }
        return incompleteTicketPoints;
    }

    private boolean hasGlobetrotter(ArrayList<Player> players) {
        for (Player player : players) {
            if (player.getCompletedCount() > this.completedTicketCount) {
                return false;
            }
        }
        return true;
    }

    private boolean hasLongestRoute(ArrayList<Player> players) {
        for (Player player : players) {
            if (player.getLongestRouteCount() > this.longestRouteCount) {
                return false;
            }
        }
        return true;
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