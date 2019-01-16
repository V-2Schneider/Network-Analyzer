package pl.put.poznan.analyzer.logic;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

public class BFS implements Algorithm{

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
     * This method is used to find the most profitable path from entry to exit using BFS algorithm.
     *
     * @param _entryNode (node that is the beginning of the path), _exitNode (node that is the end of the path), network as HashMap
     * @return shortest path (list of nodes) or null if path can't be found
     */
    @Override
    public PathResult run(int _entryNode, int _exitNode, HashMap<Integer, Node> _mapOfNode) {
        log.info("Inicjalizacja BFS");

        sRes = new ArrayList<>();
        nRes = new ArrayList<>();
        path = new LinkedList<>();
        edgeTo = new int[_mapOfNode.size()];
        notVisited = new ArrayList<>();
        sumValue = 0;


        for (int i = 0; i < _mapOfNode.size(); i++)
            notVisited.add(false);
        log.info("Rozpoczęcie przeszukiwania");

        if (!MakeBFS(_entryNode, _exitNode, _mapOfNode))
            log.info("Nie odnaleziono połączenia, zwrócono pustą liste");
        else {
            Collections.reverse(nRes);
            log.info("Przeszukiwanie zakończone powodzeniem");

            for (Connection res : sRes) {
                sumValue += res.getValue();
            }
            //System.out.print(sumValue+"    ");
        }

        //return nRes;
        return new PathResult(sumValue, nRes);

    }

/*
    /**
     * This method is used to find the most profitable path from entry to exit using BFS algorithm.
     *
     * @param _entryNode (node that is the beginning of the path), _exitNode (node that is the end of the path), network as HashMap
     * @return shortest path (list of nodes) or null if path can't be found
     */
 /*   public static PathResult BFS(int _entryNode, int _exitNode, HashMap<Integer, Node> _mapOfNode) {
        log.info("Inicjalizacja BFS");

        sRes = new ArrayList<>();
        nRes = new ArrayList<>();
        path = new LinkedList<>();
        edgeTo = new int[_mapOfNode.size()];
        notVisited = new ArrayList<>();
        sumValue = 0;


        for (int i = 0; i < _mapOfNode.size(); i++)
            notVisited.add(false);
        log.info("Rozpoczęcie przeszukiwania");

        if (!MakeBFS(_entryNode, _exitNode, _mapOfNode))
            log.info("Nie odnaleziono połączenia, zwrócono pustą liste");
        else {
            Collections.reverse(nRes);
            log.info("Przeszukiwanie zakończone powodzeniem");

            for (Connection res : sRes) {
                sumValue += res.getValue();
            }
            //System.out.print(sumValue+"    ");
        }

        //return nRes;
        return new PathResult(sumValue, nRes);
    }*/

    /**
     * Find the path by using BFS algorithm (with queue)
     *
     * @param _entryNode (node that is the beginning of the path), _exitNode (node that is the end of the path), network as HashMap
     * @return true when the path exists or false if path can't be found
     */
    private static boolean MakeBFS(int _entryNode, int _exitNode, HashMap<Integer, Node> _mapOfNode) {
        boolean zm = false;
        notVisited.set(_entryNode - 1, true);
        path.offer(_entryNode);
        while (!path.isEmpty()) {
            int v = path.remove();
            //nRes.add(_mapOfNode.get(_mapOfNode.get(v).getId()));
            if (_mapOfNode.get(v).getId() == _exitNode) {
                zm = true;
                //return true;
            }
            for (int i = 0; i < _mapOfNode.get(v).getOutgoing().size(); i++) {
                if (!notVisited.get(_mapOfNode.get(v).getOutgoing().get(i).getTo().getId() - 1)) {
                    edgeTo[_mapOfNode.get(v).getOutgoing().get(i).getTo().getId() - 1] = _mapOfNode.get(v).getId() - 1;
                    notVisited.set(_mapOfNode.get(v).getOutgoing().get(i).getTo().getId() - 1, true);
                    path.offer(_mapOfNode.get(v).getOutgoing().get(i).getTo().getId());
                }

            }
        }
        if (zm) {
            int id = 0;
            for (int w = _mapOfNode.get(_exitNode).getId() - 1; w != _mapOfNode.get(_entryNode).getId() - 1; w = edgeTo[w]) {
                nRes.add(_mapOfNode.get(_mapOfNode.get(w + 1).getId()));

                if (id != 0) {
                    for (int connectionNumber = 0; connectionNumber < _mapOfNode.get(_mapOfNode.get(w + 1).getId()).getOutgoing().size(); connectionNumber++) {
                        if (_mapOfNode.get(_mapOfNode.get(w + 1).getId()).getOutgoing().get(connectionNumber).getTo().getId() == id) {
                            sRes.add(_mapOfNode.get(_mapOfNode.get(w + 1).getId()).getOutgoing().get(connectionNumber));
                        }
                    }
                }
                id = _mapOfNode.get(w + 1).getId();
            }
            nRes.add(_mapOfNode.get(_mapOfNode.get(_entryNode).getId()));
            for (int connectionNumber = 0; connectionNumber < _mapOfNode.get(_mapOfNode.get(_entryNode).getId()).getOutgoing().size(); connectionNumber++) {
                if (_mapOfNode.get(_mapOfNode.get(_entryNode).getId()).getOutgoing().get(connectionNumber).getTo().getId() == id) {
                    sRes.add(_mapOfNode.get(_mapOfNode.get(_entryNode).getId()).getOutgoing().get(connectionNumber));
                }
            }

            return true;
        }
        return false;
    }

}
