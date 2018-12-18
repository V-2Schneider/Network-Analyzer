package pl.put.poznan.analyzer.network.src;

import com.google.gson.Gson;

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
//        Node a = new Node("papiez");
//        Node b = new Node("zawadiaka");
//
//        String output = Parser.parseNodesToJsonString(a,b);
//        Parser.writeToFile("request2.txt",output);

        //test parse json to nodes
//        String read = readFile("nodes.txt");
//        ArrayList<Node> list = Parser.parseJsonStringToNodes(read);
//
//        for(Node element: list){
//            System.out.println(element.toString());
//        }

        //test parse Path to Json
//        Node a = new Node("test_1");
//        Node b = new Node("test_2");
//        Node c = new Node("test_3");
//        Node d = new Node("test_4");
//
//        ArrayList<Node> list = new ArrayList<>();
//
//        list.add(a);
//        list.add(b);
//        list.add(c);
//        list.add(d);
//
//        Parser.writeToFile("PathTest.txt",Parser.parsePathToJsonString(list));

        //test parse Json to Path
        ArrayList<Node> list2 = Parser.parseRequestToNodesList(Parser.readFile("PathTest.txt"));
        for(Node node: list2){
            System.out.println(node.toString());
        }
    }

}
