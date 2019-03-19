package com.example.shared.model;

public class Ticket {
    private int ticketId;
    private String firstCity;
    private String secondCity;
    private int point;
    private boolean isCompleted;

    public Ticket(int ticketId, String firstCity, String secondCity, int point) {
        this.ticketId = ticketId;
        this.firstCity = firstCity;
        this.secondCity = secondCity;
        this.point = point;
        this.isCompleted = false;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getFirstCity() {
        return firstCity;
    }

    public void setFirstCity(String firstCity) {
        this.firstCity = firstCity;
    }

    public String getSecondCity() {
        return secondCity;
    }

    public void setSecondCity(String secondCity) {
        this.secondCity = secondCity;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
