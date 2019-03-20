package com.example.shared.cpu_player;

import com.example.shared.model.*;

import java.util.ArrayList;
import java.util.Random;

public class CPUPlayer extends Player {
    private CPUState cpuState;
    private Game game;
    private Random rand;

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
        cpuState.takeTurn(this);
        game.endPlayerTurn(this.username);
    }

    public void drawCard() {
        int randCard = rand.nextInt(Integer.MAX_VALUE) % 5;
        game.cardSelected(this.username, randCard);

        cpuState.drawCard(this);
    }

    public void claimRoute() {
        // Get all claimable routes and pick a random one
        ArrayList<Route> routes = game.calculateClaimableRoutes(this.username);
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

        game.claimRoute(this.username, randRouteInd, cardsToUse);
        cpuState.claimRoute(this);
    }

    public void drawTickets() {
        // Always just takes the first one
        ArrayList<Ticket> ticketsDrawn = game.initializeTickets(this.username);
        ArrayList<Ticket> returning = new ArrayList<>();
        returning.add(ticketsDrawn.get(1));
        returning.add(ticketsDrawn.get(2));

        game.ticketsReturned(game.getGameId(), this.username, returning);
        cpuState.drawTickets(this);
    }

    public Game getGame() {
        return game;
    }
}
