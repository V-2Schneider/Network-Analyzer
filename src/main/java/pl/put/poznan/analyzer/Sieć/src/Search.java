package pl.put.poznan.analyzer.Sieć.src;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Search {

    // logger
    static Logger log = LoggerFactory.getLogger(Search.class);

    //zmienne globalne



    private static List<Connection> sRes;
    private static List<Boolean> notVisited;

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


    // ================== DFS



}
