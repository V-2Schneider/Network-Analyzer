package pl.put.poznan.analyzer.logic;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

public class DFS implements Algorithm {

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
     * This method is used to find the most profitable path from entry to exit using DFS algorithm.
     * @param _entryNode (node that is the beginning of the path), _exitNode (node that is the end of the path), network as HashMap
     * @return shortest path (list of nodes) or null if path can't be found
     */
    @Override
    public PathResult run(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
        log.info("Inicjalizacja DFS");
        sRes = new ArrayList();
        nRes = new ArrayList();
        notVisited =  new ArrayList<>();
        sumValue = 0;

        for(int i = 0; i < _mapOfNode.size();i++)
            notVisited.add(false);
        log.info("Rozpoczęcie przeszukiwania");

        if(!MakeDFS(_entryNode,_exitNode,_mapOfNode))
            log.info("Nie odnaleziono połączenia, zwrócono pustą liste");
        else {
            Collections.reverse(nRes);
            log.info("Przeszukiwanie zakończone powodzeniem");

            for ( Connection res : sRes) {
                sumValue += res.getValue();
            }
            //System.out.print(sumValue+"    ");
        }

        //return nnRes;
        return new PathResult(sumValue, nRes);
    }


   /* /**
     * This method is used to find the most profitable path from entry to exit using DFS algorithm.
     * @param _entryNode (node that is the beginning of the path), _exitNode (node that is the end of the path), network as HashMap
     * @return shortest path (list of nodes) or null if path can't be found
     */
  /*  public static PathResult DFS(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
        log.info("Inicjalizacja DFS");

        sRes = new ArrayList();
        nRes = new ArrayList();
        notVisited =  new ArrayList<>();
        sumValue = 0;

        for(int i = 0; i < _mapOfNode.size();i++)
            notVisited.add(false);
        log.info("Rozpoczęcie przeszukiwania");

        if(!MakeDFS(_entryNode,_exitNode,_mapOfNode))
            log.info("Nie odnaleziono połączenia, zwrócono pustą liste");
        else {
            Collections.reverse(nRes);
            log.info("Przeszukiwanie zakończone powodzeniem");

            for ( Connection res : sRes) {
                sumValue += res.getValue();
            }
            //System.out.print(sumValue+"    ");
        }

        //return nnRes;
        return new PathResult(sumValue, nRes);
    }*/

    /**
     * Find the path by using recursive function for DFS
     * @param _entryNode (node that is the beginning of the path), _exitNode (node that is the end of the path), network as HashMap
     * @return true when the path exists or false if path can't be found
     */


    private static boolean MakeDFS(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode){
        notVisited.set(_entryNode-1,true);
        int nextNode;
        do {
            nextNode = _entryNode;
            int connectionNumber = -1;
            for (int i = 0; i < _mapOfNode.get(_entryNode).getOutgoing().size(); i++) {
                if (_mapOfNode.get(_entryNode).getOutgoing().get(i).getTo().getId() == _exitNode) {
                    nRes.add(_mapOfNode.get(_mapOfNode.get(_entryNode).getOutgoing().get(i).getTo().getId()));
                    nRes.add(_mapOfNode.get(_mapOfNode.get(_entryNode).getId()));
                    sRes.add(_mapOfNode.get(_entryNode).getOutgoing().get(i));
                    return true;
                } else if (!notVisited.get(_mapOfNode.get(_entryNode).getOutgoing().get(i).getTo().getId()-1)) {
                    nextNode = _mapOfNode.get(_entryNode).getOutgoing().get(i).getTo().getId();
                    connectionNumber = i;
                }
                if(nextNode != _entryNode) {
                    if (MakeDFS(nextNode, _exitNode, _mapOfNode)) {
                        //nRes.add(_mapOfNode.get(_mapOfNode.get(_entryNode).getOutgoing().get(connectionNumber).getTo().getId()));
                        nRes.add(_mapOfNode.get(_mapOfNode.get(_entryNode).getId()));
                        sRes.add(_mapOfNode.get(_entryNode).getOutgoing().get(connectionNumber));
                        return true;
                    }
                }
            }
        } while (nextNode != _entryNode);
        return false;
    }

}
