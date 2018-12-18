package pl.put.poznan.analyzer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        Node d = new Node("n4");
        Node e = new Node("n5");
        Node f = new Node("n6");
        Node g = new Node("n7");
        Node h = new Node("n8");

        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(f);
        graph.addNode(g);
        graph.addNode(h);

        log.info("dodano wierzchołki");

        graph.addConnection(1,2,21);
        graph.addConnection(2,3,37);
        graph.addConnection(3,4,15);
        graph.addConnection(1,5,2);
        graph.addConnection(5,6,3);
        graph.addConnection(6,4,1);
        graph.addConnection(1,7,40);
        graph.addConnection(7,4,2);
        graph.addConnection(4,8,1);


        log.info("dodano połączenia");

        // szukanie sciezki Greedy
        PathResult szukanie = Search.GreedySeach(1,4,graph.getMapOfNodes());
        if(szukanie.getValue() >0){

            for (Node res :  szukanie.getNodes() ) {
             System.out.print( res.getId() + " -> ");
            }
            System.out.println("");
        }
        // koniec szukania ścieżki Greedy

        // szukanie sciezki BFS
        PathResult szukaniee = Search.BFS(1,4,graph.getMapOfNodes());
        float sum =0;
        sum= szukaniee.getValue();
        System.out.println(sum);
        if(szukaniee.getNodes().size() >0){
            //System.out.print(szukaniee.get(0).getId());
            for (Node nes : szukaniee.getNodes() ) {
                System.out.print(nes.getId()+ " -> " );
            }
            System.out.println("");
        }

        // szukanie sciezki DFS
        PathResult szukanieee = Search.DFS(1,4,graph.getMapOfNodes());
        sum =0;
        sum= szukanieee.getValue();
        System.out.println(sum);
        if(szukanieee.getNodes().size() >0){
            //System.out.print(szukaniee.get(0).getId());
            for (Node ness : szukanieee.getNodes() ) {
                System.out.print(ness.getId()+ " -> " );
            }
            System.out.println("");
        }


        log.info("rozpoczęcie parsowania");


        System.out.println("Parsuje...");
        Parser.parseGraphToJsonString(graph);

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
