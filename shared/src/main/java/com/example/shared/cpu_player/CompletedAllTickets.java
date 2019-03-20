package com.example.shared.cpu_player;

import com.example.shared.model.Game;

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
        if (game.calculateClaimableRoutes(player.getUsername()).size() > 0){
            player.setCpuState(HasAvailableRoute.getInstance());
        }
        else{
            player.setCpuState(NoRouteIncompleteTicket.getInstance());
        }
    }
}
