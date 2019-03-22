package com.example.shared.cpu_player;

import com.example.shared.model.Game;
import com.example.shared.model.Route;

import java.util.ArrayList;

public class CompletedAllTickets extends CPUState {
    static private CompletedAllTickets instance;

    static public CompletedAllTickets getInstance(){
        if (instance == null){
            instance = new CompletedAllTickets();
        }
        return instance;
    }

    private CompletedAllTickets(){

    }
    public void takeTurn(CPUPlayer player) {
        player.drawTickets();
    }

    public void drawTickets(CPUPlayer player) {
        Game game = player.getGame();
        // If a route is claimable
        ArrayList<Route> claimable = game.calculateClaimableRoutes(player.getUsername());
        System.out.println("Claimable for " + player.getUsername() +": " + Integer.toString(claimable.size()));
        if (claimable.size() > 0){
            System.out.println(player.getUsername() + " switching to available route state");
            player.setCpuState(HasAvailableRoute.getInstance());
        }
        else{
            System.out.println(player.getUsername() + " switching to no route state");
            player.setCpuState(NoRouteIncompleteTicket.getInstance());
        }
    }
}
