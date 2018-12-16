package pl.put.poznan.analyzer.Sieć.src;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

public class Search {

    // logger
    static Logger log = LoggerFactory.getLogger(Search.class);

    //zmienne globalne



    private static List<Connection> sRes;
    private static List<Node> nRes;
    private static List<Boolean> notVisited;
    private static Queue<Integer> path;
    private static int[] edgeTo;
    private static int sumValue;

    // ================ Greedy Search


    public static List<Connection> GreedySeach(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
        log.info("Inicjalizacja Greedy Searcha");

        sRes = new ArrayList();
        notVisited =  new ArrayList<>();

        for(int i = 0; i < _mapOfNode.size();i++)
            notVisited.add(false);
        log.info("Rozpoczęcie przeszukiwania");

        if(!MakeGreedySearch(_entryNode,_exitNode,_mapOfNode))
            log.info("Nie odnaleziono połączenia, zwrócono pustą liste");
        else {
            Collections.reverse(sRes);
            log.info("Przeszukiwanie zakończone powodzeniem");
        }

        return sRes;
    }

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


    // ================== BFS
    public static List<Node> BFS(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
        log.info("Inicjalizacja BFS");

        sRes = new ArrayList();
        nRes = new ArrayList();
        path = new LinkedList<>();
        edgeTo = new int[_mapOfNode.size()];
        notVisited =  new ArrayList<>();
        sumValue = 0;


        for(int i = 0; i < _mapOfNode.size();i++)
            notVisited.add(false);
        log.info("Rozpoczęcie przeszukiwania");

        if(!MakeBFS(_entryNode,_exitNode,_mapOfNode))
            log.info("Nie odnaleziono połączenia, zwrócono pustą liste");
        else {
            Collections.reverse(nRes);
            log.info("Przeszukiwanie zakończone powodzeniem");
        }

        return nRes;
    }

    private static boolean MakeBFS(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode){
        boolean zm = false;
        notVisited.set(_entryNode-1,true);
        path.offer(_entryNode);
        while(!path.isEmpty()){
            int v = path.remove();
            //nRes.add(_mapOfNode.get(_mapOfNode.get(v).getId()));
            if (_mapOfNode.get(v).getId() == _exitNode) {
                zm=true;
                //return true;
            }
            for (int i = 0; i < _mapOfNode.get(v).getOutgoing().size(); i++){
                if (!notVisited.get(_mapOfNode.get(v).getOutgoing().get(i).getTo().getId()-1)) {
                    edgeTo[_mapOfNode.get(v).getOutgoing().get(i).getTo().getId()-1]=_mapOfNode.get(v).getId()-1;
                    notVisited.set(_mapOfNode.get(v).getOutgoing().get(i).getTo().getId()-1,true);
                    path.offer(_mapOfNode.get(v).getOutgoing().get(i).getTo().getId());
                }

            }
        }
        if (zm) {
            for (int w = _mapOfNode.get(_exitNode).getId()-1; w != _mapOfNode.get(_entryNode).getId()-1; w = edgeTo[w]) {
                nRes.add(_mapOfNode.get(_mapOfNode.get(w+1).getId()));
            }
            nRes.add(_mapOfNode.get(_mapOfNode.get(_entryNode).getId()));

            return true;
        }
        return false;
    }


    // ================== DFS

    public static List<Node> DFS(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
        log.info("Inicjalizacja DFS");

        sRes = new ArrayList();
        nnRes = new ArrayList();
        notVisited =  new ArrayList<>();
        sumValue = 0;

        for(int i = 0; i < _mapOfNode.size();i++)
            notVisited.add(false);
        log.info("Rozpoczęcie przeszukiwania");

        if(!MakeDFS(_entryNode,_exitNode,_mapOfNode))
            log.info("Nie odnaleziono połączenia, zwrócono pustą liste");
        else {
            Collections.reverse(nnRes);
            log.info("Przeszukiwanie zakończone powodzeniem");
        }

        return nnRes;
    }

    private static boolean MakeDFS(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode){
        notVisited.set(_entryNode-1,true);
        int nextNode;
        do {
            nextNode = _entryNode;
            int connectionNumber = -1;
            for (int i = 0; i < _mapOfNode.get(_entryNode).getOutgoing().size(); i++) {
                if (_mapOfNode.get(_entryNode).getOutgoing().get(i).getTo().getId() == _exitNode) {
                    nnRes.add(_mapOfNode.get(_mapOfNode.get(_entryNode).getOutgoing().get(i).getTo().getId()));
                    nnRes.add(_mapOfNode.get(_mapOfNode.get(_entryNode).getId()));
                    return true;
                } else if (!notVisited.get(_mapOfNode.get(_entryNode).getOutgoing().get(i).getTo().getId()-1)) {
                    nextNode = _mapOfNode.get(_entryNode).getOutgoing().get(i).getTo().getId();
                    connectionNumber = i;
                }
                if(nextNode != _entryNode) {
                    if (MakeDFS(nextNode, _exitNode, _mapOfNode)) {
                        //nRes.add(_mapOfNode.get(_mapOfNode.get(_entryNode).getOutgoing().get(connectionNumber).getTo().getId()));
                        nnRes.add(_mapOfNode.get(_mapOfNode.get(_entryNode).getId()));
                        return true;
                    }
                }
            }
        } while (nextNode != _entryNode);
        return false;
    }



}
