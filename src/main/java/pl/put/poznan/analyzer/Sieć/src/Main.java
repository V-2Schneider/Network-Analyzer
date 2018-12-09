package pl.put.poznan.analyzer.Sieć.src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class  Main {
    public static List<Node> nudesy = new ArrayList<>();
    public static int p,ent,outt;

    public static void main(String[] args) throws IOException {

        Graph graph = new Graph();

        //Przykład grafu : 1->2 , 1->3

        Node a = new Node("n1");
        Node b = new Node("n2");
        Node c = new Node("n3");

        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);

        graph.addConnection(1,2,21);
        graph.addConnection(2,3,37);


        // szukanie sciezki Greedy
        List<Connection> szukanie = Search.GreedySeach(1,3,graph.getMapOfNodes());
        System.out.print(szukanie.get(0).getFrom().getId());
        for (Connection res : szukanie ) {
            System.out.print(" -> " + res.getTo().getId());
        }
        System.out.println("");
        // koniec szukania ścieżki Greedy


        System.out.println("Parsuje...");
        Parser.parseGraphToJson(graph);

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
