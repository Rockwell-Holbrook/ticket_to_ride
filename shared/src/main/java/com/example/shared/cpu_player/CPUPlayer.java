package com.example.shared.cpu_player;

import com.example.shared.model.Player;

public class CPUPlayer extends Player {
    private CPUState cpuState;

    public CPUPlayer(String username, boolean isHost, PlayerColor playerColor) {
        super(username, isHost, playerColor);
        cpuState = new CompletedAllTickets();
    }

    void setCpuState(CPUState state) {
        this.cpuState = state;
    }

    public void takeTurn(CPUPlayer player) {
        cpuState.takeTurn(this);
    }

    private void drawCard(CPUPlayer player) {
        // TODO Draw card logic
        cpuState.drawCard(this);
    }

    private void claimRoute(CPUPlayer player) {
        // TODO claim route logic
        cpuState.claimRoute(this);
    }

    private void drawTickets(CPUPlayer player) {
        // TODO draw tickets logic
        cpuState.drawTickets(this);
    }


}
