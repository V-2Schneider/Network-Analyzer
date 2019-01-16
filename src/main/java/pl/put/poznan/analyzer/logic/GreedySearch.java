package pl.put.poznan.analyzer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Queue;

public class GreedySearch implements Algorithm{
    static Logger log = LoggerFactory.getLogger(Search.class);

    /**
     * list of connections between nodes
     */
    private static ArrayList<Connection> sRes;
    /**
     * list of nodes used as a result
     */
    private static ArrayList<Node> nRes;
    /**
     * List of visited nodes in greedy search
     */
    private static ArrayList<Boolean> notVisited;
    /**
     * queue used in BFS
     */
    private static Queue<Integer> path;
    /**
     *  array containing a parents of Nodes (in BFS)
     */
    private static int[] edgeTo;
    /**
     * path's value
     */
    private static int sumValue;

    /**
     * This method is used to find the most profitable path from entry to exit using greedy algorithm.
     * @param _entryNode (node that is the beginning of the path), _exitNode (node that is the end of the path), network as HashMap
     * @return shortest path (list of nodes) or null if path can't be found, and -1 on value
     * */
    @Override
    public PathResult run(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
        log.info("Inicjalizacja Greedy Searcha");

        sRes = new ArrayList<Connection>();
        notVisited =  new ArrayList<Boolean>();
        sumValue = 0;

        for(int i = 0; i < _mapOfNode.size();i++)
            notVisited.add(false);
        log.info("Rozpoczęcie przeszukiwania");
        PathResult pathResult;
        if(!MakeGreedySearch(_entryNode,_exitNode,_mapOfNode)) {
            log.info("Nie odnaleziono połączenia, zwrócono -1 w wartości");
            pathResult = new PathResult(-1, new ArrayList<Node>());
        }else {
            Collections.reverse(sRes);
            log.info("Przeszukiwanie zakończone powodzeniem");
            ArrayList<Node> nody = new ArrayList<>();
            nody.add(sRes.get(0).getFrom());
            for ( Connection res : sRes) {
                sumValue += res.getValue();
                nody.add(res.getTo());
            }
            System.out.print(sumValue+"    ");

            pathResult = new PathResult(sumValue,nody);
        }

        return pathResult;
    }


    /*public static PathResult GreedySeach(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
        log.info("Inicjalizacja Greedy Searcha");

        sRes = new ArrayList<Connection>();
        notVisited =  new ArrayList<Boolean>();
        sumValue = 0;

        for(int i = 0; i < _mapOfNode.size();i++)
            notVisited.add(false);
        log.info("Rozpoczęcie przeszukiwania");
        PathResult pathResult;
        if(!MakeGreedySearch(_entryNode,_exitNode,_mapOfNode)) {
            log.info("Nie odnaleziono połączenia, zwrócono -1 w wartości");
            pathResult = new PathResult(-1, new ArrayList<Node>());
        }else {
            Collections.reverse(sRes);
            log.info("Przeszukiwanie zakończone powodzeniem");
            ArrayList<Node> nody = new ArrayList<>();
            nody.add(sRes.get(0).getFrom());
            for ( Connection res : sRes) {
                sumValue += res.getValue();
                nody.add(res.getTo());
            }
            System.out.print(sumValue+"    ");

            pathResult = new PathResult(sumValue,nody);
        }

        return pathResult;
    }*/

    /**
     * Find the path by using recursive function for GreedySearch
     * @param _entryNode (node that is the beginning of the path), _exitNode (node that is the end of the path), network as HashMap
     * @return true when the path exists or false if path can't be found
     */

    private static boolean MakeGreedySearch(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode){
        notVisited.set(_entryNode-1,true);
        int nextNode;
        do {
            int minCost = 6666666;
            nextNode = _entryNode;
            int connectionNumber = -1;
            for (int i = 0; i < _mapOfNode.get(_entryNode).getOutgoing().size(); i++) {
                if (_mapOfNode.get(_entryNode).getOutgoing().get(i).getTo().getId() == _exitNode) {
                    sRes.add(_mapOfNode.get(_entryNode).getOutgoing().get(i));
                    return true;
                } else if (_mapOfNode.get(_entryNode).getOutgoing().get(i).getValue() < minCost && !notVisited.get(_mapOfNode.get(_entryNode).getOutgoing().get(i).getTo().getId()-1)) {
                    minCost = _mapOfNode.get(_entryNode).getOutgoing().get(i).getValue();
                    nextNode = _mapOfNode.get(_entryNode).getOutgoing().get(i).getTo().getId();
                    connectionNumber = i;
                }
            }
            if(nextNode != _entryNode) {
                if (MakeGreedySearch(nextNode, _exitNode, _mapOfNode)) {
                    sRes.add(_mapOfNode.get(_entryNode).getOutgoing().get(connectionNumber));
                    return true;
                }
            }
        } while (nextNode != _entryNode);
        return false;
    }
}
