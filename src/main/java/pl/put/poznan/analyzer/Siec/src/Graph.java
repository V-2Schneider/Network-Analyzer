package pl.put.poznan.analyzer.Siec.src;

import java.util.*;

class Graph {
    /**
     * liczba krawedzi
     */
    private int e;
    /**
     * liczba wierzcholkow
     */
    private int v;
    /**
     * maksymalny koszt
     */
    private int value;
    /**
     * tablica list sasiedztwa danego wierzcholka
     */
    private List<Integer>[] adjacencyList;

    @SuppressWarnings("unchecked")
    public Graph(int v, int value) {
        this.v = v;
        this.value=value;
        this.e = 0;
        adjacencyList = (List<Integer>[]) new List[v*value];
        for (int i = 0; i < v*value; i++) {
            adjacencyList[i] = new ArrayList<Integer>();
        }
    }

    public void setNumberOfVertices(int v){ this.v =v;}

    public void addEdge(int v, int w) {
        adjacencyList[v].add(w);
        e++;
    }

    public int getNumberOfEdges() {
        return e;
    }

    public int getNumberOfVertices() {
        return v*value;
    }

    public Iterable<Integer> getAdjacencyList(int v) {
        return adjacencyList[v];
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        s.append("wierzcholki: ").append(v).append("; krawedzie: ").append(e)
                .append(newLine);
        for (int i = 0; i < v; i++) {
            s.append(i).append(": ");
            for (int w : adjacencyList[i]) {
                s.append(w).append(" ");
            }
            s.append(newLine);
        }
        return s.toString();
    }

}
