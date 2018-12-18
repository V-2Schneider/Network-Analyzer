package pl.put.poznan.analyzer.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {


    List<Integer> gotowa = new ArrayList<>();
    void przygotujGraf(Graph g){
        //Graf testowy: init
        Node n1  = new Node("n1");
        Node n2  = new Node("n2");
        Node n3  = new Node("n3");
        Node n4  = new Node("n4");
        Node n5  = new Node("n5");
        Node n6  = new Node("n6");
        //Dodaje wierzchołki
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        g.addNode(n5);
        g.addNode(n6);
        //Dodaje połączenia
        g.addConnection(1,2,1);
        g.addConnection(1,4,8);
        g.addConnection(1,6,40);
        g.addConnection(2,3,1);
        g.addConnection(3,4,4);
        g.addConnection(4,5,2);
        g.addConnection(5,6,1);
        //Koniec grafu testowego
        //Lista do porównania indeksów wierzchołków w znalezionej ścieżce
        gotowa.add(1);
        gotowa.add(2);
        gotowa.add(3);
        gotowa.add(4);
        gotowa.add(5);
        gotowa.add(6);
        //Koniec listy
    }

    /**
     * Test algorytmu zachłannego
     */
    @Test
    void testgreedySeach() {
        Graph g = new Graph();
        przygotujGraf(g);

        //Wywołanie metody i przypisanie rezultatów
        PathResult alg = Search.GreedySeach(1,6,g.getMapOfNodes());
        //Test kosztu ścieżki
        assertEquals(alg.getValue(),40);
        //Test ilości wierzchołków ścieżki
        assertEquals(alg.getNodes().size(),2);
        //Przepisanie indeksów wierzchołków ze ścieżki do nowej tablicy integer
        List<Integer> wynik = new ArrayList();
        for (int i = 0; i < alg.getNodes().size(); i++) {
            wynik.add(alg.getNodes().get(i).getId());
        }
        //Metoda testująca
        assertEquals(wynik,gotowa);

    }

    /**
     * Test algorytmu BFS
     */
    @Test
    void testBFS() {
        Graph g = new Graph();
        przygotujGraf(g);

        //Wywołanie metody i przypisanie rezultatów
        PathResult alg = Search.BFS(1,6,g.getMapOfNodes());
        //Test kosztu ścieżki
        assertEquals(alg.getValue(),40);
        //Test ilości wierzchołków ścieżki
        assertEquals(alg.getNodes().size(),2);
        //Przepisanie indeksów wierzchołków ze ścieżki do nowej tablicy integer
        List<Integer> wynik = new ArrayList();
        for (int i = 0; i < alg.getNodes().size(); i++) {
            wynik.add(alg.getNodes().get(i).getId());
        }
        //Metoda testująca
        assertEquals(wynik,gotowa);
    }

    /**
     * Test algorytmu DFS
     */
    @Test
    void testDFS() {
        Graph g = new Graph();
        przygotujGraf(g);

        //Wywołanie metody i przypisanie rezultatów
        PathResult alg = Search.DFS(1,6,g.getMapOfNodes());
        //Test kosztu ścieżki
        assertEquals(alg.getValue(),9);
        //Test ilości wierzchołków ścieżki
        assertEquals(alg.getNodes().size(),6);
        //Przepisanie indeksów wierzchołków ze ścieżki do nowej tablicy integer
        List<Integer> wynik = new ArrayList();
        for (int i = 0; i < alg.getNodes().size(); i++) {
            wynik.add(alg.getNodes().get(i).getId());
        }
        //Metoda testująca
        assertEquals(wynik,gotowa);
    }
}