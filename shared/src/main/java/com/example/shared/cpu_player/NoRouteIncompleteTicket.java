package com.example.shared.cpu_player;

import com.example.shared.model.Game;
import com.example.shared.model.Route;

import java.util.ArrayList;

public class NoRouteIncompleteTicket extends CPUState {
    static private NoRouteIncompleteTicket instance;

    static public NoRouteIncompleteTicket getInstance(){
        if (instance == null){
            instance = new NoRouteIncompleteTicket();
        }
        return instance;
    }

    private NoRouteIncompleteTicket(){

    }

    public void takeTurn(CPUPlayer player) {
        Game game = player.getGame();

        // If a route is claimable
        ArrayList<Route> claimable = player.availableToCpu(game);
        System.out.println("Claimable for " + player.getUsername() +": " + Integer.toString(claimable.size()));
        if (claimable.size() > 0){
            System.out.println(player.getUsername() + " switching to available route state");

            player.setCpuState(HasAvailableRoute.getInstance());
            player.claimRoute();
        }
        else{
            player.drawCard();
        }
    }

    public void drawCard(CPUPlayer player) {
        Game game = player.getGame();
        // If a route is claimable
        ArrayList<Route> claimable = player.availableToCpu(game);
        System.out.println("Claimable for " + player.getUsername() +": " + Integer.toString(claimable.size()));
        if (claimable.size() > 0){
            System.out.println(player.getUsername() + " switching to available route state");
            player.setCpuState(HasAvailableRoute.getInstance());
        }
    }
}
