package pl.put.poznan.analyzer.Sieć.src;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;


 /**
 * This class allows you to find the path (containts all algorithms)
 */
public class  Search {

    // logger
    static Logger log = LoggerFactory.getLogger(Search.class);

    //zmienne globalne


    /**
     * list of connections between nodes
     */
    private static List<Connection> sRes;
    /**
     * list of nodes used as a result
     */
    private static List<Node> nRes;
    /**
     * List of visited nodes in greedy search
     */
    private static List<Boolean> notVisited;
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

    // ================ Greedy Search
     /**
      * This method is used to find the most profitable path from entry to exit using greedy algorithm.
      * @param _entryNode (node that is the beginning of the path), _exitNode (node that is the end of the path), network as HashMap
      * @return shortest path (list of nodes) or null if path can't be found, and -1 on value
      * */

    public static Result GreedySeach(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
        log.info("Inicjalizacja Greedy Searcha");

        sRes = new ArrayList<Connection>();
        notVisited =  new ArrayList<Boolean>();
        sumValue = 0;

        for(int i = 0; i < _mapOfNode.size();i++)
            notVisited.add(false);
        log.info("Rozpoczęcie przeszukiwania");
        Result result;
        if(!MakeGreedySearch(_entryNode,_exitNode,_mapOfNode)) {
            log.info("Nie odnaleziono połączenia, zwrócono -1 w wartości");
            result = new Result(-1, new ArrayList<Node>());
        }else {
            Collections.reverse(sRes);
            log.info("Przeszukiwanie zakończone powodzeniem");
            List<Node> nody = new ArrayList<>();
            nody.add(sRes.get(0).getFrom());
            for ( Connection res : sRes) {
                sumValue += res.getValue();
                nody.add(res.getTo());
            }
            System.out.print(sumValue+"    ");

            result = new Result(sumValue,nody);
        }

        return result;
    }

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


    // ================== BFS
    /**
     * This method is used to find the most profitable path from entry to exit using BFS algorithm.
     * @param _entryNode (node that is the beginning of the path), _exitNode (node that is the end of the path), network as HashMap
     * @return shortest path (list of nodes) or null if path can't be found
    */
    public static Result BFS(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
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
         
            for ( Connection res : sRes) {
                 sumValue += res.getValue();
             }
             //System.out.print(sumValue+"    ");
        }

        //return nRes;
        return new Result(sumValue, nRes);
    }
    
    /**
     * Find the path by using BFS algorithm (with queue)
     * @param _entryNode (node that is the beginning of the path), _exitNode (node that is the end of the path), network as HashMap
     * @return true when the path exists or false if path can't be found
     */
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
            int id = 0;
            for (int w = _mapOfNode.get(_exitNode).getId()-1; w != _mapOfNode.get(_entryNode).getId()-1; w = edgeTo[w]) {
                nRes.add(_mapOfNode.get(_mapOfNode.get(w+1).getId()));
             
                if (id !=0) {
                    for (int connectionNumber = 0; connectionNumber < _mapOfNode.get(_mapOfNode.get(w+1).getId()).getOutgoing().size(); connectionNumber++) {
                        if (_mapOfNode.get(_mapOfNode.get(w+1).getId()).getOutgoing().get(connectionNumber).getTo().getId() == id) {
                            sRes.add(_mapOfNode.get(_mapOfNode.get(w+1).getId()).getOutgoing().get(connectionNumber));
                        }
                    }
                }
                id =  _mapOfNode.get(w+1).getId();
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


    // ================== DFS
    /**
     * This method is used to find the most profitable path from entry to exit using DFS algorithm.
     * @param _entryNode (node that is the beginning of the path), _exitNode (node that is the end of the path), network as HashMap
     * @return shortest path (list of nodes) or null if path can't be found
    */
    public static Result DFS(int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
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
        return new Result(sumValue, nRes);
    }
    
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
