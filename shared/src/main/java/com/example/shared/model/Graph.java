package com.example.shared.model;

import java.util.*;

/* Note: this is currently implemented as an undirected graph */
/* Note: this currently only supports integer edge lengths */
/* precondition: node values must be unique */
/* assumes at most one edge between any given pair of nodes */

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

    private class Edge {
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

    private class PathMatrix {
        class Entry {
            int dist;
            Set<Edge> path;

            Entry() {
                dist = 0;
                path = new HashSet<>();
            }

            void setDist(int dist) {
                this.dist = dist;
            }
            void setPath(Set<Edge> path) {
                this.path = path;
            }
            int getDist() {
                return dist;
            }
            Set<Edge> getPath() {
                return path;
            }
        }

        List<List<List<Entry>>> matrix;
        List<List<Entry>> base;
        int largestDistance;

        PathMatrix(int size) {
            matrix = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                List<List<Entry>> slice = new ArrayList<>();
                base = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    List<Entry> row = new ArrayList<>();
                    List<Entry> baseRow = new ArrayList<>();
                    for (int k = 0; k < size; k++) {
                        row.add(new Entry());
                        baseRow.add(new Entry());
                    }
                    slice.add(row);
                    base.add(baseRow);
                }
                matrix.add(slice);
            }
            largestDistance = 0;
        }
        void setDist(int i, int j, int k, int dist) {
            if (k == BASE) {
                base.get(i).get(j).setDist(dist);
            } else {
                matrix.get(k).get(i).get(j).setDist(dist);
            }
            if (dist > largestDistance) {
                largestDistance = dist;
            }
        }
        void setPath(int i, int j, int k, Set<Edge> path) {
            if (k == BASE) {
                base.get(i).get(j).setPath(path);
            } else {
                matrix.get(k).get(i).get(j).setPath(path);
            }
        }
        int getDist(int i, int j, int k) {
            if (k == BASE) {
                return base.get(i).get(j).getDist();
            }
            return matrix.get(k).get(i).get(j).getDist();
        }
        Set<Edge> getPath(int i, int j, int k) {
            if (k == BASE) {
                return base.get(i).get(j).getPath();
            }
            return matrix.get(k).get(i).get(j).getPath();
        }
        int getLargestDist() {
            return largestDistance;
        }
    }

    private Map<T, Node> nodes;
    private Set<Edge> edges;
    private Map<T, Boolean> visited;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
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
        edges.add(new Edge(start, end, length));
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
        LinkedHashMap<T, Integer> indexMap = new LinkedHashMap<>();
        int index = 0;
        for (T value : nodes.keySet()) {
            indexMap.put(value, index);
            index++;
        }
        indexMap.put(null, BASE);

        PathMatrix matrix = new PathMatrix(nodes.size());
        for (Edge edge : edges) {
            int start = indexMap.get(edge.startNode);
            int end = indexMap.get(edge.endNode);
            int pivot = indexMap.get(null);
            matrix.setDist(start,end,pivot,edge.length);
            Set<Edge> set = new HashSet<>();
            set.add(edge);
            matrix.setPath(start,end,pivot,set);
        }
        for (T pivotNode : nodes.keySet()) {
            for (T startNode : nodes.keySet()) {
                for (T endNode : nodes.keySet()) {
                    int start = indexMap.get(startNode);
                    int end = indexMap.get(endNode);
                    int pivot = indexMap.get(pivotNode);
                    int dist1 = matrix.getDist(start, pivot, pivot - 1);
                    int dist2 = matrix.getDist(pivot, end, pivot - 1);
                    int oldDist = matrix.getDist(start, end, pivot - 1);
                    Set<Edge> path1 = matrix.getPath(start, pivot, pivot - 1);
                    Set<Edge> path2 = matrix.getPath(pivot, end, pivot - 1);
                    Set<Edge> oldPath = matrix.getPath(start, end, pivot - 1);
                    if (pathsOverlap(path1, path2) || dist1 + dist2 <= oldDist) {
                        matrix.setDist(start, end, pivot, oldDist);
                        matrix.setDist(end, start, pivot, oldDist);
                        matrix.setPath(start, end, pivot, oldPath);
                        matrix.setPath(end, start, pivot, oldPath);
                    } else {
                        matrix.setDist(start, end, pivot, dist1 + dist2);
                        matrix.setDist(end, start, pivot, dist1 + dist2);
                        matrix.setPath(start, end, pivot, getUnion(path1, path2));
                        matrix.setPath(end, start, pivot, getUnion(path1, path2));
                    }
                }
            }
        }
        return matrix.getLargestDist();
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

    private boolean pathsOverlap(Set<Edge> pathA, Set<Edge> pathB) {
        HashSet<Edge> a = new HashSet<>(pathA);
        HashSet<Edge> b = new HashSet<>(pathB);
        a.retainAll(b);
        return a.size() > 0;
    }

    private Set<Edge> getUnion(Set<Edge> pathA, Set<Edge> pathB) {
        HashSet<Edge> a = new HashSet<>(pathA);
        HashSet<Edge> b = new HashSet<>(pathB);
        a.addAll(b);
        return a;
    }
}
