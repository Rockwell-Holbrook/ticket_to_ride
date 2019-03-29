package com.example.shared.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        assertEquals(25, graph.getLongestPath());
        Graph<Character> otherGraph = new Graph<>();
        otherGraph.addEdge('a', 'c', 1);
        otherGraph.addEdge('c', 'b', 1);
        otherGraph.addEdge('b', 'e', 1);
        otherGraph.addEdge('b', 'd', 1);
        otherGraph.addEdge('d', 'e', 1);
        otherGraph.addEdge('e', 'f', 1);
        otherGraph.addEdge('f', 'c', 1);
        assertEquals(6,  otherGraph.getLongestPath());
    }

    @Test
    public void getLongestPathSimple2() {
        Graph<Character> otherGraph = new Graph<>();
        otherGraph.addEdge('a', 'c', 1);
        otherGraph.addEdge('c', 'b', 1);
        otherGraph.addEdge('b', 'e', 2);
        otherGraph.addEdge('b', 'd', 1);
        otherGraph.addEdge('d', 'e', 1);
        otherGraph.addEdge('e', 'f', 1);
        otherGraph.addEdge('f', 'c', 1);
        assertEquals(7,  otherGraph.getLongestPath());
    }

    @Test
    public void getLongestPathDeadEnd() {
        Graph<Character> deadEnd = new Graph<>();
        deadEnd.addEdge('a', 'b', 1);
        deadEnd.addEdge('b', 'c', 1);
        deadEnd.addEdge('c', 'd', 1);
        deadEnd.addEdge('c', 'e', 1);
        deadEnd.addEdge('e', 'f', 1);
        deadEnd.addEdge('f', 'g', 1);
        assertEquals(5, deadEnd.getLongestPath());
    }

    @Test
    public void getLongestPathLongDeadEnd() {
        Graph<Character> deadEnd = new Graph<>();
        deadEnd.addEdge('a', 'b', 1);
        deadEnd.addEdge('b', 'c', 1);
        deadEnd.addEdge('c', 'd', 4);
        deadEnd.addEdge('c', 'e', 1);
        deadEnd.addEdge('e', 'f', 1);
        deadEnd.addEdge('f', 'g', 1);
        assertEquals(7, deadEnd.getLongestPath());
    }

    @Test
    public void getLongestPathCities() {
        Graph<City> black = new Graph<>();
        black.addEdge(new City("Vancouver"), new City("Calgary"), 3);
        black.addEdge(new City("Vancouver"), new City("Seattle"), 1);
        black.addEdge(new City("Seattle"), new City("Portland"), 1);
        black.addEdge(new City("Portland"), new City("San Francisco"), 5);
        black.addEdge(new City("San Francisco"), new City("Los Angeles"), 3);
        black.addEdge(new City("Los Angeles"), new City("Las Vegas"), 2);
        black.addEdge(new City("San Francisco"), new City("Salt Lake City"), 5);
        black.addEdge(new City("Salt Lake City"), new City("Denver"), 3);
        black.addEdge(new City("Denver"), new City("Kansas City"), 4);
        black.addEdge(new City("Santa Fe"), new City("Oklahoma City"), 3);
        black.addEdge(new City("Houston"), new City("Dallas"), 1);
        black.addEdge(new City("Dallas"), new City("Oklahoma City"), 2);
        black.addEdge(new City("Oklahoma City"), new City("Kansas City"), 2);
        black.addEdge(new City("Kansas City"), new City("Omaha"), 1);
        black.addEdge(new City("Omaha"), new City("Duluth"), 2);
        black.addEdge(new City("Duluth"), new City("Sault St Marie"), 3);
        black.addEdge(new City("Montreal"), new City("Boston"), 2);
        assertEquals(28, black.getLongestPath());

        Graph<City> red = new Graph<>();
        red.addEdge(new City("Vancouver"), new City("Seattle"), 1);
        red.addEdge(new City("Seattle"), new City("Helena"), 6);
        red.addEdge(new City("Helena"), new City("Salt Lake City"), 3);
        red.addEdge(new City("Helena"), new City("Denver"), 4);
        red.addEdge(new City("Denver"), new City("Oklahoma City"), 4);
        red.addEdge(new City("Saint Saul Marie"), new City("Toronto"), 2);
        red.addEdge(new City("Saint Saul Marie"), new City("Ehostter"), 5);
        red.addEdge(new City("Ehostter"), new City("New York"), 3);
        red.addEdge(new City("New York"), new City("Washington"), 2);
        red.addEdge(new City("Washington"), new City("Raleigh"), 2);
        red.addEdge(new City("Raleigh"), new City("Charleston"), 2);
        red.addEdge(new City("Charleston"), new City("Atlanta"), 2);
        red.addEdge(new City("Charleston"), new City("Miami"), 4);
        assertEquals(20, red.getLongestPath());

        Graph<City> yellow = new Graph<>();
        yellow.addEdge(new City("Los Angeles"), new City("El Paso"), 6);
        yellow.addEdge(new City("Los Angeles"), new City("Phoenix"), 3);
        yellow.addEdge(new City("Phoenix"), new City("El Paso"), 3);
        yellow.addEdge(new City("Phoenix"), new City("Santa Fe"), 3);
        yellow.addEdge(new City("El Paso"), new City("Dallas"), 4);
        yellow.addEdge(new City("Dallas"), new City("Oklahoma City"), 2);
        yellow.addEdge(new City("Oklahoma City"), new City("Kansas City"), 2);
        yellow.addEdge(new City("Kansas City"), new City("Omaha"), 1);
        yellow.addEdge(new City("Omaha"), new City("Duluth"), 2);
        yellow.addEdge(new City("Kansas City"), new City("St. Louis"), 2);
        yellow.addEdge(new City("St. Louis"), new City("Nashville"),2);
        yellow.addEdge(new City("Nashville"), new City("Atlanta"), 1);
        yellow.addEdge(new City("Nashville"), new City("Pittsburg"), 4);
        yellow.addEdge(new City("Pittsburg"), new City("Raleigh"), 2);
        yellow.addEdge(new City("Pittsburg"), new City("Toronto"), 2);
        yellow.addEdge(new City("Toronto"), new City("Ehostter"), 3);
        yellow.addEdge(new City("Ehostter"), new City("Boston"), 2);
        assertEquals(35, yellow.getLongestPath());
    }

    @Test
    public void getLongestPathOneEdge() {
        Graph<Character> oneEdgeGraph = new Graph<>();
        oneEdgeGraph.addEdge('a', 'b', 3);
        assertEquals(3, oneEdgeGraph.getLongestPath());
    }

    @Test
    public void getLongestPathEmptyGraph() {
        Graph<Character> emptyGraph = new Graph<>();
        assertEquals(0, emptyGraph.getLongestPath());
    }

    @Test
    public void getLongestPathNoEdges() {
        Graph<Character> noEdgeGraph = new Graph<>();
        noEdgeGraph.addNode('a');
        noEdgeGraph.addNode('b');
        noEdgeGraph.addNode('c');
        assertEquals(0, noEdgeGraph.getLongestPath());
    }

    @Test
    public void getLongestPathPureCycle() {
        Graph<Character> cycleGraph = new Graph<>();
        cycleGraph.addEdge('a', 'b', 1);
        cycleGraph.addEdge('b', 'c', 1);
        cycleGraph.addEdge('c', 'd', 2);
        cycleGraph.addEdge('d', 'e', 1);
        cycleGraph.addEdge('e', 'f', 3);
        cycleGraph.addEdge('f', 'a', 1);
        assertEquals(9, cycleGraph.getLongestPath());
    }

    @Test
    public void getLongestPathDisjointGraph() {
        Graph<Character> disjoint = new Graph<>();
        disjoint.addEdge('a', 'b', 3);
        disjoint.addEdge('c', 'd', 2);
        disjoint.addEdge('e', 'f', 7);
        assertEquals(7, disjoint.getLongestPath());
    }

    @Test
    public void getLongestPathBigDisjointGraph() {
        Graph<Character> bigDisjoint = new Graph<>();
        bigDisjoint.addEdge('a', 'b', 1);
        bigDisjoint.addEdge('b', 'c', 1);
        bigDisjoint.addEdge('b', 'f', 1);
        bigDisjoint.addEdge('c', 'd', 1);
        bigDisjoint.addEdge('d', 'e', 1);
        bigDisjoint.addEdge('e', 'f', 1);
        bigDisjoint.addEdge('h', 'g', 1);
        bigDisjoint.addEdge('z', 'x', 1);
        bigDisjoint.addEdge('x', 'y', 1);
        bigDisjoint.addEdge('y', 'v', 1);
        bigDisjoint.addEdge('y', 'w', 1);
        bigDisjoint.addEdge('w', 'v', 1);
        bigDisjoint.addEdge('v', 'u', 1);
        bigDisjoint.addEdge('u', 'x', 1);
        assertEquals(6, bigDisjoint.getLongestPath());
    }

    @Test
    public void getLongestPathBigDisjoint2() {
        Graph<Character> bigDisjoint = new Graph<>();
        bigDisjoint.addEdge('a', 'b', 1);
        bigDisjoint.addEdge('b', 'c', 1);
        bigDisjoint.addEdge('b', 'f', 1);
        bigDisjoint.addEdge('c', 'd', 1);
        bigDisjoint.addEdge('d', 'e', 1);
        bigDisjoint.addEdge('e', 'f', 2);
        bigDisjoint.addEdge('h', 'g', 1);
        bigDisjoint.addEdge('z', 'x', 1);
        bigDisjoint.addEdge('x', 'y', 1);
        bigDisjoint.addEdge('y', 'v', 1);
        bigDisjoint.addEdge('y', 'w', 1);
        bigDisjoint.addEdge('w', 'v', 1);
        bigDisjoint.addEdge('v', 'u', 1);
        bigDisjoint.addEdge('u', 'x', 1);
        assertEquals(7, bigDisjoint.getLongestPath());
    }

    @Test
    public void getLongestPathBigDisjoint3() {
        Graph<Character> bigDisjoint = new Graph<>();
        bigDisjoint.addEdge('a', 'b', 1);
        bigDisjoint.addEdge('b', 'c', 1);
        bigDisjoint.addEdge('b', 'f', 1);
        bigDisjoint.addEdge('c', 'd', 1);
        bigDisjoint.addEdge('d', 'e', 1);
        bigDisjoint.addEdge('e', 'f', 1);
        bigDisjoint.addEdge('h', 'g', 1);
        bigDisjoint.addEdge('z', 'x', 1);
        bigDisjoint.addEdge('x', 'y', 1);
        bigDisjoint.addEdge('y', 'v', 1);
        bigDisjoint.addEdge('y', 'w', 1);
        bigDisjoint.addEdge('w', 'v', 1);
        bigDisjoint.addEdge('v', 'u', 1);
        bigDisjoint.addEdge('u', 'x', 3);
        assertEquals(8, bigDisjoint.getLongestPath());
    }
}