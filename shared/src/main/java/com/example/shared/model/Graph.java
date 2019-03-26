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

    private class PathMatrix {
        class Entry {
            Integer dist;
            Set<Edge> path;

            Entry() {
                dist = null;
                path = new HashSet<>();
            }

            void setDist(Integer dist) {
                this.dist = dist;
            }
            void setPath(Set<Edge> path) {
                this.path = path;
            }
            Integer getDist() {
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
        void setDist(int i, int j, int k, Integer dist) {
            if (k == BASE) {
                base.get(i).get(j).setDist(dist);
            } else {
                matrix.get(k).get(i).get(j).setDist(dist);
            }
            if (dist != null && dist > largestDistance) {
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
        Integer getDist(int i, int j, int k) {
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
        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append("Initial\n");
            for (List<Entry> row : base) {
                for (Entry entry : row) {
                    str.append(String.format("%5d", entry.dist));
                }
                str.append("\n");
            }
            str.append("\n");
            for (List<List<Entry>> subMatrix : matrix) {
                for (List<Entry> row : subMatrix) {
                    for (Entry entry :row) {
                        str.append(String.format("%5d", entry.dist));
                    }
                    str.append("\n");
                }
                str.append("\n");
            }
            return str.toString();
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
//        LinkedHashMap<T, Integer> indexMap = new LinkedHashMap<>();
//        int index = 0;
//        for (T value : nodes.keySet()) {
//            indexMap.put(value, index);
//            index++;
//        }
//        indexMap.put(null, BASE);
//
//        PathMatrix matrix = new PathMatrix(nodes.size());
//        int pivot = indexMap.get(null);
//        for (int i = 0; i < nodes.size(); i++) {
//            matrix.setDist(i,i,pivot,0);
//        }
//        for (Edge edge : edges) {
//            int start = indexMap.get(edge.startNode);
//            int end = indexMap.get(edge.endNode);
//            matrix.setDist(start,end,pivot,edge.length);
//            matrix.setDist(end,start,pivot,edge.length);
//            Set<Edge> set = new HashSet<>();
//            set.add(edge);
//            matrix.setPath(start,end,pivot,set);
//            matrix.setPath(end,start,pivot,set);
//        }
//        for (T pivotNode : nodes.keySet()) {
//            for (T startNode : nodes.keySet()) {
//                for (T endNode : nodes.keySet()) {
//                    int start = indexMap.get(startNode);
//                    int end = indexMap.get(endNode);
//                    pivot = indexMap.get(pivotNode);
//                    Integer dist1 = matrix.getDist(start, pivot, pivot - 1);
//                    Integer dist2 = matrix.getDist(pivot, end, pivot - 1);
//                    Integer oldDist = matrix.getDist(start, end, pivot - 1);
//                    Integer oldDistTemp = (oldDist == null ? 0 : oldDist);
//                    Set<Edge> path1 = matrix.getPath(start, pivot, pivot - 1);
//                    Set<Edge> path2 = matrix.getPath(pivot, end, pivot - 1);
//                    Set<Edge> oldPath = matrix.getPath(start, end, pivot - 1);
//                    // if paths overlap, check combinations of previous dist1, dist2
//                    if (dist1 == null || dist2 == null || pathsOverlap(path1, path2) || dist1 + dist2 <= oldDistTemp) {
//                        matrix.setDist(start, end, pivot, oldDist);
//                        matrix.setDist(end, start, pivot, oldDist);
//                        matrix.setPath(start, end, pivot, oldPath);
//                        matrix.setPath(end, start, pivot, oldPath);
//                    } else {
//                        matrix.setDist(start, end, pivot, dist1 + dist2);
//                        matrix.setDist(end, start, pivot, dist1 + dist2);
//                        matrix.setPath(start, end, pivot, getUnion(path1, path2));
//                        matrix.setPath(end, start, pivot, getUnion(path1, path2));
//                    }
//                }
//            }
//        }
//        System.out.print(matrix.toString());
//        return matrix.getLargestDist();
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
