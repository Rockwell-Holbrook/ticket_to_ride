package com.example.shared.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DeckTest {
    @Test
    public void canCreate(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Deck<Integer> deck = new Deck<>(list);
        assertEquals(deck.getDeckSize(), 5);
    }

    @Test
    public void getFromTop(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Deck<Integer> deck = new Deck<>(list);
        Integer top = deck.drawFromTop();
        assertEquals(top, new Integer(1));
        assertEquals(deck.getDeckSize(), 4);
    }

    @Test
    public void shuffleTest(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Deck<Integer> deck = new Deck<>(list);
        deck.shuffle();
        System.out.println(deck.getDeckList().toString());
    }

    @Test
    public void swapTest(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Deck<Integer> deck = new Deck<>(list);

        deck.discard(6);
        deck.discard(7);

        deck.swapDecks();

        List<Integer> deckList = deck.getDeckList();

        assertEquals(deckList.get(0), new Integer(6));
        assertEquals(deck.getDeckSize(), 2);
    }
}
