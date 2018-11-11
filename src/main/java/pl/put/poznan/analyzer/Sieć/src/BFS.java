import java.util.*;

class BFS {
    // tablica krawedzi ktora jest
    // przechowuje wierzcholki z ktorych mozna sie dostac do biezacego
    // okreslonego indeksem tablicy
    private int[] edgeTo;
    // tablica odwiedzonych wierzcholkow
    private boolean[] marked;
    // wierzcholek zrodlowy, z ktorego rozpoczynamy przeszukiwanie
    private final int source;
    private Queue<Integer> priorityQueue;

    public BFS(Graph graph, int source) {
        this.source = source;
        edgeTo = new int[graph.getNumberOfVertices()];
        marked = new boolean[graph.getNumberOfVertices()];
        priorityQueue = new PriorityQueue<Integer>(graph.getNumberOfVertices());
        priorityQueue.offer(source);
        bfs(graph, source);
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

    private void bfs(Graph graph, int vertex) {

        marked[vertex] = true;

        priorityQueue.offer(vertex);

        while (!priorityQueue.isEmpty()) {
            int v = priorityQueue.remove();
            for (int w : graph.getAdjacencyList(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    priorityQueue.offer(w);
                }
            }
        }
    }
}
