package com.example.shared.cpu_player;

import com.example.shared.model.Game;
import com.example.shared.model.Route;
import com.example.shared.model.Ticket;

import java.util.ArrayList;

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
        ArrayList<Route> claimable = player.availableToCpu(game);
        System.out.println("Claimable for " + player.getUsername() +": " + Integer.toString(claimable.size()));
        if (claimable.size() == 0){
            // New State
            System.out.println(player.getUsername() + " switching to no route state");
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
            System.out.println(player.getUsername() + " switching to completed route state");
            player.setCpuState(CompletedAllTickets.getInstance());
        }
        // If no more available routes
        ArrayList<Route> claimable = player.availableToCpu(game);
        System.out.println("Claimable for " + player.getUsername() +": " + Integer.toString(claimable.size()));
        if (claimable.size() == 0){
            System.out.println(player.getUsername() + " switching to no route state");
            player.setCpuState(NoRouteIncompleteTicket.getInstance());
        }
        // Otherwise, stay in this state
    }
}
