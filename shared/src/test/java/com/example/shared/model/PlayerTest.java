package com.example.shared.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;

public class PlayerTest {
    ArrayList<Player> players;

    @Before
    public void setUp() throws Exception {
        players = new ArrayList<>();
        createPlayerOne("usernameOne", Player.PlayerColor.BLACK);
        createPlayerTwo("usernameTwo", Player.PlayerColor.BLUE);
        createPlayerThree("usernameThree", Player.PlayerColor.RED);
        createPlayerFour("usernameFour", Player.PlayerColor.YELLOW);
        createWinner("usernameFive", Player.PlayerColor.GREEN);
    }

    private void createWinner(String usernameFive, Player.PlayerColor color) {
        Player player = new Player(usernameFive, true, color);
        player.setPointsEarned(120);
        Ticket completedTicket = new Ticket(1, new City("Los Angeles"), new City("New York City"), 21);
        Ticket incompleteTicket = new Ticket(2, new City("Duluth"), new City("Houston"), 8);
        Ticket incompleteTicketTwo = new Ticket(3, new City("Sault Ste Marie"), new City("Nashville"), 8);
        Ticket completedTicketTwo = new Ticket(3, new City("Sault Ste Marie"), new City("Nashville"), 8);
        completedTicket.setCompleted(true);
        incompleteTicket.setCompleted(false);
        incompleteTicketTwo.setCompleted(false);
        completedTicketTwo.setCompleted(true);
        player.addTicket(completedTicket);
        player.addTicket(incompleteTicket);
        player.addTicket(incompleteTicketTwo);
        player.addTicket(completedTicketTwo);
        players.add(player);
    }

    private void createPlayerOne(String usernameOne, Player.PlayerColor color) {
        Player player = new Player(usernameOne, true, color);
        player.setPointsEarned(50);
        Ticket completedTicket = new Ticket(1, new City("Los Angeles"), new City("New York City"), 21);
        Ticket incompleteTicket = new Ticket(2, new City("Duluth"), new City("Houston"), 8);
        completedTicket.setCompleted(true);
        incompleteTicket.setCompleted(false);
        player.addTicket(completedTicket);
        player.addTicket(incompleteTicket);
        player.setLongestRouteCount(45);
        players.add(player);
    }

    private void createPlayerTwo(String usernameOne, Player.PlayerColor color) {
        Player player = new Player(usernameOne, true, color);
        player.setPointsEarned(50);
        Ticket completedTicket = new Ticket(1, new City("Los Angeles"), new City("New York City"), 21);
        Ticket incompleteTicket = new Ticket(2, new City("Duluth"), new City("Houston"), 8);
        completedTicket.setCompleted(true);
        incompleteTicket.setCompleted(false);
        player.addTicket(completedTicket);
        player.addTicket(incompleteTicket);
        players.add(player);
    }

    private void createPlayerThree(String usernameOne, Player.PlayerColor color) {
        Player player = new Player(usernameOne, true, color);
        player.setPointsEarned(50);
        Ticket completedTicket = new Ticket(1, new City("Los Angeles"), new City("New York City"), 21);
        Ticket incompleteTicket = new Ticket(2, new City("Duluth"), new City("Houston"), 8);
        completedTicket.setCompleted(true);
        incompleteTicket.setCompleted(false);
        player.addTicket(completedTicket);
        player.addTicket(incompleteTicket);
        players.add(player);
    }

    private void createPlayerFour(String usernameOne, Player.PlayerColor color) {
        Player player = new Player(usernameOne, true, color);
        player.setPointsEarned(50);
        Ticket completedTicket = new Ticket(1, new City("Los Angeles"), new City("New York City"), 21);
        Ticket incompleteTicket = new Ticket(2, new City("Duluth"), new City("Houston"), 8);
        completedTicket.setCompleted(true);
        incompleteTicket.setCompleted(false);
        player.addTicket(completedTicket);
        player.addTicket(incompleteTicket);
        players.add(player);
    }

    @Test
    public void calculateEndGame() {
        for (Player player: players) {
            player.setCompletedTicketCount(player.getCompletedTicketCount());
        }
        for (Player player: players) {
            player.calculateEndGame(players);
        }

        //Get Players
        Player playerOne = players.get(1);
        Player playerTwo = players.get(2);
        Player playerThree = players.get(3);
        Player playerFour = players.get(4);
        Player playerFive = players.get(0);

        //Test player One
        assertTrue(playerOne.getPointsEarned() == 50);
        assertTrue(playerOne.getCompletedCount() == 1);
        assertTrue(playerOne.getIncompletedTicketCount() == 1);
        assertTrue(playerOne.getTicketPoints() == 13);
    }
}