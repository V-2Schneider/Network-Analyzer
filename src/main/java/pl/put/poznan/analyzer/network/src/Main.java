package pl.put.poznan.analyzer.network.src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class  Main {
    public static List<Node> nudesy = new ArrayList<>();
    public static int p,ent,outt;

    public static void main(String[] args) throws IOException {

//        Graph graph = new Graph();
//
//        //Przykład grafu : 1->2 , 1->3
//
//        Node a = new Node("n1");
//        Node b = new Node("n2");
//        Node c = new Node("n3");

//        graph.addNode(a);
//        graph.addNode(b);
//        graph.addNode(c);
//
//        graph.addConnection(1,2,21);
//        graph.addConnection(2,3,37);
//
//        System.out.println("Parsuje...");
//        String output = Parser.parseGraphToJsonString(graph);
//
//        //test wypisywania
//        System.out.println(output);
//
//        //test zapisu
//        Parser.writeToFile("json.txt",output);

        //test odczytu
//        String read = readFile("json.txt");
//        System.out.println(read);

        //test parsowania JsonString do graph.
//        Graph graph = Parser.parseJsonStringToGraph(read);
//        for(HashMap.Entry<Integer,Connection> entry: graph.getMapOfConnections().entrySet()) {
//            System.out.println("entry_key_" + entry.getKey().toString() + " , value : " + entry.getValue().toString() );
//        }
//
//        for(HashMap.Entry<Integer,Node> entry: graph.getMapOfNodes().entrySet()){
//            System.out.println("node_" + entry.getKey().toString() + ", value : " + entry.getValue().toString());
//        }

        //test parse nodes to JSON
        Node a = new Node("papiez");
        Node b = new Node("zawadiaka");

        String output = Parser.parseNodesToRequest(a,b);
        Parser.writeToFile("request2.txt",output);

//        //wprowadzanie
//        Scanner s = new Scanner(System.in);
//        System.out.println("Gimme ilość nodes");
//        p = s.nextInt();
//        for (int i = 1; i <= p; i++) {
//            System.out.println("Oto jest nudes nr " + i);
//            Node n = new Node();
//            n.setId(i);
//            n.setName();
//            System.out.println("Gimme ilość wchodzace");
//            ent = s.nextInt();
//            if (ent != 0) {
//                n.readIncoming(ent);
//            }
//            System.out.println("Gimme ilość wychodzace");
//            outt = s.nextInt();
//            if (outt != 0) {
//                n.readOutgoing(outt);
//            }
//            n.setType();
//            nudesy.add(n);
//
//
//        }
//
//        //wyświetlanie
//        for (int i = 0; i < nudesy.size(); i++) {
//            System.out.println("Oto jest " + nudesy.get(i).getName());
//            nudesy.get(i).getIncoming();
//            nudesy.get(i).getOutgoing();
//            nudesy.get(i).getTypeOfNode();
//        }
    }

}
