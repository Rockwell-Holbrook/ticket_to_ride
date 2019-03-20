package com.example.shared.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Note: this is currently implemented as an undirected graph */

public class Graph<T> {
    class Node {
        List<T> neighbors;

        Node() {
            this.neighbors = new ArrayList<>();
        }

        void addNeighbor(T neighbor) {
            this.neighbors.add(neighbor);
        }
    }

    private Map<T, Node> nodes;

    private Map<T, Boolean> visited;

    public Graph() {
        nodes = new HashMap<>();
        visited = new HashMap<>();
    }

    public boolean hasNode(T node) {
        return nodes.containsKey(node);
    }

    public void addNode(T value) {
        this.nodes.put(value, new Node());
        this.visited.put(value, false);
    }

    public void addEdge(T start, T end) {
        if (!hasNode(start)) {
            addNode(start);
        }
        if (!hasNode(end)) {
            addNode(end);
        }
        nodes.get(start).addNeighbor(end);
        nodes.get(end).addNeighbor(start);
    }

    public boolean hasPath(T start, T end) {
        if (!hasNode(start) || !hasNode(end)) {
            return false;
        }
        explore(start);
        boolean result = visited.get(end);
        clearVisited();
        return result;
    }

    private void explore(T start) {
        visited.put(start, true);
        Node node = nodes.get(start);
        for (T neighbor : node.neighbors) {
            if (!visited.get(neighbor)) {
                explore(neighbor);
            }
        }
    }

    private void clearVisited() {
        for (T key : visited.keySet()) {
            visited.put(key, false);
        }
    }
}
