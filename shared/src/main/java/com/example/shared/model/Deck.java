package com.example.shared.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck<T> {
    private List<T> inUse;
    private List<T> discarded;

    /**
     * Generic class for implementing a deck of cards that has an associated discard pile
     *
     * @param initialList A list of 'card' objects (type T) that the deck will be initialized to
     */
    public Deck(ArrayList<T> initialList) {
        this.inUse = initialList;
        this.discarded = new ArrayList<>();
    }

    /**
     * Shuffles the deck that is in use
     */
    public void shuffle() {
        Random rand = new Random();
        ArrayList<T> newDeck = new ArrayList<>();

        while (inUse.size() > 0) {
            // Until there are no more cards in inUse
            // Remove a random card from the inUse deck and add it to the new deck
            newDeck.add(inUse.remove(rand.nextInt() % inUse.size()));
        }
        // Make the new deck the in use deck
        inUse = newDeck;
    }

    /**
     * Removes the first card in the deck and returns it
     *
     * @return Card at top of deck or null if there is none
     */
    public T drawFromTop() {
        if (inUse.size() == 0) {
            return null;
        }
        return inUse.remove(0);
    }

    /**
     * Will swap the deck that is in use with the discard pile
     */
    public void swapDecks() {
        List<T> temp = inUse;
        inUse = discarded;
        discarded = temp;
    }

    /**
     * Slips the given card under the deck (puts it at the end)
     *
     * @param card Card to slip in
     */
    public void putToBottom(T card) {
        inUse.add(card);
    }

    /**
     * Takes the given card and adds it to the discard pile
     *
     * @param card Card to discard
     */
    public void discard(T card) {
        discarded.add(card);
    }

    /**
     * Gets the size of the in-use deck
     *
     * @return Size of the deck as int
     */
    public int getDeckSize() {
        return this.inUse.size();
    }

    public List<T> getDeckList(){
        return inUse;
    }
}
