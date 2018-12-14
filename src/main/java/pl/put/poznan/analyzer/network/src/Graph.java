package pl.put.poznan.analyzer.network.src;

import java.util.HashMap;

public class Graph {
    private HashMap<Integer,Node> MapOfNodes;
    private HashMap<Integer,Connection> MapOfConnections;

    public Graph() {
        MapOfNodes = new HashMap<Integer,Node>();
        MapOfConnections = new HashMap<Integer,Connection>();
    }

    public HashMap<Integer,Node> getMapOfNodes() {
        return MapOfNodes;
    }

    public HashMap<Integer,Connection> getMapOfConnections() {
        return MapOfConnections;
    }

    public void addNode(Node node){
        if(!MapOfNodes.containsValue(node)){
            MapOfNodes.put(node.getId(),node);
        }
    }

    public Node getNodeById(int id){
        return MapOfNodes.get(id);
    }

    public void addConnection(int id_from, int id_to, int value){
        //Numery połączeń generowane automatycznie jako kolejne liczby (z Map.size()), są tworzone na potrzeby przechowywania w HashMap.
        //Połączenie musi być zapisane w Mapie grafu, oraz w listach odpowiednich Node'ów. (wg założeń projektu)

        Node from = MapOfNodes.get(id_from);
        Node to = MapOfNodes.get(id_to);
        Connection connection = new Connection(from,to,value);

        MapOfConnections.put(MapOfConnections.size(), connection);
        from.addToOutgoing(connection);
        to.addToIncoming(connection);
        from.setType();
        to.setType();
    }
}
