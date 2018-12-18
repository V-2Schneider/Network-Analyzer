package pl.put.poznan.analyzer.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Metoda testująca metody klasy Graph
 */
class GraphTest {

    /**
     * Metoda testuje dodanie obiektu Node do grafu
     */
    @Test
    void testAdd(){
        Node test  = new Node("test");
        Graph g = new Graph();
        g.addNode(test);
        assertNotNull(g.getMapOfNodes());
    }

    /**
     * Metoda testuje dodanie połączenia do grafu
     */
    @Test
    void testConnection(){
        Node test1  = new Node("test1");
        Node test2  = new Node("test2");
        Graph g = new Graph();
        g.addNode(test1);
        g.addNode(test2);
        g.addConnection(1,2,5);
        assertNotNull(g.getMapOfConnections());
    }
}