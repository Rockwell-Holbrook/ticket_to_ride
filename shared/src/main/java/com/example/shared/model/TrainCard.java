package com.example.shared.model;

public class TrainCard {
    private Color color;

    public TrainCard(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public enum Color {
        PINK, WHITE, BLUE, YELLOW, ORANGE, BLACK, RED, GREEN, WILD;
    }
}
