package pl.put.poznan.analyzer.logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

public class NetworkAnalyzer {
    private static final Logger logger = LoggerFactory.getLogger(NetworkAnalyzer.class);
    /**
     * Instance of BFS class, which is used to find the best path in network using BFS algorithm
     */
    private final Algorithm bfs;
    /**
     * Instance of DFS class, which is used to find the best path in network using DFS algorithm
     */
    private final Algorithm dfs;
    /**
     * Instance of GreedyAlgorithm class, which is used to find the best path in network using greedy algorithm
     */
    private final Algorithm greedy;

    private final PathFinder pathFinder;

    @Autowired
    public NetworkAnalyzer(BFS bfs, DFS dfs, PathFinder pathFinder, GreedySearch greedy) {
        this.bfs = bfs;
        this.dfs = dfs;
        this.greedy = greedy;
        this.pathFinder = pathFinder;
    }

    /**
     * Find the most profitable path in the network by BFS algorithm
     *
     * @param
     * @return the best path as Result (list of nodes and path's value)
     * <br> or NULL if path can't be found
     */
    public PathResult findTheBestPathByBFS(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
        pathFinder.setAlgorithm(bfs);
        return pathFinder.findPath(_entryNode, _exitNode, _mapOfNode);
    }

    /**
     * Find the most profitable path in the network by DFS algorithm
     *
     * @param
     * @return the best path as Result (list of nodes and path's value)
     * <br> or NULL if path can't be found
     */
    public PathResult findTheBestPathByDFS(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
        pathFinder.setAlgorithm(dfs);
        return pathFinder.findPath(_entryNode, _exitNode, _mapOfNode);
    }

    /**
     * Find the most profitable path in the network by greedy algorithm
     *
     * @param _entryNode (node that is the beginning of the path), _exitNode (node that is the end of the path), network as HashMap
     * @return the best path as Result (list of nodes and path's value)
     * <br> or NULL if path can't be found
     */
    public PathResult findTheBestPathByGreedy(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
        pathFinder.setAlgorithm(greedy);
        return pathFinder.findPath(_entryNode, _exitNode, _mapOfNode);
    }

}
