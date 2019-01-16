package pl.put.poznan.analyzer.logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * This class allows you to find the path between the entry node and the exit node.
 */

public class PathFinder {
    private static final Logger logger = LoggerFactory.getLogger(PathFinder.class);

    /**
     * The algorithm used to find the path.
     */
    private Algorithm algorithm;

    /**
     * Method that sets the algorithm used to find a path in the network.
     *
     * @param algorithm Object that implements the path finding algorithm.
     */
    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Find the most profitable path in the network.
     *
     * @param _entryNode (node that is the beginning of the path), _exitNode (node that is the end of the path), network as HashMap
     * @return The best path as Result (list of nodes and path's value)
     * <br> or NULL if path can't be found.
     */
    public PathResult findPath(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
        return algorithm.run(_entryNode, _exitNode, _mapOfNode);
    }


}
