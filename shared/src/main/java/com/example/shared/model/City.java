package com.example.shared.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class City {
    private String name;

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass())
            return false;
        City node = (City) o;
        return node.name.equals(this.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
