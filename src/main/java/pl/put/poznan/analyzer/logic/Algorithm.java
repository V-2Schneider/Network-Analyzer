package pl.put.poznan.analyzer.logic;

import java.util.HashMap;
import java.util.List;

public interface Algorithm {

    /**
 * Find the most profitable path in the network.
 *
 * @param _entryNode (node that is the beginning of the path), _exitNode (node that is the end of the path), network as HashMap
 * @return Path as Result (list of nodes and path's value).
 */
    PathResult run(int _entryNode, int _exitNode, HashMap<Integer, Node> _mapOfNode);

}
