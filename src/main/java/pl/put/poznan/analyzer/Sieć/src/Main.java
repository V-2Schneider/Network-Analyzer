package pl.put.poznan.analyzer.Sieć.src;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class  Main {
    public static List<Node> nudesy = new ArrayList<>();
    public static int p,ent,outt;

    // logger
    static Logger log = LoggerFactory.getLogger(Search.class);

    public static void main(String[] args) throws IOException {

        log.info("Uruchomiono serwer");

        Graph graph = new Graph();

        //Przykład grafu : 1->2 , 1->3

        Node a = new Node("n1");
        Node b = new Node("n2");
        Node c = new Node("n3");

        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);

        log.info("dowano wierzchołki");

        graph.addConnection(1,2,21);
        graph.addConnection(2,3,37);

        log.info("dodano połączenia");

        // szukanie sciezki Greedy
        List<Connection> szukanie = Search.GreedySeach(1,3,graph.getMapOfNodes());
        if(szukanie.size() >0){
            System.out.print(szukanie.get(0).getFrom().getId());
            for (Connection res : szukanie ) {
             System.out.print(" -> " + res.getTo().getId());
            }
            System.out.println("");
        }
        // koniec szukania ścieżki Greedy
        
        // szukanie sciezki BFS
        List<Node> szukaniee = Search.BFS(1,4,graph.getMapOfNodes());
        if(szukaniee.size() >0){
            //System.out.print(szukaniee.get(0).getId());
            for (Node nes : szukaniee ) {
                System.out.print(nes.getId()+ " -> " );
            }
            System.out.println("");
        }

        // szukanie sciezki DFS
        List<Node> szukanieee = Search.DFS(1,4,graph.getMapOfNodes());
        if(szukanieee.size() >0){
            //System.out.print(szukaniee.get(0).getId());
            for (Node ness : szukanieee ) {
                System.out.print(ness.getId()+ " -> " );
            }
            System.out.println("");
        }
        

        log.info("rozpoczęcie parsowania");


        System.out.println("Parsuje...");
        Parser.parseGraphToJson(graph);

        log.info("parsowanie zakończone");

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
