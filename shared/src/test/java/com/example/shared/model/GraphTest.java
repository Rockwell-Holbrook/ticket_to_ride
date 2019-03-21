package com.example.shared.model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphTest {
    private Graph<Character> graph;

    @Before
    public void setup() {
        graph = new Graph<>();
        graph.addEdge('a', 'b');
        graph.addEdge('b', 'c');
        graph.addEdge('b', 'f');
        graph.addEdge('c', 'd');
        graph.addEdge('d', 'e');
        graph.addEdge('e', 'f');
        graph.addEdge('h', 'g');
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
        oneEdgeGraph.addEdge('a', 'b');
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
        cityGraph.addEdge(new City("San Francisco"), new City("New York"));
        cityGraph.addEdge(new City("New York"), new City("Chicago"));
        cityGraph.addEdge(new City("Chicago"), new City("Miami"));
        cityGraph.addEdge(new City("Santa Fe"), new City("Salt Lake City"));
        assertTrue(cityGraph.hasPath(new City("San Francisco"), new City("Miami")));
        assertFalse(cityGraph.hasPath(new City("New York"), new City("Salt Lake City")));
        assertFalse(cityGraph.hasPath(new City("Santa Fe"), new City("Atlantis")));
    }

    // TODO: Make sure duplicate nodes can't be added.
}