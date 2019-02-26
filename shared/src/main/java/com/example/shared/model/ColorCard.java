package com.example.shared.model;

public class ColorCard extends TrainCard {
    private Color color;

    public ColorCard(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public enum Color {
        PINK, WHITE, BLUE, YELLOW, ORANGE, BLACK, RED, GREEN;
    }
}
