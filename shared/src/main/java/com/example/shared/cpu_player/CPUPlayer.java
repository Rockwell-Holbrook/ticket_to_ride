package com.example.shared.cpu_player;

import com.example.shared.model.*;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class CPUPlayer extends Player {
    private transient CPUState cpuState;
    private transient Game game;
    private transient Random rand;

    public CPUPlayer(String username, boolean isHost, PlayerColor playerColor, Game game) {
        super(username, isHost, playerColor);
        this.cpuState = CompletedAllTickets.getInstance();
        this.game = game;
        this.rand = new Random();
    }

    void setCpuState(CPUState state) {
        this.cpuState = state;
    }

    public void takeTurn() {
        System.out.println(this.username + " starting a turn.");
        try{
            sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        cpuState.takeTurn(this);
        System.out.println(this.username + " ending a turn.");
        game.endPlayerTurn(this.username);
    }

    public void drawCard() {
        System.out.println(this.username + " drawing a card.");

        int randCard = rand.nextInt(Integer.MAX_VALUE) % 5;
        game.cardSelected(this.username, randCard);

        int secondCard = rand.nextInt(Integer.MAX_VALUE) % 5;
        game.cardSelected(this.username, secondCard);

        cpuState.drawCard(this);
    }

    public void claimRoute() {
        System.out.println(this.username + " claiming a route.");

        // Get all claimable routes and pick a random one
        ArrayList<Route> routes = availableToCpu(game);

        int randRouteInd = rand.nextInt(Integer.MAX_VALUE) % routes.size();
        Route randRoute = routes.get(randRouteInd);

        // Provide the needed cards
        TrainCard.Color routeColor = game.getCardColorFromRouteColor(randRoute.getColor());
        ArrayList<TrainCard> cardsToUse = new ArrayList<>();
        for (int i = 0, cnt = 0; i < this.trainCards.size() && cnt < randRoute.getLength(); i++){
            TrainCard card = this.trainCards.get(i);
            if (card.getColor() == routeColor || card.getColor() == TrainCard.Color.WILD){
                cardsToUse.add(card);
                cnt++;
            }
        }

        game.claimRoute(this.username, randRoute.getGroupId(), cardsToUse);
        cpuState.claimRoute(this);
    }

    public void drawTickets() {
        System.out.println(this.username + " drawing tickets");

        // Always just takes the first one
        ArrayList<Ticket> ticketsDrawn = game.initializeTickets(this.username);
        ArrayList<Ticket> returning = new ArrayList<>();
        returning.add(ticketsDrawn.get(1));
        returning.add(ticketsDrawn.get(2));

        game.ticketsReturned(game.getGameId(), this.username, returning);
        cpuState.drawTickets(this);
    }

    private ArrayList<Route> filterGray(ArrayList<Route> routes){
        ArrayList<Route> temp = new ArrayList<>();
        for (Route r: routes){
            if (r.getColor() != Route.RouteColor.GRAY){
                temp.add(r);
            }
        }
        return temp;
    }

    public ArrayList<Route> availableToCpu(Game game){
        ArrayList<Route> routes = game.calculateClaimableRoutes(this.getUsername());
        return filterGray(routes);
    }

    public Game getGame() {
        return game;
    }
}
