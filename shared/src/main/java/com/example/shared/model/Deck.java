package com.example.shared.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Deck<T> {
    private List<T> inUse;
    private List<T> discarded;

    public Deck(ArrayList<T> initialList) {
        this.inUse = initialList;
        this.discarded = new ArrayList<>();
    }

    public void shuffle(){

    }

    public T drawFromTop(){
        // TODO return top of in use deck
        return null;
    }

    public void swapDecks(){

    }

    public void putToBottom(T card){

    }

    public void discardTicket(T card){

    }

    public void discartTrainCard(T card) {

    }

    public int getDeckSize() {
        return this.inUse.size();
    }
}
