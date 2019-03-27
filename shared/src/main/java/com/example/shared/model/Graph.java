package com.example.shared.model;

import java.util.*;

/* Note: this is currently implemented as an undirected graph */
/* Note: this currently only supports integer edge lengths */
/* precondition: node values must be unique */
/* assumes at most one edge between any given pair of nodes */
/* assumes no node has an edge to itself */

public class Graph<T> {
    private final int BASE = -1;

    private class Node {
        Map<T, Integer> neighbors;

        Node() {
            this.neighbors = new HashMap<>();
        }

        void addNeighbor(T neighbor, int length) {
            this.neighbors.put(neighbor, length);
        }
    }

    public class Edge {
        T startNode;
        T endNode;
        int length;

        Edge(T start, T end, int len) {
            startNode = start;
            endNode = end;
            length = len;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (o.getClass() != this.getClass()) {
                return false;
            }
            Edge other = (Edge) o;
            return this.startNode.equals(other.startNode) && this.endNode.equals(other.endNode) && this.length == other.length;
        }

        @Override
        public int hashCode() {
            return startNode.hashCode() + endNode.hashCode() + length * 31;
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

    public void addEdge(T start, T end, int length) {
        if (!hasNode(start)) {
            addNode(start);
        }
        if (!hasNode(end)) {
            addNode(end);
        }
        nodes.get(start).addNeighbor(end, length);
        nodes.get(end).addNeighbor(start, length);
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

    public int getLongestPath() {
        Set<Set<Edge>> paths = new HashSet<>();
        for (T node1 : nodes.keySet()) {
            for (T node2 : nodes.keySet()) {
                Set<Set<Edge>> allDaPaths = findPaths(node1, node2);
                if (allDaPaths != null) {
                    paths.addAll(allDaPaths);
                }
            }
        }
        Set<Edge> longest = getLongest(paths);
        return getLength(longest);
    }

    private void explore(T start) {
        visited.put(start, true);
        Node node = nodes.get(start);
        for (T neighbor : node.neighbors.keySet()) {
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

    private Set<Set<Edge>> findPaths(T start, T end) {
        return findAllPaths(start, end, new HashSet<Edge>());
    }

    private Set<Set<Edge>> findAllPaths(T start, T end, Set<Edge> traversed) {
        Set<Set<Edge>> paths = new HashSet<>();
        Node node = nodes.get(start);
        for (T neighbor : node.neighbors.keySet()) {
            Edge edge = new Edge(start, neighbor, node.neighbors.get(neighbor));
            Edge reverseEdge = new Edge(neighbor, start, node.neighbors.get(neighbor));
            if (!traversed.contains(edge) && !traversed.contains(reverseEdge)) {
                if (neighbor.equals(end)) {
                    Set<Edge> path = new HashSet<>();
                    path.add(edge);
                    paths.add(path);
                }
                Set<Edge> traversedCopy = new HashSet<>(traversed);
                traversedCopy.add(edge);
                traversedCopy.add(reverseEdge);
                Set<Set<Edge>> newPaths = findAllPaths(neighbor, end, traversedCopy);
                if (newPaths != null) {
                    for (Set<Edge> newPath : newPaths) {
                        if (newPath != null) {
                            Set<Edge> path = new HashSet<>();
                            path.add(edge);
                            path.addAll(newPath);
                            paths.add(path);
                        }
                    }
                }
            }
        }
        if (paths.size() == 0) {
            if (start != end) { return null; }
        }
        return paths;
    }

    private Set<Edge> getLongest(Set<Set<Edge>> paths) {
        Set<Edge> longest = null;
        int longestLength = 0;
        for (Set<Edge> path : paths) {
            if (getLength(path) > longestLength) {
                longest = path;
                longestLength = getLength(path);
            }
        }
        return longest;
    }

    private int getLength(Set<Edge> path) {
        int length = 0;
        for (Edge edge : path) {
            length += edge.length;
        }
        return length;
    }
}
