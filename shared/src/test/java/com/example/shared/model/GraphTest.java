package com.example.shared.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

// TODO: Make sure duplicate nodes can't be added.

public class GraphTest {
    private Graph<Character> graph;

    @Before
    public void setup() {
        graph = new Graph<>();
        graph.addEdge('a', 'b', 1);
        graph.addEdge('b', 'c', 2);
        graph.addEdge('b', 'f', 5);
        graph.addEdge('c', 'd', 4);
        graph.addEdge('d', 'e', 6);
        graph.addEdge('e', 'f', 7);
        graph.addEdge('h', 'g', 3);
    }

    @Test
    public void hasPath_true() {
        // ab, ba, be, eb, cf, fd, gh
        assertTrue(graph.hasPath('a', 'b'));
        assertTrue(graph.hasPath('b', 'a'));
        assertTrue(graph.hasPath('b', 'e'));
        assertTrue(graph.hasPath('e', 'b'));
        assertTrue(graph.hasPath('c', 'f'));
        assertTrue(graph.hasPath('f', 'd'));
        assertTrue(graph.hasPath('g', 'h'));
    }

    @Test
    public void hasPath_noPath() {
        // ag, ga, dh, he
        assertFalse(graph.hasPath('a', 'g'));
        assertFalse(graph.hasPath('g', 'a'));
        assertFalse(graph.hasPath('d', 'h'));
        assertFalse(graph.hasPath('h', 'e'));
    }

    @Test
    public void hasPath_valueNotInGraph() {
        // am, zh, xy, yx
        assertFalse(graph.hasPath('a', 'm'));
        assertFalse(graph.hasPath('z', 'h'));
        assertFalse(graph.hasPath('x', 'y'));
        assertFalse(graph.hasPath('y', 'x'));
    }

    @Test
    public void hasPathOneEdge() {
        Graph<Character> oneEdgeGraph = new Graph<>();
        oneEdgeGraph.addEdge('a', 'b', 1);
        assertTrue(oneEdgeGraph.hasPath('a', 'b'));
        assertTrue(oneEdgeGraph.hasPath('b', 'a'));
        assertFalse(oneEdgeGraph.hasPath('a', 'c'));
        assertFalse(oneEdgeGraph.hasPath('d', 'r'));
    }

    @Test
    public void hasPathEmptyGraph() {
        Graph<Character> emptyGraph = new Graph<>();
        assertFalse(emptyGraph.hasPath('a', 'b'));
    }

    @Test
    public void hasPathNoEdges() {
        Graph<Character> noEdgeGraph = new Graph<>();
        noEdgeGraph.addNode('a');
        noEdgeGraph.addNode('b');
        noEdgeGraph.addNode('c');
        assertFalse(noEdgeGraph.hasPath('a', 'b'));
        assertFalse(noEdgeGraph.hasPath('c', 'b'));
    }

    @Test
    public void hasPathCityGraph() {
        Graph<City> cityGraph = new Graph<>();
        cityGraph.addEdge(new City("San Francisco"), new City("New York"), 15);
        cityGraph.addEdge(new City("New York"), new City("Chicago"), 7);
        cityGraph.addEdge(new City("Chicago"), new City("Miami"), 10);
        cityGraph.addEdge(new City("Santa Fe"), new City("Salt Lake City"), 5);
        assertTrue(cityGraph.hasPath(new City("San Francisco"), new City("Miami")));
        assertFalse(cityGraph.hasPath(new City("New York"), new City("Salt Lake City")));
        assertFalse(cityGraph.hasPath(new City("Santa Fe"), new City("Atlantis")));
    }

    @Test
    public void getLongestPathSimple() {
        Set<Set<Graph<Character>.Edge>> paths = graph.findPaths('a', 'f');
        assertEquals(2, paths.size());
//        assertEquals(25, graph.getLongestPath());
//        graph.addEdge('f', 'h', 1);
//        assertEquals(25, graph.getLongestPath());
//        graph.addEdge('f', 'g', 2);
//        assertEquals(26, graph.getLongestPath());
    }
}