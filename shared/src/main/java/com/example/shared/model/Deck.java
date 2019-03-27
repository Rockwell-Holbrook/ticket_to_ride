package com.example.shared.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A generic deck class that holds within it a deck that is in use and a discard pile
 *
 * @param <T> The type of object being held in the deck that will act as a "card"
 * @invariant inUse and discarded are set to lists of type T
 */
public class Deck<T> {
    private List<T> inUse;
    private List<T> discarded;

    /**
     * Generic class for implementing a deck of cards that has an associated discard pile
     *
     * @param initialList A list of 'card' objects (type T) that the deck will be initialized to
     * @pre Initial list is an array list of type T
     * @post InUse is set to the list passed in. Discarded is an empty list.
     */
    public Deck(ArrayList<T> initialList) {
        this.inUse = initialList;
        this.discarded = new ArrayList<>();
    }

    /**
     * Shuffles the deck that is in use
     * @pre inUse is set to a list object
     * @post inUse is not the same as it was previously (although its random and could be the same)
     */
    public void shuffle() {
        Random rand = new Random();
        ArrayList<T> newDeck = new ArrayList<>();

        while (inUse.size() > 0) {
            // Until there are no more cards in inUse
            // Remove a random card from the inUse deck and add it to the new deck
            newDeck.add(inUse.remove(rand.nextInt(Integer.MAX_VALUE) % inUse.size()));
        }
        // Make the new deck the in use deck
        inUse = newDeck;
    }

    /**
     * Removes the first card in the deck and returns it
     *
     * @return Card at top of deck or null if there is none
     * @pre inUse is set to a list object
     * @post inUse is one less if it was greater than zero or still zero if it was empty,
     *          and inUse does not have the returned object at the front of the inUse list.
     */
    public T drawFromTop() {
        if (inUse.size() == 0) {
            return null;
        }
        return inUse.remove(0);
    }

    /**
     * Will swap the deck that is in use with the discard pile
     *
     * @pre inUse and discarded are set to lists
     * @post inUse and discarded have been swapped
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
     * @pre inUse and discarded are lists
     * @post inUse grows by 1 and the last element is equal to the card parameter
     */
    public void putToBottom(T card) {
        inUse.add(card);
    }

    /**
     * Takes the given card and adds it to the discard pile
     *
     * @param card Card to discard
     * @pre class invariant
     * @post discarded is one larger and the last element is equal to card parameter
     */
    public void discard(T card) {
        discarded.add(card);
    }

    public void discard(List<T> cards){
        for (T card : cards){
            discard(card);
        }
    }

    /**
     * Gets the size of the in-use deck
     *
     * @return Size of the deck as int
     */
    public int getDeckSize() {
        return this.inUse.size();
    }

    public int getDiscardDeckSize() {return this.discarded.size();}

    public List<T> getDeckList(){
        return inUse;
    }
}
