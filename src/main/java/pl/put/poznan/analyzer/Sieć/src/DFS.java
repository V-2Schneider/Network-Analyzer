package pl.put.poznan.analyzer.Sieć.src;

import java.util.*;

public class DFS {
    // tablica krawedzi ktora jest
    // przechowuje wierzcholki z ktorych mozna sie dostac do biezacego
    // okreslonego indeksem tablicy
    private int[] edgeTo;
    // tablica odwiedzonych wierzcholkow
    private boolean[] marked;
    // wierzcholek zrodlowy, z ktorego rozpoczynamy przeszukiwanie
    private final int source;

    public DFS(Graph graph, int source) {
        this.source = source;
        edgeTo = new int[graph.getNumberOfVertices()];
        marked = new boolean[graph.getNumberOfVertices()];
        dfs(graph, source);
    }
    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    public Iterable<Integer> getPathTo(int vertex) {
        Deque<Integer> path = new ArrayDeque<Integer>();
        if (!hasPathTo(vertex)) {
            return path;
        }
        for (int w = vertex; w != source; w = edgeTo[w]) {
            path.push(w);
        }
        path.push(source);
        return path;
    }

    private void dfs(Graph graph, int vertex) {
        marked[vertex] = true;
        for (int w : graph.getAdjacencyList(vertex)) {
            if (!marked[w]) {
                edgeTo[w] = vertex;
                dfs(graph, w);
            }
        }
    }
}