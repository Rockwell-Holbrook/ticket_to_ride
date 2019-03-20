package com.example.shared.cpu_player;

import com.example.shared.model.Game;

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
        if (game.calculateClaimableRoutes(player.getUsername()).size() > 0){
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
        if (game.calculateClaimableRoutes(player.getUsername()).size() > 0){
            player.setCpuState(HasAvailableRoute.getInstance());
        }
    }
}
