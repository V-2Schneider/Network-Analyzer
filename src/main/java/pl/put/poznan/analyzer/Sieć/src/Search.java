package pl.put.poznan.analyzer.Sieć.src;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Search {

    //zmienne globalne

    private static List<Connection> sRes;
    private static List<Boolean> notVisited;

    // ================ Greedy Search


    public static List<Connection> GreedySeach(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
        sRes = new ArrayList();
        notVisited =  new ArrayList<>();

        for(int i = 0; i < _mapOfNode.size();i++)
            notVisited.add(false);

        if(!MakeGreedySearch(_entryNode,_exitNode,_mapOfNode))
            System.out.println("Nie znaleziono połączenia, zwrócono pusty obiekt");
        else {
            Collections.reverse(sRes);
        }

        return sRes;
    }

    private static boolean MakeGreedySearch(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode){
        notVisited.set(_entryNode,true);
        int nextNode;
        do {
            int minCost = 6666666;
            nextNode = _entryNode;
            int connectionNumber = -1;

            for (int i = 0; i < _mapOfNode.get(_entryNode).getOutgoing().size(); i++) {
                if (_mapOfNode.get(_entryNode).getOutgoing().get(i).getTo().getId() == _exitNode) {
                    sRes.add(_mapOfNode.get(_entryNode).getOutgoing().get(i));
                    return true;
                } else if (_mapOfNode.get(_entryNode).getOutgoing().get(i).getValue() < minCost && !notVisited.get(_mapOfNode.get(_entryNode).getOutgoing().get(i).getTo().getId())) {
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
