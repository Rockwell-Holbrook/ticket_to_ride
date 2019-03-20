package com.example.shared.cpu_player;

import com.example.shared.model.Game;
import com.example.shared.model.Ticket;

public class HasAvailableRoute extends CPUState {
    static private HasAvailableRoute instance;

    static public HasAvailableRoute getInstance(){
        if (instance == null){
            instance = new HasAvailableRoute();
        }
        return instance;
    }

    private HasAvailableRoute(){

    }
    
    public void takeTurn(CPUPlayer player) {
        Game game = player.getGame();

        // If no available route anymore
        if (game.calculateClaimableRoutes(player.getUsername()).size() == 0){
            // New State
            player.setCpuState(NoRouteIncompleteTicket.getInstance());
            player.drawCard();
        }
        else {
            player.claimRoute();
        }
    }

    public void claimRoute(CPUPlayer player) {
        Game game = player.getGame();

        boolean allTicketsDone = true;
        for (Ticket ticket : player.getTickets()){
            // TODO actually use function for finding out if ticket is done
            allTicketsDone = false;
        }

        if (allTicketsDone) {
            player.setCpuState(CompletedAllTickets.getInstance());
        }
        // If no more available routes
        else if (game.calculateClaimableRoutes(player.getUsername()).size() == 0){
            player.setCpuState(NoRouteIncompleteTicket.getInstance());
        }
        // Otherwise, stay in this state
    }
}
